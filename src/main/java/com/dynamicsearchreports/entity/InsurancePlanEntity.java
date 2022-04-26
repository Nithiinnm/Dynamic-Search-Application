package com.dynamicsearchreports.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "insurance_plans")
public class InsurancePlanEntity {
	
	@Id
	@GeneratedValue
	@Column(name = "planId")
	private Integer id;
	
	@Column(name = "planName")
	private String planName;
	
	@Column(name = "holderName")
	private String holderName;
	
	@Column(name = "holderSsn")
	private Long holderSsn;
	
	@Column(name = "planStatus")
	private String planStatus;
	

}
