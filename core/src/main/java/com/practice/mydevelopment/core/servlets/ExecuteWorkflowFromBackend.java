package com.practice.mydevelopment.core.servlets;

import com.adobe.granite.workflow.WorkflowSession;
import com.adobe.granite.workflow.exec.WorkflowData;
import com.adobe.granite.workflow.model.WorkflowModel;
import org.apache.commons.lang.StringUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;

@Component(service = Servlet.class,
        property = {
                "sling.servlet.paths=/bin/executeworkflow",
                "sling.servlet.paths=/mydevelopment/executeworkflow"
        }
)
public class ExecuteWorkflowFromBackend extends SlingSafeMethodsServlet {
    private static final Logger log = LoggerFactory.getLogger(ExecuteWorkflowFromBackend.class);

    @Override
    protected void doGet(final SlingHttpServletRequest req, final SlingHttpServletResponse res) throws ServletException, IOException {
        String status = "Workflow Executing";

        final ResourceResolver resourceResolver = req.getResourceResolver();
        String payload = req.getRequestParameter("page").getString();

        try{
            if(StringUtils.isNotBlank(payload)){
                WorkflowSession workflowSession = resourceResolver.adaptTo(WorkflowSession.class);

                WorkflowModel workflowModel = workflowSession.getModel("/var/workflow/models/custom-create-version");

                WorkflowData workflowData = workflowSession.newWorkflowData("JCR_PATH",payload);

                status = workflowSession.startWorkflow(workflowModel, workflowData).getState();
            }
        }catch(Exception e){
            log.info("\n Error in Workflow {}", e.getMessage());
        }
        res.setContentType("application/json");
        res.getWriter().write(status);
    }
}
