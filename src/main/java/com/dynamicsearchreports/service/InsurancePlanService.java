package com.dynamicsearchreports.service;

import java.util.List;

import com.dynamicsearchreports.bindings.SearchRequest;
import com.dynamicsearchreports.bindings.SearchResponse;

public interface InsurancePlanService {
	
	public List<SearchResponse> searchPlan(SearchRequest searchRequest);

	public List<String> getPlans();
	
	public List<String> getStatus();
	
}
