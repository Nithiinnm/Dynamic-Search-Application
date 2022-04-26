package com.dynamicsearchreports.bindings;

import lombok.Data;

@Data
public class SearchResponse {
	
	private Integer Id;
	private String planName;
	private String holderName;
	private Long holderSsn;
	private String planStatus;

}
