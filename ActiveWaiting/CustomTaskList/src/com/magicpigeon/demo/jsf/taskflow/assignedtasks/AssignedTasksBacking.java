package com.magicpigeon.demo.jsf.taskflow.assignedtasks;

import com.magicpigeon.demo.pi.client.types.CustomProcessInfo;
import com.magicpigeon.demo.pi.client.types.CustomProcessInfoCollection;
import com.magicpigeon.demo.pi.client.types.DbDemoSelectCustomId;
import com.magicpigeon.demo.util.ADFUtils;

import com.magicpigeon.demo.util.BPMHelper;

import java.io.IOException;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import javax.annotation.PostConstruct;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import oracle.adf.view.rich.component.rich.RichPoll;
import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;
import oracle.adf.view.rich.context.AdfFacesContext;

import oracle.bpel.services.workflow.StaleObjectException;
import oracle.bpel.services.workflow.WorkflowException;

import org.apache.commons.lang.StringUtils;
import org.apache.myfaces.trinidad.event.PollEvent;
import org.apache.myfaces.trinidad.render.ExtendedRenderKitService;
import org.apache.myfaces.trinidad.util.ComponentReference;
import org.apache.myfaces.trinidad.util.Service;

/**
 * Backing Bean for the Assigned Tasks Fragment UI
 */
public final class AssignedTasksBacking {
    
    // Injection: Old fashion way JSF
    private AssignedTasksHelper assignedTasksHelper;
    
    // UI Components
    private ComponentReference poller;
    private ComponentReference busyPopup;
    private ComponentReference pglPollerWrapper;

    /**
     * Set Poller UI Component
     * @param poller
     */
    public void setBusyPopup(RichPopup busyPopup) {
        this.busyPopup = ComponentReference.newUIComponentReference(busyPopup);
    }
    
    @PostConstruct
    public void init() {
    }
    
    /**
     * Refresh the Task List
     */
    public void refreshAssignedTasks(ActionEvent ae) {
        ADFUtils.findOperation("retrieveAssignedUserTasks").execute();      
        ADFUtils.findIterator("tasksIterator").executeQuery();
    }
    
    /**
     * Starts the BPM Demo Async Process will takes time to arrive to the first human task
     * @param ae
     */
    public void startAsyncProcess(ActionEvent ae) {
        // Clean previius
        this.assignedTasksHelper.setProcessAuditImage(null);
        this.assignedTasksHelper.setInstanceId(null);
        this.assignedTasksHelper.setBackgroundText("Loading...\n");
        // Generate random input
        int randomCustomId = ThreadLocalRandom.current().nextInt(0, 200 + 1);
        // Store Case ID to persist in a longer life object
        String customId = String.valueOf(randomCustomId);
        this.assignedTasksHelper.setCustomId(customId);
        // Asyncronous Call to a BPM process
        this.assignedTasksHelper.getDemoProcessServices().start(customId);
        // Activate Poller
        // Update the Poller to poll each 2 seconds
        this.setPollerIntervanl(2000);
        AdfFacesContext.getCurrentInstance().addPartialTarget(this.getPoller());
        // 3. Load Busy Popup
        openPopup(this.getBusyPopupClientId());
    }
    
