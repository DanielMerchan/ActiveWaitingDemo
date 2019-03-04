package com.magicpigeon.demo.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import java.util.Map;

import oracle.bpel.services.workflow.StaleObjectException;
import oracle.bpel.services.workflow.WorkflowException;
import oracle.bpel.services.workflow.query.ITaskQueryService;
import oracle.bpel.services.workflow.repos.Predicate;
import oracle.bpel.services.workflow.repos.TableConstants;
import oracle.bpel.services.workflow.task.ITaskService;
import oracle.bpel.services.workflow.task.model.Task;
import oracle.bpel.services.workflow.verification.IWorkflowContext;
import oracle.bpel.services.workflow.worklist.api.util.WorklistUtil;
import oracle.bpel.services.workflow.worklist.servlet.Constants;

import oracle.bpm.client.BPMServiceClientFactory;
import oracle.bpm.services.client.IBPMServiceClient;
import oracle.bpm.services.common.exception.BPMException;
import oracle.bpm.services.instancequery.IAuditInstance;
import oracle.bpm.services.instancequery.IInstanceQueryService;

public final class BPMHelper {

    /**
     * Returns a Task based on querying the CaseId
     * @param instanceId - Instance ID
     * @return BPEL Task
     * @throws WorkflowException
     */
    public static Task getWorklistTaskByInstanceId(String instanceId) throws WorkflowException {

        Task worklistTask = null;

        Predicate predicateBasic = new Predicate(TableConstants.WFTASK_INSTANCEID_COLUMN, Predicate.OP_EQ, instanceId);
        List displayColumns = new ArrayList();
        displayColumns.add("TASKID");
        displayColumns.add("TASKNUMBER");
        displayColumns.add("TITLE");
        displayColumns.add("PRIORITY");
        displayColumns.add("STATE");
        displayColumns.add("CREATOR");
        displayColumns.add("CREATEDDATE");
        displayColumns.add("ASSIGNEES");
        displayColumns.add(TableConstants.WFTASK_COMPOSITEINSTANCEID_COLUMN.getName());
        displayColumns.add(TableConstants.WFTASK_PROCESSID_COLUMN.getName());
        displayColumns.add(TableConstants.WFTASK_PROCESSNAME_COLUMN.getName());
        displayColumns.add(TableConstants.WFTASK_ACTIVITYID_COLUMN.getName());
        displayColumns.add(TableConstants.WFTASK_ACTIVITYNAME_COLUMN.getName());
        displayColumns.add(TableConstants.WFTASK_IDENTIFICATIONKEY_COLUMN.getName());
        displayColumns.add(TableConstants.WFTASK_COMPONENTINSTANCEID_COLUMN.getName());
        displayColumns.add(TableConstants.WFTASK_INSTANCEID_COLUMN.getName());
        displayColumns.add(TableConstants.WFTASK_TASKDISPLAYURL_COLUMN.getName());

        // Query Additional Info
        List optionalInfo = new ArrayList();
        optionalInfo.add(ITaskQueryService.OptionalInfo.DISPLAY_INFO);

        IWorkflowContext ctx = (IWorkflowContext) JSFUtils.getManagedBeanValue("sessionScope.workflowContext");
        ITaskQueryService querySvc = BPMContextUtil.getTaskQueryService();

        List worklistTasks = querySvc.queryTasks(ctx, displayColumns, optionalInfo, //dDo not query additional info
                                                 ITaskQueryService.AssignmentFilter.MY_AND_GROUP, null, //No keywords
                                                 predicateBasic, null, //No special ordering
                                                 0, //Do not page the query result
                                                 0);

        oracle.bpel.services.workflow.task.model.Task taskReturned = null;
        for (int i = 0; i < worklistTasks.size(); i++) {
            worklistTask = (oracle.bpel.services.workflow.task.model.Task) worklistTasks.get(i);
            String taskId = worklistTask.getSystemAttributes().getTaskId();
            if (taskId != null) {
                for (Object o : worklistTask.getSystemAttributes().getAssignees()) {
                    oracle.bpel.services.workflow.task.model.IdentityTypeImpl assignee =
                        (oracle.bpel.services.workflow.task.model.IdentityTypeImpl) o;
                    System.out.println("Assignee: " + assignee.getDisplayName());
                }
            }
        }
        return worklistTask;
    }


