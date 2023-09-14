package com.practice.mydevelopment.core.servlets;

import com.practice.mydevelopment.core.services.JCRSQL2SearchService;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletPaths;
import org.json.JSONObject;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.Servlet;
import java.io.IOException;

@Component(service = Servlet.class)
@SlingServletPaths(value = "/bin/sql/search")
public class JCRSQL2SearchServlet extends SlingAllMethodsServlet {

    private static final Logger LOGGER = LoggerFactory.getLogger(JCRSQL2SearchServlet.class);

    @Reference
    JCRSQL2SearchService jcrsql2SearchService;

    @Override
    protected void doGet(final SlingHttpServletRequest req, final SlingHttpServletResponse res) throws IOException {
        JSONObject searchResult = null;
        try{
            String searchPath = req.getRequestParameter("searchPath").getString();
            searchResult = jcrsql2SearchService.searchResultSQL2(searchPath);
        }catch (Exception e){
            LOGGER.info("\n Error : {} ",e.getMessage());
        }
        res.setContentType("application/json");
        res.getWriter().write(searchResult.toString());
    }
}