    /**
     * The Poll Event checks if the Human Task has been reached, if not it continues polling
     * @param ae
     */
    public void pollerEvent(PollEvent pe) throws WorkflowException, StaleObjectException, IOException, Exception {
        this.assignedTasksHelper.increasePollTimes();
        String taskURL = "";
        oracle.bpel.services.workflow.task.model.Task workListTask = null;
        // 1. Before retrieving BPM process image / audit, it is required the BPM Process Instance
        if (assignedTasksHelper.getInstanceId() == null) {
            DbDemoSelectCustomId customId = new DbDemoSelectCustomId();
            customId.setCustomId(this.assignedTasksHelper.getCustomId());

            CustomProcessInfoCollection processInfo =
                assignedTasksHelper.getDemoDBServices().dbDemoSelect(customId);
            List<CustomProcessInfo> customProcessInfo = processInfo.getCustomProcessInfo();
            if (customProcessInfo != null && customProcessInfo.size() > 0 && customProcessInfo.size() < 2) {
                this.assignedTasksHelper.setInstanceId(customProcessInfo.get(0).getInstanceNumber().getValue().toString());
            }
        // Polling to get a Task available
        } else {
            this.assignedTasksHelper.setBackgroundText(this.assignedTasksHelper.getBackgroundText() + BPMHelper.currentBPMActivity(this.assignedTasksHelper.getInstanceId()));
            // Get Audit Image to display how is in the process
            this.assignedTasksHelper.setProcessAuditImage(BPMHelper.getAuditImage(this.assignedTasksHelper.getInstanceId()));
//             Get the Task to reditect to 
            workListTask = BPMHelper.getWorklistTaskByInstanceId(this.assignedTasksHelper.getInstanceId());
            System.out.println(workListTask);
            if (workListTask != null) {
                System.out.println(workListTask.getProcessInfo().getInstanceId());
                taskURL = BPMHelper.getTaskURLFromDisplayInfo(workListTask);
            }
        }
         
        // In case of we reach the limit, or a Human Task has been reached...
        if (this.assignedTasksHelper.getPollTimes() == 20 || StringUtils.isNotEmpty(taskURL)) {
            FacesContext fctx = FacesContext.getCurrentInstance();
            ExtendedRenderKitService erks = Service.getRenderKitService(fctx, ExtendedRenderKitService.class);
            // Invoke a java script method name called showConfPopup()' with two parameters, You can pass any value
            erks.addScript(fctx, "disablePoller('" + getBusyPopupClientId() + "'");
            this.getBusyPopup().hide();
            this.getPoller().setInterval(-1);
            // PPR to the wrapper component of the Poller to invalidate it
            AdfFacesContext.getCurrentInstance().addPartialTarget(this.getPglPollerWrapper());
            // Reset Poll Times
            this.assignedTasksHelper.resetPollTimes();
            
            if (StringUtils.isNotEmpty(taskURL)) {
                // AutoClaim
                BPMHelper.autoClaimTask(workListTask);
                fctx.getExternalContext().redirect(taskURL);
                fctx.responseComplete();
            }
        }
    }
    
    // Private auxiliar methods
    
    /**
     * Set a specific interval to the poller
     * @param interval
     */
    private void setPollerIntervanl(int interval) {
        this.getPoller().setInterval(interval);
    }
    
    /**
     * Returns the generated Client ID for the Busy Popup Component
     * @return String
     */
    private String getBusyPopupClientId() {
        return this.getBusyPopup().getClientId();
    }
    
    /**
     * Open the RichPopup based on the Client ID
     * @param popupId
     */
    public void openPopup(String popupId) {
        ExtendedRenderKitService erkService =
            Service.getService(FacesContext.getCurrentInstance().getRenderKit(), ExtendedRenderKitService.class);
        erkService.addScript(FacesContext.getCurrentInstance(),
                             "var hints = {autodismissNever:true}; " + "AdfPage.PAGE.findComponent('" + popupId +
                             "').show(hints);");
    }

    
    // Injection

    /**
     * Set the Assigned Tasks Helper Bean
     * @param assignedTasksHelper
     */
    public void setAssignedTasksHelper(AssignedTasksHelper assignedTasksHelper) {
        this.assignedTasksHelper = assignedTasksHelper;
    }
    
    // Getters and Setters
    /**
     * Get Poller UI Component
     * @return RichPoll
     */
    public RichPoll getPoller() {
        if (poller != null) {
            return (RichPoll) poller.getComponent();
        }
        return null;
    }

    /**
     * Set Poller UI Component
     * @param poller
     */
    public void setPoller(RichPoll poller) {
        this.poller = ComponentReference.newUIComponentReference(poller);
    }
    
    /**
     * Get Busy Popup UI Component
     * @return RichPoll
     */
    public RichPopup getBusyPopup() {
        if (busyPopup != null) {
            return (RichPopup) busyPopup.getComponent();
        }
        return null;
    }
    
    /**
     * Set Busy Popup UI Component
     * @param busyPopup
     */
    public void setBusyPopup(RichPanelGroupLayout busyPopup) {
        this.busyPopup = ComponentReference.newUIComponentReference(busyPopup);
    }
    
    /**
     * Get Busy Popup UI Wrapper
     * @return RichPanelGroupLayout
     */
    public RichPanelGroupLayout getPglPollerWrapper() {
        if (pglPollerWrapper != null) {
            return (RichPanelGroupLayout) pglPollerWrapper.getComponent();
        }
        return null;
    }
    
    /**
     * Set Busy Popup UI Wrapper
     * @param busyPopup
     */
    public void setPglPollerWrapper(RichPanelGroupLayout pglPollerWrapper) {
        this.pglPollerWrapper = ComponentReference.newUIComponentReference(pglPollerWrapper);
    }
}
