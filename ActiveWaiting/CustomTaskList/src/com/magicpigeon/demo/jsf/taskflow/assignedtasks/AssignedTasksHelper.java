package com.magicpigeon.demo.jsf.taskflow.assignedtasks;

import com.magicpigeon.demo.bpm.client.DemoPortType;
import com.magicpigeon.demo.bpm.client.DemoService;
import com.magicpigeon.demo.pi.client.DbDemoPtt;
import com.magicpigeon.demo.pi.client.DbDemoPttBindingQSService;

import java.io.Serializable;

import javax.annotation.PostConstruct;

/**
 * JSF Managed Bean View Scoped for keeping information longer than a request
 */
public final class AssignedTasksHelper implements Serializable {
    
    @SuppressWarnings("compatibility:7593974525869609123")
    private static final long serialVersionUID = -7621386992647011467L;

    /**
     * Holds how many times has been polled a poller
     */
    private int pollTimes;
    
    /**
     * Holds the message about what is happening in the background
     */
    private String backgroundText;
    
    /**
     * Custom Process / Case Identifier being initiated
     */
    private String customId;
    
    /**
     * BPM Instance ID
     */
    private String instanceId;
    
    /**
     * String base64 PNG of the current process
     */
    private String processAuditImage;
    
    // Services
    private transient DbDemoPtt demoDBServices;
    private transient DemoPortType demoProcessServices;
    
    /**
     * Initializes the instance
     */
    @PostConstruct
    public void init() {
        pollTimes=0;
        instanceId = null;
        processAuditImage = null;
        DbDemoPttBindingQSService dbDemoPttBindingQSService = new DbDemoPttBindingQSService();
        demoDBServices = dbDemoPttBindingQSService.getDbDemoPttBindingQSPort();
        DemoService demoService = new DemoService();
        demoProcessServices = demoService.getDemoPort();
    }
    
    // Auxiliar methods
    /**
     * Increase the Poll Time by +1
     */
    public void increasePollTimes() {
        pollTimes+=1;
    }
    
    /**
     * Reset the Poll Times to 0
     */
    public void resetPollTimes() {
        pollTimes=0;
    }

    /**
     * Set the number of pollTimes
     * @param pollTimes
     */
    public void setPollTimes(int pollTimes) {
        this.pollTimes = pollTimes;
    }

    /**
     * Get the number of polling ticks
     * @return int
     */
    public int getPollTimes() {
        return pollTimes;
    }

    /**
     * Set the Background Text
     * @param backgroundText
     */
    public void setBackgroundText(String backgroundText) {
        this.backgroundText = backgroundText;
    }

    /**
     * Get the Background Text
     * @return String
     */
    public String getBackgroundText() {
        return backgroundText;
    }

    /**
     * Set the Custom Id generated
     * @param caseId
     */
    public void setCustomId(String customId) {
        this.customId = customId;
    }

    /**
     * Get the current Custom Id
     * @return String
     */
    public String getCustomId() {
        return customId;
    }

    /**
     * Set the BPM Instance ID
     * @param instanceId
     */
    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }

    /**
     * Get the BPM Instance ID
     * @return String
     */
    public String getInstanceId() {
        return instanceId;
    }

    /**
     * Set the Process Audit Image Base 64 - PNG
     * @param processAuditImage
     */
    public void setProcessAuditImage(String processAuditImage) {
        this.processAuditImage = processAuditImage;
    }

    /**
     * Return the Process Audit Image Base 64 - PNG
     * @return String
     */
    public String getProcessAuditImage() {
        return processAuditImage;
    }

    /**
     * Obtain the DB Demo Services SOAP Client
     * @return DbDemoPtt
     */
    public DbDemoPtt getDemoDBServices() {
        return demoDBServices;
    }

    /**
     * Obtain the Demo Process BPM SOAP Endpoint
     * @return DemoPortType
     */
    public DemoPortType getDemoProcessServices() {
        return demoProcessServices;
    }
}
