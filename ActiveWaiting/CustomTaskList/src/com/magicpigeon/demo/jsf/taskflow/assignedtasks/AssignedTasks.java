package com.magicpigeon.demo.jsf.taskflow.assignedtasks;

import java.util.ArrayList;
import java.util.List;

import com.magicpigeon.demo.model.Task;

import com.magicpigeon.demo.util.JSFUtils;

import oracle.bpel.services.workflow.IWorkflowConstants;
import oracle.bpel.services.workflow.WorkflowException;
import oracle.bpel.services.workflow.client.IWorkflowServiceClient;
import oracle.bpel.services.workflow.client.WorkflowServiceClientFactory;
import oracle.bpel.services.workflow.query.ITaskQueryService;
import oracle.bpel.services.workflow.repos.Predicate;
import oracle.bpel.services.workflow.repos.TableConstants;
import oracle.bpel.services.workflow.verification.IWorkflowContext;

/**
 * Data Control for retrieving the Assigned Tasks
 */
public final class AssignedTasks {
    
    /**
     * List of Tasks
     */
    private List<Task> tasks = new ArrayList<Task>(20);

    /**
     * Set the Tasks
     * @param tasks
     */
    public void setTasks(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Get the Tasks
     * @return List
     */
    public List<Task> getTasks() {
        try {
            tasks = this.retrieveAssignedUserTasks();
        } catch (WorkflowException e) {
            e.printStackTrace();
        }
        
        return tasks;
    }
    
    /**
     * Auxiliar method to call BPM API to retrieve the assigned Tasks
     * @return List
     * @throws WorkflowException
     */
    public List<Task> retrieveAssignedUserTasks() throws WorkflowException {
        tasks.clear();
        
        List<String> correctStates = new ArrayList<String>();
        correctStates.add(IWorkflowConstants.TASK_STATE_ASSIGNED);
        Predicate predicateBasic = new Predicate(TableConstants.WFTASK_STATE_COLUMN, Predicate.OP_IN, correctStates);
        
        List<String> displayColumns = new ArrayList<>();
        displayColumns.add("TASKID");
        displayColumns.add("TASKNUMBER");
        displayColumns.add("TITLE");
        displayColumns.add("PRIORITY");
        displayColumns.add("STATE");
        displayColumns.add("CREATOR");
        displayColumns.add("CREATEDDATE");
        
        IWorkflowContext ctx = (IWorkflowContext)JSFUtils.getManagedBeanValue("sessionScope.workflowContext");
        
        IWorkflowServiceClient  wfSvcClient = WorkflowServiceClientFactory.getWorkflowServiceClient(WorkflowServiceClientFactory.REMOTE_CLIENT);
        ITaskQueryService querySvc = wfSvcClient.getTaskQueryService();
        
        List worklistTasks = querySvc.queryTasks(ctx,
                     displayColumns,                   
                     null, //Do not query additional info
                     ITaskQueryService.AssignmentFilter.MY_AND_GROUP_ALL,
                     null, //No keywords
                     predicateBasic , 
                     null, //No special ordering
                     0,    //Do not page the query result
                     0);
        
        for(int i = 0 ; i < worklistTasks.size() ; i ++) {
            oracle.bpel.services.workflow.task.model.Task worklistTask = (oracle.bpel.services.workflow.task.model.Task)worklistTasks.get(i);
            String taskId = worklistTask.getSystemAttributes().getTaskId();
            String taskNumber = Integer.toString(worklistTask.getSystemAttributes().getTaskNumber());
            String title = worklistTask.getTitle();
            String priority = Integer.toString(worklistTask.getPriority());
            String state = worklistTask.getSystemAttributes().getState();
            String creator = worklistTask.getCreator();
            
            Task task = new Task();
            task.setTaskId(taskId);
            task.setTaskNumber(taskNumber);
            task.setTitle(title);
            task.setPriority(priority);
            task.setState(state);
            task.setCreator(creator);
            
            tasks.add(task);
        }
        return tasks;
    }
}
