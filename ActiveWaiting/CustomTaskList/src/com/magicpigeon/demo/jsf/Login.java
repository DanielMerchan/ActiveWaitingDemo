package com.magicpigeon.demo.jsf;

import com.magicpigeon.demo.util.BPMContextUtil;
import com.magicpigeon.demo.util.JSFUtils;
import com.magicpigeon.demo.util.WorkflowContextUtil;

import java.io.IOException;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import javax.security.auth.Subject;
import javax.security.auth.login.FailedLoginException;
import javax.security.auth.login.LoginException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import oracle.bpel.services.bpm.common.IBPMContext;
import oracle.bpel.services.workflow.WorkflowException;
import oracle.bpel.services.workflow.verification.IWorkflowContext;

import weblogic.security.URLCallbackHandler;
import weblogic.security.services.Authentication;

/**
 * JSF Managed Bean for handling ADF - Security Authentication in Oracle WebLogic Application Server
 * In addition, it initiates the BPM Context and Workflow Context based on the current user
 */
public final class Login {
    
    /**
     * User 
     */
    private String _username;
    
    /**
     * Password
     */
    private String _password;

    /**
     * Set the username
     * @param _username
     */
    public void setUsername(String _username) {
        this._username = _username.toLowerCase();
    }

    /**
     * Get the user name
     * @return String
     */
    public String getUsername() {
        return _username;
    }

    /**
     * Set the password of the user
     * @param _password
     */
    public void setPassword(String _password) {
        this._password = _password;
    }

    /**
     * Get the password of the user
     * @return String
     */
    public String getPassword() {
        return _password;
    }
    
    /**
     * Invokes the ADF Authentication Servlet and initiates the BPM objects in case of a successfull login
     * @return String
     */
    public String doLogin() {
        String un = _username;
        byte[] pw = _password.getBytes();
        FacesContext ctx = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest)ctx.getExternalContext().getRequest();
        try {
            Subject subject = Authentication.login(new URLCallbackHandler(un, pw));
            weblogic.servlet.security.ServletAuthentication.runAs(subject, request);
            
            IWorkflowContext workflowContext = WorkflowContextUtil.initBPMContext(_username);
            JSFUtils.setManagedBeanValue("sessionScope.workflowContext", workflowContext);
            
            IBPMContext ibpmContext = BPMContextUtil.getIBPMContext(_username);
            JSFUtils.setManagedBeanValue("sessionScope.ibpmContext", ibpmContext);
            
            String loginUrl = "/adfAuthentication?success_url=/faces/welcome";
            HttpServletResponse response = (HttpServletResponse)ctx.getExternalContext().getResponse();
            sendForward(request, response, loginUrl);
        } catch (FailedLoginException fle) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Incorrect Username or Password", "An incorrect Username or Password was specified");
            ctx.addMessage(null, msg);
        } catch (LoginException le) {
            reportUnexpectedLoginError("LoginException", le);
        } catch (WorkflowException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Forward to a specific URL
     * @param request - Current HTTP Request
     * @param response - HTTP Response
     * @param forwardUrl - URL to forward
     */
    private void sendForward(HttpServletRequest request, HttpServletResponse response, String forwardUrl) {
        FacesContext ctx = FacesContext.getCurrentInstance();
        RequestDispatcher dispatcher = request.getRequestDispatcher(forwardUrl);
        try {
            dispatcher.forward(request, response);
        } catch (ServletException se) {
            reportUnexpectedLoginError("ServletException", se);
        } catch (IOException ie) {
            reportUnexpectedLoginError("IOException", ie);
        }
        ctx.responseComplete();
    }

    /**
     * Reports an error while Login
     * @param errType
     * @param e
     */
    private void reportUnexpectedLoginError(String errType, Exception e) {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Unexpected error during login", "Unexpected error during login (" + errType + "), please consult logs for detail");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        e.printStackTrace();
    }
    
    /**
     * Logout Action
     * @return String
     */
    public String onLogout() {
        FacesContext fctx = FacesContext.getCurrentInstance();
        ExternalContext ectx = fctx.getExternalContext();
        String url = ectx.getRequestContextPath() + "/adfAuthentication?logout=true&end_url=/faces/welcome";     
        try {
            ectx.redirect(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        fctx.responseComplete();
        return null;
    }
}
