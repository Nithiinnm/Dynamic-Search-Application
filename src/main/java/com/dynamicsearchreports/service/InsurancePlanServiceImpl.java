package com.dynamicsearchreports.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.dynamicsearchreports.bindings.SearchRequest;
import com.dynamicsearchreports.bindings.SearchResponse;
import com.dynamicsearchreports.entity.InsurancePlanEntity;
import com.dynamicsearchreports.repo.InsurancePlanRepositary;

@Service
public class InsurancePlanServiceImpl implements InsurancePlanService {

	@Autowired
	private InsurancePlanRepositary planRepo;
	
	@Override
	public List<SearchResponse> searchPlan(SearchRequest request) {
		
		InsurancePlanEntity entity = new InsurancePlanEntity();
		
		if(request !=null && request.getPlanName() != null && !request.getPlanName().equals("")) {
			entity.setPlanName(request.getPlanName());
			System.out.println("PlanName Entity :"+ entity);
		}
		
		if(request !=null && request.getPlanStatus() != null && !request.getPlanStatus().equals("")) {
			entity.setPlanStatus(request.getPlanStatus());
			System.out.println("PlanStatus Entity :"+ entity);
		}
		
		Example<InsurancePlanEntity> of = Example.of(entity);
		List<InsurancePlanEntity> listofPlans = planRepo.findAll(of);
		System.out.println("List of Plans :" + listofPlans);
		List<SearchResponse> response = new ArrayList<>();
		
		for(InsurancePlanEntity plan : listofPlans) {
			
			SearchResponse planResponse = new SearchResponse();
			BeanUtils.copyProperties(plan, planResponse);
			response.add(planResponse);
			//System.out.println("Response :"+response);
		}
		return response;
	}

	@Override
	public List<String> getPlans() {
		return planRepo.getPlanNames();
	}

	@Override
	public List<String> getStatus() {
		return planRepo.getPlanStatus();
	}

}
