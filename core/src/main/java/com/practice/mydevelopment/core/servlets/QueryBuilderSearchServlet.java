package com.practice.mydevelopment.core.servlets;

import com.practice.mydevelopment.core.services.QueryBuilderSearchService;
import com.practice.mydevelopment.core.services.impl.QueryBuilderSearchServiceImpl;
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
@SlingServletPaths(value = "/bin/search")
public class QueryBuilderSearchServlet extends SlingAllMethodsServlet {

    private static final Logger LOGGER = LoggerFactory.getLogger(QueryBuilderSearchServlet.class);

    @Reference
    QueryBuilderSearchService queryBuilderSearchService;

    @Override
    protected void doGet(final SlingHttpServletRequest req, final SlingHttpServletResponse res) throws IOException {
        JSONObject searchResult = null;
        try{
            String searchtext = req.getRequestParameter("searchText").getString();
            int pageNumber = Integer.parseInt(req.getRequestParameter("pageNumber").getString())-1;
            int resultPerPage = Integer.parseInt(req.getRequestParameter("resultPerPage").getString());
            int startResult = pageNumber * resultPerPage;
            searchResult = queryBuilderSearchService.searchResult(searchtext,startResult,resultPerPage);
        }catch (Exception e){
            LOGGER.info("\n Error occured : {} ",e.getMessage());
        }
        res.setContentType("application/json");
        res.getWriter().write(searchResult.toString());
    }
}
