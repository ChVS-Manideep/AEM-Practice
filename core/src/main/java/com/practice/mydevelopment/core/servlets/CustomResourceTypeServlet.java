package com.practice.mydevelopment.core.servlets;

import com.day.cq.commons.jcr.JcrConstants;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.request.RequestParameter;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletResourceTypes;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.propertytypes.ServiceDescription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.Servlet;
import java.io.IOException;
import java.util.List;

@Component(service = Servlet.class)
@SlingServletResourceTypes(
        resourceTypes = "mydevelopment/components/structure/page",
        methods = {HttpConstants.METHOD_POST,HttpConstants.METHOD_GET},
        selectors = {"dev","test"},
        extensions = {"txt","xml"}
)
@ServiceDescription("Sample Resource Type Servlet Using DS1.4/R7 Annotations")
public class CustomResourceTypeServlet extends SlingAllMethodsServlet {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomResourceTypeServlet.class);

    @Override
    protected void doGet(final SlingHttpServletRequest req, final SlingHttpServletResponse res) throws IOException {
        final Resource resource = req.getResource();
        res.setContentType("text/plain");
        res.getWriter().write("Page Title : " + resource.getValueMap().get(JcrConstants.JCR_TITLE));
    }

    @Override
    protected void doPost(final SlingHttpServletRequest req, final SlingHttpServletResponse res) throws IOException {
        try {
            LOGGER.info("\n========================POST STARTED===========================");
            List<RequestParameter> requestParameterList = req.getRequestParameterList();
            for (RequestParameter requestParameter : requestParameterList) {
                LOGGER.info("\n =====PARAMETERS=====> {} : {} ", requestParameter.getName(), requestParameter.getString());
            }
            res.setContentType("text/plain");
            res.getWriter().write("Form Submitted...");
        } catch (Exception e) {
            LOGGER.info("\n Error in request {} ", e.getMessage());
        }
    }
}
