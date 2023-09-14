package com.practice.mydevelopment.core.services.impl;

import com.day.cq.search.PredicateGroup;
import com.day.cq.search.Query;
import com.day.cq.search.QueryBuilder;
import com.day.cq.search.result.Hit;
import com.day.cq.search.result.SearchResult;
import com.day.cq.wcm.api.Page;
import com.practice.mydevelopment.core.services.QueryBuilderSearchService;
import com.practice.mydevelopment.core.utils.ResolverUtil;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.json.JSONArray;
import org.json.JSONObject;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jcr.Session;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component(service = QueryBuilderSearchService.class,immediate = true)
public class QueryBuilderSearchServiceImpl implements QueryBuilderSearchService {

    private static final Logger LOGGER = LoggerFactory.getLogger(QueryBuilderSearchServiceImpl.class);

    @Reference
    QueryBuilder queryBuilder;

    @Reference
    ResourceResolverFactory resourceResolverFactory;

    @Activate
    public void activate(){
        LOGGER.info("\n==============Activate Method===================");
    }

    public Map<String,String> createTextSearchQuery(String searchText,int startResult,int resultPerPage){
        Map<String,String> queryMap = new HashMap<>();
        queryMap.put("path","/content/mydevelopment");
        queryMap.put("type","cq:Page");
        queryMap.put("fulltext",searchText);
        queryMap.put("p.offset",Long.toString(startResult));
        queryMap.put("p.limit",Long.toString(resultPerPage));
        return queryMap;
    }

    @Override
    public JSONObject searchResult(String searchText, int startResult, int resultPerPage) {
        LOGGER.info("\n===========Search Result===============");
        JSONObject searchResult = new JSONObject();
        try{
            ResourceResolver resourceResolver = ResolverUtil.newResolver(resourceResolverFactory);
            final Session session = resourceResolver.adaptTo(Session.class);
            Query query = queryBuilder.createQuery(PredicateGroup.create(createTextSearchQuery(searchText,startResult,resultPerPage)),session);

            SearchResult result = query.getResult();

            int perPageResults = result.getHits().size();
            long totalResults = result.getTotalMatches();
            long startingResult = result.getStartIndex();
            double totalPages = Math.ceil((double) totalResults / (double) resultPerPage);

            searchResult.put("perpageresult",perPageResults);
            searchResult.put("totalresults",totalResults);
            searchResult.put("startingresult",startingResult);
            searchResult.put("pages",totalPages);

            List<Hit> hits = result.getHits();
            JSONArray resultsArray = new JSONArray();
            for(Hit hit : hits){
                Page page = hit.getResource().adaptTo(Page.class);
                JSONObject resultObject = new JSONObject();
                resultObject.put("title",page.getTitle());
                resultObject.put("path",page.getPath());
                resultsArray.put(resultObject);
                LOGGER.info("\n Page {}",page.getPath());
            }
            searchResult.put("results",resultsArray);
        }catch (Exception e){
            LOGGER.info("\nError Occured : {}",e.getMessage());
        }
        return searchResult;
    }
}
