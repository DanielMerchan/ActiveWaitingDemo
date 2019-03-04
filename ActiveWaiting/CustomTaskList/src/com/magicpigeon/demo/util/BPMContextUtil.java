package com.magicpigeon.demo.util;

import java.util.HashMap;
import java.util.Map;

import oracle.bpel.services.bpm.common.IBPMContext;
import oracle.bpel.services.workflow.client.IWorkflowServiceClient;
import oracle.bpel.services.workflow.client.IWorkflowServiceClientConstants;
import oracle.bpel.services.workflow.client.WorkflowServiceClientFactory;
import oracle.bpel.services.workflow.query.ITaskQueryService;
import oracle.bpel.services.workflow.task.ITaskService;

import oracle.bpm.client.BPMServiceClientFactory;
import oracle.bpm.services.client.IBPMServiceClient;
import oracle.bpm.services.common.exception.BPMException;

/**
 * Utilities class for managing the client connection to the Oracle BPM API (via EJB) and retrieving a BPMContext for the current user
 */
public final class BPMContextUtil {
    
    /**
     * URL where Oracle BPM is running
     */
    private static String url = "t3://127.0.0.1:7101";

    /**
     * Construct a BPM Client Interface for invoking the BPM EJB API
     * @return BPMServiceClientFactory
     */
    public static BPMServiceClientFactory getBPMServiceClientFactory() {
        Map<IWorkflowServiceClientConstants.CONNECTION_PROPERTY, String> properties =
            new HashMap<IWorkflowServiceClientConstants.CONNECTION_PROPERTY, String>();

        properties.put(IWorkflowServiceClientConstants.CONNECTION_PROPERTY.CLIENT_TYPE,
                       WorkflowServiceClientFactory.REMOTE_CLIENT);
        properties.put(IWorkflowServiceClientConstants.CONNECTION_PROPERTY.EJB_PROVIDER_URL, url);
        properties.put(IWorkflowServiceClientConstants.CONNECTION_PROPERTY.EJB_INITIAL_CONTEXT_FACTORY,
                       "weblogic.jndi.WLInitialContextFactory");
        return BPMServiceClientFactory.getInstance(properties, null, null);
    }

    /**
     * Retrieves the IBPMContext for the given user
     * @param userName - User Identifier used for retrieving the BPMContext
     * @return IBPMContext
     * @throws BPMException
     */
    public static IBPMContext getIBPMContext(String userName) throws BPMException {
        IBPMContext ctx =
            getBPMServiceClientFactory().getBPMUserAuthenticationService()
            .authenticate("weblogic", "Oracle12c".toCharArray(), null);
        ctx = getBPMServiceClientFactory().getBPMUserAuthenticationService().authenticateOnBehalfOf(ctx, userName);
        return ctx;
    }

    /**
     * Get a WorkflowServiceClient for invoking Workflow Services 
     * @return IWorkflowServiceClient
     */
    public static IWorkflowServiceClient getIWorkflowServiceClient() {
        return getBPMServiceClientFactory().getWorkflowServiceClient();
    }

    /**
     * Get a ITaskService for invoking Task Services
     * @return ITaskService
     */
    public static ITaskService getTaskService() {
        return getBPMServiceClientFactory().getWorkflowServiceClient().getTaskService();
    }

    /**
     * Get a ITaskQueryService for invoking Task Query Services
     * @return ITaskQueryService
     */
    public static ITaskQueryService getTaskQueryService() {
        return getBPMServiceClientFactory().getWorkflowServiceClient().getTaskQueryService();
    }

    /**
     * Get a IBPMServiceClient for invoking BPM Services
     * @return IBPMServiceClient
     */
    public static IBPMServiceClient getBPMServiceClient() {
        return getBPMServiceClientFactory().getBPMServiceClient();
    }
}
