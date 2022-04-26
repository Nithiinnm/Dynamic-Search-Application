package com.dynamicsearchreports.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dynamicsearchreports.bindings.SearchRequest;
import com.dynamicsearchreports.bindings.SearchResponse;
import com.dynamicsearchreports.reports.InsuranceplanExcelGenerator;
import com.dynamicsearchreports.reports.PdfReportGenerator;
import com.dynamicsearchreports.service.InsurancePlanService;

@RestController
//@RequestMapping(name = "/dynamicSearch")
public class InsurancePlanController {

	@Autowired
	private InsurancePlanService planService;
	
	
	
	@GetMapping("/excel")
	public void generateExcel(HttpServletResponse response) throws Exception {
		
		response.setContentType("application/octet-stream");
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormatter.format(new Date());

		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=plans_" + currentDateTime + ".xlsx";
		response.setHeader(headerKey, headerValue);
		
		List<SearchResponse> searchPlans = planService.searchPlan(null);
		
		InsuranceplanExcelGenerator exportExcel = new InsuranceplanExcelGenerator();
		//excelGenerator.export(searchPlans, response);
		exportExcel.export(searchPlans, response);
	}
	
	
	@GetMapping("/pdf")
	public void generatePdf(HttpServletResponse response) throws Exception {
		
		response.setContentType("application/pdf");
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormatter.format(new Date());

		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=plans_" + currentDateTime + ".pdf";
		response.setHeader(headerKey, headerValue);
		
		List<SearchResponse> searchPlans = planService.searchPlan(null);
		//System.out.println("SearchPlans :"+ searchPlans);
		
		PdfReportGenerator exportExcel = new PdfReportGenerator();
		//excelGenerator.export(searchPlans, response);
		exportExcel.exportPdf(searchPlans, response);
	}
	
	@PostMapping("/searchPlans")
	public ResponseEntity<List<SearchResponse>> getSearchPlans(@RequestBody SearchRequest request){
		 List<SearchResponse> searchPlan = planService.searchPlan(request);
		 return new ResponseEntity(searchPlan, HttpStatus.OK);
		
	}
	
	@GetMapping("/getPlans") 
	public ResponseEntity<List<String>> getPlanNames() {
		List<String> plans = planService.getPlans();
		return new ResponseEntity<List<String>>(plans, HttpStatus.OK);
	}
	
	@GetMapping("/getStatus") 
	public ResponseEntity<List<String>> getPlanStatus() {
		List<String> status = planService.getStatus();
		return new ResponseEntity<List<String>>(status, HttpStatus.OK);
	}
}