    /**
     * Given a BPEL Human Task it extracts which is the URI to perform this Human Task
     * @param task
     * @return
     * @throws Exception
     */
    public static String getTaskURLFromDisplayInfo(Task task) throws Exception {
        String url = "";
        //        List displayInfo = task.getSystemAttributes().getDisplayInfo();
        //        oracle.xml.jaxb.JaxbListImpl jaxbList = (JaxbListImpl) task.getSystemAttributes().getDisplayInfo();
        //        System.out.println("Display Info Size: " + jaxbList.size());
        //        for (Object o : jaxbList) {
        //            oracle.bpel.services.workflow.task.model.DisplayInfoTypeImpl dispInfoImpl = (oracle.bpel.services.workflow.task.model.DisplayInfoTypeImpl)o;
        //            url = "http://"+dispInfoImpl.getHostname()+dispInfoImpl.getHttpPort()+
        //            WorkflowUtil.
        //        }

        Map parameters = new HashMap();
        parameters.put(Constants.BPM_WORKLIST_TASK_ID, task.getSystemAttributes().getTaskId());
        parameters.put(Constants.BPM_WORKLIST_CONTEXT, BPMContextUtil.getIBPMContext("weblogic").getToken());
        BPMServiceClientFactory bpmServiceClientFactory = BPMContextUtil.getBPMServiceClientFactory();

        url =
            WorklistUtil.getTaskDisplayURL(bpmServiceClientFactory.getWorkflowServiceClient(),
                                           BPMContextUtil.getIBPMContext("weblogic"), task, null, "worklist",
                                           parameters);
        return url;
    }

    /**
     * AutoClaim a Task
     * @param worklistTask
     */
    public static void autoClaimTask(Task worklistTask) throws WorkflowException, StaleObjectException {
        IWorkflowContext ctx = (IWorkflowContext) JSFUtils.getManagedBeanValue("sessionScope.workflowContext");
        ITaskService taskSvc = BPMContextUtil.getTaskService();
        ITaskQueryService querySvc = BPMContextUtil.getTaskQueryService();
        Task taskUpdatable = querySvc.getTaskDetailsById(ctx, worklistTask.getSystemAttributes().getTaskId());
        taskSvc.acquireTask(ctx, taskUpdatable);
    }

    /**
     * Returns the audit image for the given Instance Id
     * @param bpmInstanceId
     * @return String
     */
    public static String getAuditImage(String bpmInstanceId) throws BPMException {
        IBPMServiceClient bpmServiceClient = BPMContextUtil.getBPMServiceClient();
        IInstanceQueryService instanceQueryService = bpmServiceClient.getInstanceQueryService();
        //Image in PNG format and base64 coded
        //String imageSeq=instanceQueryService.getProcessDiagram(bpmContext, instanceIdS,java.util.Locale.ENGLISH );
        String imageSeq =
            instanceQueryService.getProcessAuditDiagram(BPMContextUtil.getIBPMContext("weblogic"), bpmInstanceId,
                                                        java.util
                                                                                                                      .Locale
                                                                                                                      .ENGLISH);
        //        byte[] decode = Base64.decode(imageSeq);
        return imageSeq;
    }

    public static String currentBPMActivity(String bpmInstanceId) throws BPMException {
        String activity = "";
        IBPMServiceClient bpmServiceClient = BPMContextUtil.getBPMServiceClient();
        IInstanceQueryService instanceQueryService = bpmServiceClient.getInstanceQueryService();
        List<IAuditInstance> auditInstances =
            instanceQueryService.queryAuditInstanceByProcessId(BPMContextUtil.getIBPMContext("weblogic"),
                                                               bpmInstanceId);
        //            // work out which activities have not finished
        List<IAuditInstance> started = new ArrayList<IAuditInstance>();
        for (IAuditInstance a1 : auditInstances) {
            if (a1.getAuditInstanceType().compareTo("START") == 0) {
                // ingore the process instance itself, we only care
                // about tasks in the process
                if (a1.getActivityName().compareTo("PROCESS") != 0) {
                    started.add(a1);
                }
            }
        }
        next:
        for (IAuditInstance a2 : auditInstances) {
            if (a2.getAuditInstanceType().compareTo("END") == 0) {
                for (int i = 0; i < started.size(); i++) {
                    // IAuditInstance temp=(IAuditInstance)started.get(i);
                    if (a2.getActivityId().compareTo(started.get(i).getActivityId()) == 0) {
                        started.remove(i);
                        continue next;
                    }
                }
            }
        }
        for (IAuditInstance s : started) {
            activity += s.getLabel() + "\n";
        }
        return activity;
    }
}
