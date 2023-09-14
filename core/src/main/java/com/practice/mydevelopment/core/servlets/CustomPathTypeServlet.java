package com.practice.mydevelopment.core.servlets;

import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.request.RequestParameter;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletPaths;
import org.json.JSONArray;
import org.json.JSONObject;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.propertytypes.ServiceDescription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.Servlet;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

@Component(service = Servlet.class)
@SlingServletPaths(value = {"/bin/pages","/mydevelopment/pages"})
@ServiceDescription("Sample Path Type Servlet Using DS1.4/R7 Annotations")
public class CustomPathTypeServlet extends SlingAllMethodsServlet {

    private final static Logger LOG = LoggerFactory.getLogger(CustomPathTypeServlet.class);

    @Override
    protected void doGet(final SlingHttpServletRequest req, final SlingHttpServletResponse res) throws IOException {
        final ResourceResolver resourceResolver = req.getResourceResolver();
        Page page = resourceResolver.adaptTo(PageManager.class).getPage("/content/mydevelopment/language-masters/en");
        JSONArray pagesArray = new JSONArray();
        try{
            Iterator<Page> childPages = page.listChildren();
            while(childPages.hasNext()){
                Page childpage = childPages.next();
                JSONObject pageObject = new JSONObject();
                pageObject.put(childpage.getTitle(),childpage.getPath().toString());
                pagesArray.put(pageObject);
            }
        }catch (Exception e){
            LOG.info("\n Error {} ",e.getMessage());
        }
        res.setContentType("application/json");
        res.getWriter().write(pagesArray.toString());
    }

    @Override
    protected void doPost(final SlingHttpServletRequest req, final SlingHttpServletResponse res){
        try {
            LOG.info("\n========================POST STARTED===========================");
            List<RequestParameter> requestParameterList = req.getRequestParameterList();
            for (RequestParameter requestParameter : requestParameterList) {
                LOG.info("\n =====PARAMETERS=====> {} : {} ", requestParameter.getName(), requestParameter.getString());
            }
            res.setContentType("text/plain");
            res.getWriter().write("Form Submitted...");
        } catch (Exception e) {
            LOG.info("\n Error in request {} ", e.getMessage());
        }
    }
}
