package com.dynamicsearchreports.reports;

import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.dynamicsearchreports.bindings.SearchResponse;

//@Component
public class InsuranceplanExcelGenerator {
	
	public void export(List<SearchResponse> plans, HttpServletResponse response) throws Exception {
		
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("Plans");
		
		XSSFRow headerRow = sheet.createRow(0);
		
//		XSSFCell cell0 = headerRow.createCell(0);
//		cell0.setCellValue("PlanName");
//		XSSFCell cell1 = headerRow.createCell(1);
//		cell1.setCellValue("HolderName");
//		XSSFCell cell2 = headerRow.createCell(2);
//		cell2.setCellValue("HolderSsn");
//		XSSFCell cell3 = headerRow.createCell(3);
//		cell3.setCellValue("PlanStatus");
		
		headerRow.createCell(0).setCellValue("PlanName");
		headerRow.createCell(1).setCellValue("HolderName");
		headerRow.createCell(2).setCellValue("HolderSsn");
		headerRow.createCell(3).setCellValue("PlanStatus");
		
		for(int i=0; i< plans.size(); i++) {
			
			SearchResponse plan = plans.get(i);
			XSSFRow dataRow = sheet.createRow(i+1);
			
//			XSSFCell dcell0 = dataRow.createCell(0);
//			dcell0.setCellValue(plan.getPlanName());
//			
//			XSSFCell dcell1 = dataRow.createCell(1);
//			dcell1.setCellValue(plan.getHolderName());
//			
//			XSSFCell dcell2 = dataRow.createCell(2);
//			dcell2.setCellValue(plan.getHolderSsn());
//			
//			XSSFCell dcell3 = dataRow.createCell(3);
//			dcell3.setCellValue(plan.getPlanStatus());
			
			dataRow.createCell(0).setCellValue(plan.getPlanName());
			dataRow.createCell(1).setCellValue(plan.getHolderName());
			dataRow.createCell(2).setCellValue(plan.getHolderSsn());
			dataRow.createCell(3).setCellValue(plan.getPlanStatus());
		}
		
		ServletOutputStream outputStream = response.getOutputStream();
		workbook.write(outputStream);
		workbook.close();
		outputStream.close();
		
	}
}
