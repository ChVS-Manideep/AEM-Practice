package com.practice.mydevelopment.core.services.impl;

import com.practice.mydevelopment.core.services.JCRSQL2SearchService;
import com.practice.mydevelopment.core.utils.ResolverUtil;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.json.JSONArray;
import org.json.JSONObject;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.jcr.Session;
import javax.jcr.query.Query;
import javax.jcr.query.QueryResult;

@Component(service = JCRSQL2SearchService.class,immediate = true)
public class JCRSQL2SearchServiceImpl implements JCRSQL2SearchService {

    private static final Logger LOGGER = LoggerFactory.getLogger(JCRSQL2SearchServiceImpl.class);

    @Reference
    ResourceResolverFactory resourceResolverFactory;
    @Override
    public JSONObject searchResultSQL2(String searchPath) {
        JSONObject searchResult = new JSONObject();
        try{
            String sql2Query = "SELECT * FROM [cq:PageContent] AS node WHERE ISDESCENDANTNODE ("+searchPath+") ORDER BY node.[jcr:title]";
            ResourceResolver resourceResolver = ResolverUtil.newResolver(resourceResolverFactory);
            final Session session = resourceResolver.adaptTo(Session.class);
            final javax.jcr.query.Query query = session.getWorkspace().getQueryManager().createQuery(sql2Query, Query.JCR_SQL2);
            final QueryResult result = query.execute();
            NodeIterator pages = result.getNodes();
            JSONArray resultsArray = new JSONArray();
            while (pages.hasNext()){
                Node page = pages.nextNode();
                if(page.hasProperty("jcr:title")){
                    JSONObject resultObject = new JSONObject();
                    resultObject.put("tilte",page.getProperty("jcr:title").getString());
                    resultObject.put("name",page.getName());
                    resultObject.put("created",page.getProperty("jcr:created").getString());
                    resultsArray.put(resultObject);
                }
            }
            searchResult.put("Pages",resultsArray);
        }catch (Exception e){
            LOGGER.info("\n Error Occured {} ",e.getMessage());
        }
        return searchResult;
    }
}
