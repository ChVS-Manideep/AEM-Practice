package com.practice.mydevelopment.core.services;

import org.json.JSONObject;

public interface QueryBuilderSearchService {
    public JSONObject searchResult(String searchText, int startResult, int resultPerPage);
}
