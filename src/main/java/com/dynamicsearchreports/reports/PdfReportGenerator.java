package com.dynamicsearchreports.reports;

import java.awt.Color;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.dynamicsearchreports.bindings.SearchResponse;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

public class PdfReportGenerator {
	
	public void exportPdf(List<SearchResponse> plans,HttpServletResponse response) throws DocumentException, IOException {
		
		Document document = new Document(PageSize.A4);
		PdfWriter.getInstance(document, response.getOutputStream());
		document.open();
		
		Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
		font.setSize(18);
		font.setColor(Color.BLUE);
		
		Paragraph p = new Paragraph("List of Users", font);
		p.setAlignment(Paragraph.ALIGN_CENTER);
		
		document.add(p);
	// Creating Table with Row(5) here we need 5 rows	
		PdfPTable table = new PdfPTable(5);
		table.setWidthPercentage(100f);
		table.setWidths(new float[] {1.0f, 1.0f, 2.0f, 2.0f, 1.5f});
		table.setSpacingBefore(10);
		
	// Creating Header	
		PdfPCell cell = new PdfPCell();
		cell.setBackgroundColor(Color.BLUE);
		cell.setPadding(5);
		
		Font headerFont = FontFactory.getFont(FontFactory.HELVETICA);
		headerFont.setColor(Color.WHITE);
		
		cell.setPhrase(new Phrase("Plan ID", headerFont));
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("Plan Name", headerFont));
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("Holder Name", headerFont));
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("SSN", headerFont));
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("Status", headerFont));
		table.addCell(cell);
		
		// Creating Data Row
		
		for(SearchResponse plan : plans) {
			
			table.addCell(plan.getId().toString());
			table.addCell(plan.getPlanName());
			table.addCell(plan.getHolderName());
			table.addCell(plan.getHolderSsn().toString());
			table.addCell(plan.getPlanStatus());
		}
		
		document.add(table);
		document.close();
	}

}
