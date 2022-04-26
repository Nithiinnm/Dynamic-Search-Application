package com.dynamicsearchreports.repo;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.dynamicsearchreports.entity.InsurancePlanEntity;

public interface InsurancePlanRepositary extends JpaRepository<InsurancePlanEntity, Serializable>{
	
	@Query("Select distinct planName from InsurancePlanEntity")
	public List<String> getPlanNames();
	
	@Query("Select distinct planStatus from InsurancePlanEntity")
	public List<String> getPlanStatus();

}
