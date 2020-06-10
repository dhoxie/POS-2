package com.scottsdaleair.pdfGenerator;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.border.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.property.TextAlignment;


public class PDFReport {
  private Document theDocument;
  private PdfDocument pdf;

  public PDFReport(){
    //makes a report of all of the invoices in the database. 

  }

  public PDFReport start() throws Exception{
    PdfWriter writer =
        new PdfWriter("SalesReport.pdf");
    pdf = new PdfDocument(writer);
    theDocument = new Document(pdf);

    createHeader();
    createTableHeader();
    done();
    return this;
  }

  private void createHeader(){
    Text starLine = new Text("************************************************"
        + "****************************************************************\n");
    Paragraph dataLine = new Paragraph("The date Sales Report From date To date ");
    dataLine.setTextAlignment(TextAlignment.CENTER);
    theDocument.add(new Paragraph(starLine));
    theDocument.add(dataLine);
    theDocument.add(new Paragraph(starLine));
    Paragraph salesBreakdown = new Paragraph("Sales Breakdown \n =============");
    salesBreakdown.setTextAlignment(TextAlignment.CENTER);
    theDocument.add(salesBreakdown);
  }

  private void done() {
    theDocument.close();
  }

  private void createTableHeader(){
    Table tmp = new Table(6);
    tmp.setFontSize(8);
    Cell categoryCell = new Cell(1,1).setBorder(Border.NO_BORDER).add("Category");
    Cell partsCell = new Cell(1,1).setBorder(Border.NO_BORDER).add("Parts");
    Cell laborCell = new Cell(1,1).setBorder(Border.NO_BORDER).add("Labor");
    Cell totalCell = new Cell(1,1).setBorder(Border.NO_BORDER).add("Total");
    Cell costCell = new Cell(1,1).setBorder(Border.NO_BORDER).add("Cost");
    Cell profitCell = new Cell(1,1).setBorder(Border.NO_BORDER).add("Profit");
    Cell equalsCell = new Cell(1,6).setBorder(Border.NO_BORDER).add("========"+
        "==================================================================" +
        "======================================");
    tmp.addCell(categoryCell);
    tmp.addCell(partsCell);
    tmp.addCell(laborCell);
    tmp.addCell(totalCell);
    tmp.addCell(costCell);
    tmp.addCell(profitCell);
    tmp.addCell(equalsCell);
    theDocument.add(tmp);


  }

}