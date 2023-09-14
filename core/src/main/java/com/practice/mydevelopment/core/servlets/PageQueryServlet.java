package com.practice.mydevelopment.core.servlets;

import org.osgi.service.component.annotations.Reference;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.commons.json.JSONArray;
import org.apache.sling.commons.json.JSONObject;
import org.apache.sling.servlets.annotations.SlingServletPaths;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.search.PredicateGroup;
import com.day.cq.search.Query;
import com.day.cq.search.QueryBuilder;
import com.day.cq.search.result.Hit;
import com.day.cq.search.result.SearchResult;

import javax.jcr.Session;
import javax.servlet.Servlet;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component(service = Servlet.class)
@SlingServletPaths(value = {"/bin/generate/pagesreport"})
public class PageQueryServlet extends SlingSafeMethodsServlet {

    private static final long serialVersionUID = 1L;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Reference
    private QueryBuilder builder;

    @Reference
    private ResourceResolverFactory resolverFactory;

    @SuppressWarnings("deprecation")
    @Override
    protected void doGet(SlingHttpServletRequest request,
                         SlingHttpServletResponse response) throws IOException {

        String pageRootPath = request.getParameter("pageroot");
        if(pageRootPath == null){
            pageRootPath = "/content";
        }
        String compType = request.getParameter("comptype");
        if(compType == null){
            compType = "weretail/components/content/productgrid";
        }else{
            compType = compType.replace("/apps/", "");
            logger.info("Logging comp type is: "+compType);
        }
        long totalMatches = 0;

        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");

        JSONObject json = new JSONObject();
        JSONArray jsonarray = new JSONArray();
        JSONObject tempJson;

        try {
            ResourceResolver resolver = request.getResourceResolver();
            Session session = resolver.adaptTo(Session.class);

            Map map = new HashMap();
            map.put("path", pageRootPath);
            map.put("type", "nt:unstructured");
            map.put("property", "sling:resourceType");

            String[] patharr = compType.split(",");
            for (int i=0; i<patharr.length; i++){
                map.put("property."+i+"_value", patharr[i]);
            }
            map.put("p.offset", "0");
            map.put("p.limit", "-1");

            Query query = builder.createQuery(PredicateGroup.create(map), session);

            SearchResult result = query.getResult();
            totalMatches = result.getTotalMatches();
            String hitpath;
            Resource pageResource;
            String pagepath;
            String jcrpagepath;
            List<String> pagesList = new ArrayList<String>();
            int pagecounter = 0;
            int compcounter = 1;

            for (Hit hit : result.getHits()) {
                hitpath = hit.getPath();
                tempJson = new JSONObject();
                pagepath = hitpath.split("/jcr:content")[0];
                jcrpagepath = pagepath +"/jcr:content";
                pageResource = resolver.getResource(jcrpagepath);

                if (pageResource != null && !pagesList.contains(pagepath)) {
                    pagecounter++;
                    ValueMap valueMap = pageResource.getValueMap();
                    tempJson.put("pagename",
                            valueMap.get("jcr:title", String.class));
                    tempJson.put("pagerestype",
                            valueMap.get("sling:resourceType", String.class));
                    tempJson.put("pagetemplate",
                            valueMap.get("cq:template", String.class));
                    tempJson.put("pagepath", pagepath);
                    jsonarray.put(tempJson);
                    pagesList.add(pagepath);
                }
            }
            json.put("totalcomponents", totalMatches);
            json.put("totalpages", pagecounter);
            json.put("pages", jsonarray);
            logger.info("Total" + totalMatches + "paths were found: \n");

        } catch (Exception e) {
            logger.error("Exception caught: "+e);
        }
        response.getWriter().print(json.toString());

    }
}