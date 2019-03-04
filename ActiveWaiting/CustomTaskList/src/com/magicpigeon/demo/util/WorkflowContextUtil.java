package com.magicpigeon.demo.util;

import oracle.bpel.services.workflow.WorkflowException;
import oracle.bpel.services.workflow.client.IWorkflowServiceClient;
import oracle.bpel.services.workflow.client.WorkflowServiceClientFactory;
import oracle.bpel.services.workflow.query.ITaskQueryService;
import oracle.bpel.services.workflow.verification.IWorkflowContext;

/**
 * Utility Class for managing the WorkflowContext object in BPEL/BPM
 * @version 1.0
 */
public final class WorkflowContextUtil {
    
    /**
     * Default Constructor
     */
    public WorkflowContextUtil() {
        super();
    }
    
    /**
     * Return the Workflow Context object for the given user
     * @param userName - Identifier of the user to use for loading the Workflow Context
     * @return IWorkflowContext
     * @throws WorkflowException - In the case of the WorkflowContext cannot be generated
     */
    public static IWorkflowContext initBPMContext(final String userName) throws WorkflowException {
        IWorkflowServiceClient  wfSvcClient = WorkflowServiceClientFactory.getWorkflowServiceClient(WorkflowServiceClientFactory.REMOTE_CLIENT);
        ITaskQueryService querySvc = wfSvcClient.getTaskQueryService();
        IWorkflowContext ctx = querySvc.authenticate("weblogic", "Oracle12c".toCharArray(), null);
        return querySvc.authenticateOnBehalfOf(ctx, userName);
    }
}