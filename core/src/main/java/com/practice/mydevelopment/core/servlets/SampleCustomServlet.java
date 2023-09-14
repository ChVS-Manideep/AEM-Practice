package com.practice.mydevelopment.core.servlets;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.request.RequestParameter;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.propertytypes.ServiceDescription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.Servlet;

import java.io.IOException;
import java.util.List;

import static org.apache.sling.api.servlets.ServletResolverConstants.*;

@Component(service = Servlet.class,
        property = {
                "sling.servlet.methods=" + HttpConstants.METHOD_GET,
                SLING_SERVLET_METHODS + "=" + HttpConstants.METHOD_POST,
                "sling.servlet.resourceTypes=" + "mydevelopment/components/structure/page",
                SLING_SERVLET_PATHS+"="+"/mydevelopment/r5annots",
                "sling.servlet.selectors=" + "custom",
                SLING_SERVLET_SELECTORS + "=" + "dev",
                "sling.servlet.extensions=" + "xml",
                SLING_SERVLET_EXTENSIONS + "=" + "txt"
        })
@ServiceDescription("Servlet(ResourceType & Path type) using DS1.2/R5 Annotations")
public class SampleCustomServlet extends SlingAllMethodsServlet {
    private static final Logger log = LoggerFactory.getLogger(SampleCustomServlet.class);

    @Override
    protected void doGet(final SlingHttpServletRequest req, final SlingHttpServletResponse res) throws IOException {
        final ResourceResolver resourceResolver = req.getResourceResolver();
        String reqSelectors = "SELECTORS => ";
        String reqExtensions = req.getRequestPathInfo().getExtension();
        try {
            String[] selectors = req.getRequestPathInfo().getSelectors();
            for (String selector : selectors) {
                reqSelectors = reqSelectors + " " + selector;
            }
        } catch (Exception e) {
            log.info("\n Error {} ", e.getMessage());
        }
        res.setContentType("application/json");
        res.getWriter().write(reqSelectors + " # " + reqExtensions);
    }

    @Override
    protected void doPost(final SlingHttpServletRequest req, final SlingHttpServletResponse res) {
        try {
            log.info("\n========================POST STARTED===========================");
            List<RequestParameter> requestParameterList = req.getRequestParameterList();
            for (RequestParameter requestParameter : requestParameterList) {
                log.info("\n =====PARAMETERS=====> {} : {} ", requestParameter.getName(), requestParameter.getString());
            }
            res.setContentType("text/plain");
            res.getWriter().write("Form Submitted...");
        } catch (Exception e) {
            log.info("\n Error in request {} ", e.getMessage());
        }
    }
}

