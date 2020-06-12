package com.scottsdaleair.pdfGenerator;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.border.Border;
import com.itextpdf.layout.border.SolidBorder;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.property.Leading;
import com.itextpdf.layout.property.Property;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.UnitValue;
import com.scottsdaleair.data.Customer;
import com.scottsdaleair.data.Invoice;
import com.scottsdaleair.data.Part;
import com.scottsdaleair.data.Vehicle;
import java.io.FileNotFoundException;

/**
 * A class to create a PDF out of an Invoice object.
 * 
 * @author Spencer Curley
 */
public class PDFInvoice {
  private Invoice theInvoice;
  private Customer theCust;
  private Vehicle theVehicle;
  private Document theDocument;
  private PdfDocument pdf;
  private Table partialTable;
  private CellFactory theCellFactory;

  /**
   * Create a PDFInvoice object from an Invoice object.
   * 
   * @param theInvoice The invoice to be used
   */
  public PDFInvoice(Invoice theInvoice) {
    this.theInvoice = theInvoice;
    theCust = Customer.getFromDb(theInvoice.getCustomerID());
    theVehicle = Vehicle.getFromDb(theInvoice.getVehicleVin());
    theCellFactory = new CellFactory();
  }

  /**
   * used for creating production pdfs.
   * 
   * @return this object
   * @throws FileNotFoundException if pdf file cannot be found
   */
  public PDFInvoice start() throws FileNotFoundException {
    // this is of type PDFTest so if someone wants to create this class and
    // create a pdf at the same time it can be done with object chaining
    PdfWriter writer =
        new PdfWriter(theInvoice.getId() + theCust.getFname() + theCust.getLname() + ".pdf");
    pdf = new PdfDocument(writer);
    theDocument = new Document(pdf);
    // all of the below function calls should return stuff so that the adding to the
    // pdf is done here to make it easy to unit test
    createHeader();
    createCustInfoHeader();
    createPartsAndPriceHeader();
    String[] theParts = theInvoice.getParts();
    partialTable = new Table((UnitValue.createPercentArray(new float[] {2, 16, 1, 1, 1, 1})));
    partialTable.setWidthPercent(100);
    for (int i = 0; i < theInvoice.getParts().length; i++) {
      addPart(Part.getFromDb(theParts[i]));
      // need a way to get parts , service , Vehicle not just the primary keys of the
      // data
    }
    theDocument.add(partialTable);

    addNotesHeader();
    addNotes();
    addTotalSection();
    done();

    return this;
  }

  /**
   * Closes and creates the pdf.
   */
  private void done() {
    theDocument.close();
  }

  /**
   * Creates the header.
   * 
   * @return return is for junit
   */
  public Document createHeader() {
    Table table = new Table(3);
    theDocument.setProperty(Property.LEADING, new Leading(Leading.MULTIPLIED, .8f));
    table.setFontSize(8);
    Cell invoiceNumberCell = new Cell(1, 1).setTextAlignment(TextAlignment.LEFT)
        .add(new Paragraph("Invoice # \n" + theInvoice.getId())).setBorder(Border.NO_BORDER);
    Cell whoWeAreCell =
        new Cell(1, 1).setTextAlignment(TextAlignment.CENTER)
            .add(new Paragraph("NORTHWEST AUTOMOTIVE CENTERS \n "
                + "324 North Pines \n Spokane, WA 99216 \n 509-922-2006"))
            .setBorder(Border.NO_BORDER);
    Cell dateCell = new Cell(1, 1).setTextAlignment(TextAlignment.RIGHT)
        .add(new Paragraph(theInvoice.getDate())).setBorder(Border.NO_BORDER);
    table.addCell(invoiceNumberCell);
    table.addCell(whoWeAreCell);
    table.addCell(dateCell);
    Text starLine = new Text("************************************************"
        + "****************************************************************\n");
    theDocument.add(new Paragraph(starLine));
    theDocument.add(table);
    theDocument.add(new Paragraph(starLine));
    return theDocument;
  }

  /**
   * creates the customerinfo name address and vehicle info etc....
   * 
   * @return is for junit only
   */
  public Document createCustInfoHeader() {
    Table table = new Table(3);
    table.setFontSize(8);
    Cell lastNameCellText = new Cell(1, 1).setTextAlignment(TextAlignment.RIGHT)
        .setBorder(Border.NO_BORDER).add(new Paragraph("LNAME"));
    Cell lastNameCellData = new Cell(1, 1).setTextAlignment(TextAlignment.LEFT)
        .setBorder(Border.NO_BORDER).add(new Paragraph(theCust.getLname()));
    Cell firstNameCellText = new Cell(1, 1).setTextAlignment(TextAlignment.RIGHT)
        .setBorder(Border.NO_BORDER).add(new Paragraph("FNAME"));
    Cell firstCellData = new Cell(1, 1).setTextAlignment(TextAlignment.LEFT)
        .setBorder(Border.NO_BORDER).add(new Paragraph(theCust.getFname()));
    Cell addressCellText = new Cell(1, 1).setTextAlignment(TextAlignment.RIGHT)
        .setBorder(Border.NO_BORDER).add(new Paragraph("ADDRESS"));
    Cell addressCellData = new Cell(1, 1).setTextAlignment(TextAlignment.LEFT)
        .setBorder(Border.NO_BORDER).add(new Paragraph(theCust.getAddress()));
    Cell cityStateCellText = new Cell(1, 1).setTextAlignment(TextAlignment.RIGHT)
        .setBorder(Border.NO_BORDER).add(new Paragraph("CY/ST"));
    Cell cityStateCellData = new Cell(1, 1).setTextAlignment(TextAlignment.LEFT)
        .setBorder(Border.NO_BORDER).add(new Paragraph("City State here"));
    Cell techCellText = new Cell(1, 1).setTextAlignment(TextAlignment.RIGHT)
        .setBorder(Border.NO_BORDER).add(new Paragraph("TECH"));
    Cell techCellData = new Cell(1, 1).setTextAlignment(TextAlignment.LEFT)
        .setBorder(Border.NO_BORDER).add(new Paragraph("NW"));
    Cell managerCellText = new Cell(1, 1).setTextAlignment(TextAlignment.RIGHT)
        .setBorder(Border.NO_BORDER).add(new Paragraph("MANAGER"));
    Cell managerCellData = new Cell(1, 1).setTextAlignment(TextAlignment.LEFT)
        .setBorder(Border.NO_BORDER).add(new Paragraph("NW"));
    // created all of the cell data
    // adding to an inner table
    Table innerTableFirst = new Table(new float[] {5, 1, 5})
        .setHorizontalAlignment(HorizontalAlignment.CENTER).setPadding(0);
    innerTableFirst.addCell(firstNameCellText);
    innerTableFirst.addCell(theCellFactory.getColon());
    innerTableFirst.addCell(firstCellData);
    innerTableFirst.addCell(lastNameCellText);
    innerTableFirst.addCell(theCellFactory.getColon());
    innerTableFirst.addCell(lastNameCellData);
    innerTableFirst.addCell(addressCellText);
    innerTableFirst.addCell(theCellFactory.getColon());
    innerTableFirst.addCell(addressCellData);
    innerTableFirst.addCell(cityStateCellText);
    innerTableFirst.addCell(theCellFactory.getColon());
    innerTableFirst.addCell(cityStateCellData);
    innerTableFirst.addCell(techCellText);
    innerTableFirst.addCell(theCellFactory.getColon());
    innerTableFirst.addCell(techCellData);
    innerTableFirst.addCell(managerCellText);
    innerTableFirst.addCell(theCellFactory.getColon());
    innerTableFirst.addCell(managerCellData);

    Cell phoneCellText = new Cell(1, 1).setTextAlignment(TextAlignment.RIGHT)
        .setBorder(Border.NO_BORDER).add(new Paragraph("HPHONE"));
    Cell phoneCellData = new Cell().setTextAlignment(TextAlignment.LEFT).setBorder(Border.NO_BORDER)
        .add(new Paragraph(theCust.getPhones()[0].getNum()));
    Cell workPhoneCellText = new Cell(1, 1).setTextAlignment(TextAlignment.RIGHT)
        .setBorder(Border.NO_BORDER).add(new Paragraph("WPHONE"));
    Cell workPhoneCellData = new Cell().setTextAlignment(TextAlignment.LEFT)
        .setBorder(Border.NO_BORDER).add(new Paragraph(theCust.getPhones()[0].getNum()));
    Cell zipCellText = new Cell(1, 1).setTextAlignment(TextAlignment.RIGHT)
        .setBorder(Border.NO_BORDER).add(new Paragraph("ZIP"));
    Cell zipCellData = new Cell().setTextAlignment(TextAlignment.LEFT).setBorder(Border.NO_BORDER)
        .add(new Paragraph("zip here"));

    Table innerTable = new Table(new float[] {5, 1, 5})
        .setHorizontalAlignment(HorizontalAlignment.CENTER).setPadding(0);
    innerTable.addCell(phoneCellText);
    innerTable.addCell(theCellFactory.getColon());
    innerTable.addCell(phoneCellData);
    innerTable.addCell(workPhoneCellText);
    innerTable.addCell(theCellFactory.getColon());
    innerTable.addCell(workPhoneCellData);
    innerTable.addCell(zipCellText);
    innerTable.addCell(theCellFactory.getColon());
    innerTable.addCell(zipCellData);

    Cell manufactorCellText = new Cell(1, 1).setTextAlignment(TextAlignment.RIGHT)
        .setBorder(Border.NO_BORDER).add(new Paragraph("MANUF"));
    Cell manufactorCellData = new Cell().setTextAlignment(TextAlignment.LEFT)
        .setBorder(Border.NO_BORDER).add(new Paragraph(theVehicle.getMake()));
    Cell tagCellText = new Cell(1, 1).setTextAlignment(TextAlignment.RIGHT)
        .setBorder(Border.NO_BORDER).add(new Paragraph("TAG"));
    Cell tagCellData = new Cell(1, 1).setTextAlignment(TextAlignment.LEFT)
        .setBorder(Border.NO_BORDER).add(new Paragraph(theVehicle.getPlate()));
    Cell yearCellText = new Cell(1, 1).setTextAlignment(TextAlignment.RIGHT)
        .setBorder(Border.NO_BORDER).add(new Paragraph("YEAR"));
    Cell yearCellData = new Cell(1, 1).setTextAlignment(TextAlignment.LEFT)
        .setBorder(Border.NO_BORDER).add(new Paragraph(theVehicle.getYear()));
    Cell makeCellText = new Cell(1, 1).setTextAlignment(TextAlignment.RIGHT)
        .setBorder(Border.NO_BORDER).add(new Paragraph("MAKE"));
    Cell makeCellData = new Cell(1, 1).setTextAlignment(TextAlignment.LEFT)
        .setBorder(Border.NO_BORDER).add(new Paragraph(theVehicle.getMake()));

    Cell modelCellText = new Cell(1, 1).setTextAlignment(TextAlignment.RIGHT)
        .setBorder(Border.NO_BORDER).add(new Paragraph("MODEL"));
    Cell modelCellData = new Cell(1, 1).setTextAlignment(TextAlignment.LEFT)
        .setBorder(Border.NO_BORDER).add(new Paragraph(theVehicle.getModel()));
    Cell mileageCellText = new Cell(1, 1).setTextAlignment(TextAlignment.RIGHT)
        .setBorder(Border.NO_BORDER).add(new Paragraph("MILEAGE"));
    Cell mileageCellData = new Cell(1, 1).setTextAlignment(TextAlignment.LEFT)
        .setBorder(Border.NO_BORDER).add(new Paragraph(theVehicle.getMileage()));
    Cell vinCellText = new Cell(1, 1).setTextAlignment(TextAlignment.RIGHT)
        .setBorder(Border.NO_BORDER).add(new Paragraph("VIN"));
    Cell vinCellData = new Cell(1, 1).setTextAlignment(TextAlignment.LEFT)
        .setBorder(Border.NO_BORDER).add(new Paragraph(theVehicle.getVin()));

    Table innerTableLast = new Table(new float[] {5, 1, 5})
        .setHorizontalAlignment(HorizontalAlignment.CENTER).setPadding(0);
    innerTableLast.addCell(tagCellText);
    innerTableLast.addCell(theCellFactory.getColon());
    innerTableLast.addCell(tagCellData);
    innerTableLast.addCell(yearCellText);
    innerTableLast.addCell(theCellFactory.getColon());
    innerTableLast.addCell(yearCellData);
    innerTableLast.addCell(makeCellText);
    innerTableLast.addCell(theCellFactory.getColon());
    innerTableLast.addCell(makeCellData);
    innerTableLast.addCell(modelCellText);
    innerTableLast.addCell(theCellFactory.getColon());
    innerTableLast.addCell(modelCellData);
    innerTableLast.addCell(manufactorCellText);
    innerTableLast.addCell(theCellFactory.getColon());
    innerTableLast.addCell(manufactorCellData);
    innerTableLast.addCell(mileageCellText);
    innerTableLast.addCell(theCellFactory.getColon());
    innerTableLast.addCell(mileageCellData);
    innerTableLast.addCell(vinCellText);
    innerTableLast.addCell(theCellFactory.getColon());
    innerTableLast.addCell(vinCellData);


    Cell phoneCell = new Cell(1, 1).setBorder(Border.NO_BORDER)
        .setHorizontalAlignment(HorizontalAlignment.CENTER).add(innerTable);

    Cell nameAddrCell =
        new Cell(1, 1).setTextAlignment(TextAlignment.LEFT).setBorder(Border.NO_BORDER)
            // .add(new Paragraph("LNAME: the name \nFNAME: the name \nADRES: the address
            // here \nCY/ST: the city state here \nTECH: NW \nMANAGER: NW"));
            .add(innerTableFirst);
    Cell vehicleInfo = new Cell(1, 1).setTextAlignment(TextAlignment.RIGHT)
        // .add(new Paragraph("TAG: plate \nYR: 9999 MAKE: the make\nMODEL: the MODEL
        // \nMILEAGE: 123412\nVIN: 14512451245"))
        .setBorder(Border.NO_BORDER).add(innerTableLast);
    table.addCell(nameAddrCell);
    table.addCell(phoneCell);
    table.addCell(vehicleInfo);
    theDocument.add(table);
    return theDocument;
  }

  /**
   * Creates part that ays partNum Description and price infro.
   * 
   * @return is for junit
   */
  public Document createPartsAndPriceHeader() {
    Table bottomTable = new Table(1);
    Table table = new Table((UnitValue.createPercentArray(new float[] {2, 16, 1, 1, 1, 1})));
    // table.setWidthPercent(100);
    table.setFontSize(8);

    Cell topCell = new Cell(1, 1).setBorder(new SolidBorder(1f)).setBorderBottom(Border.NO_BORDER)
        .setBorderLeft(Border.NO_BORDER).setBorderRight(Border.NO_BORDER);
    bottomTable.addCell(topCell);
    Cell partCell = new Cell(1, 1).setBorder(Border.NO_BORDER).add(new Paragraph("partNum"));
    Cell descriptionCell =
        new Cell(1, 1).setBorder(Border.NO_BORDER).add(new Paragraph("Description"));
    Cell quantityCell = new Cell(1, 1).setBorder(Border.NO_BORDER).add(new Paragraph("qty"));
    Cell priceCell = new Cell(1, 1).setBorder(Border.NO_BORDER).add(new Paragraph("price"));
    Cell discCell = new Cell(1, 1).setBorder(Border.NO_BORDER).add(new Paragraph("DISC"));
    Cell totalCell = new Cell(1, 1).setBorder(Border.NO_BORDER).add(new Paragraph("TOTAL"));
    table.addCell(partCell);
    table.addCell(descriptionCell);
    table.addCell(quantityCell);
    table.addCell(priceCell);
    table.addCell(discCell);
    table.addCell(totalCell);
    theDocument.add(table);
    theDocument.add(bottomTable);
    table.setWidthPercent(100);
    return theDocument;

  }

  /**
   * Adds the notes header.
   * 
   * @return for junit only
   */
  public Document addNotesHeader() {
    Table bottomTable = new Table(1);
    Cell topCell = new Cell(1, 1).setBorder(new SolidBorder(1f)).setBorderBottom(Border.NO_BORDER)
        .setBorderLeft(Border.NO_BORDER).setBorderRight(Border.NO_BORDER);
    bottomTable.addCell(topCell);
    theDocument.add(new Paragraph("Notes").setFontSize(8));
    theDocument.add(bottomTable);
    return theDocument;
  }

  /**
   * Adds the param to the notes.
   * 
   * @return
   */
  public Document addNotes() {
    theDocument.add(new Paragraph(theInvoice.getPubNotes() + "\n this not written by me")
        .setFontSize(8).setBorder(new SolidBorder(1f)).setPadding(1));
    return theDocument;
  }

  /**
   * Adds the part to the description part in the pdf.
   * 
   * @param toAdd Part to add to the document
   * @return the document for unit testing purposes
   */
  public Document addPart(Part toAdd) {

    partialTable.setFontSize(8);

    partialTable.addCell(new Cell(1, 1).add(new Paragraph(toAdd.getPartID())));
    partialTable.addCell(new Cell(1, 1).add(new Paragraph(toAdd.getVendor())));
    partialTable.addCell(new Cell(1, 1).add(new Paragraph("inc")));
    partialTable.addCell(new Cell(1, 1).add(new Paragraph(toAdd.getPrice())));
    partialTable.addCell(new Cell(1, 1).add(new Paragraph("disco")));
    partialTable.addCell(new Cell(1, 1).add(new Paragraph("total")));

    return theDocument;
  }

  /**
   * Adds the total section.
   */
  public void addTotalSection() {
    Table table = new Table(2);
    Cell partsCellText = new Cell(1, 1).setBorder(Border.NO_BORDER).add(new Paragraph("Parts"));
    Cell partsCellValue =
        new Cell(1, 1).setBorder(Border.NO_BORDER).add(new Paragraph("calculated "));
    Cell laborCellText = new Cell(1, 1).setBorder(Border.NO_BORDER).add(new Paragraph("Labor"));
    Cell laborCellValue =
        new Cell(1, 1).setBorder(Border.NO_BORDER).add(new Paragraph("calculated"));
    Cell subtotalCellText =
        new Cell(1, 1).setBorder(Border.NO_BORDER).add(new Paragraph("subtotal"));
    Cell subtotalCellValue =
        new Cell(1, 1).setBorder(Border.NO_BORDER).add(new Paragraph("calculated"));
    Cell shopFeeCellText =
        new Cell(1, 1).setBorder(Border.NO_BORDER).add(new Paragraph("Shop Fee"));
    Cell shopFeeCellValue =
        new Cell(1, 1).setBorder(Border.NO_BORDER).add(new Paragraph("calculated"));
    Cell totalCellText = new Cell(1, 1).setBorder(Border.NO_BORDER).add(new Paragraph("Total"));
    Cell totalCellValue =
        new Cell(1, 1).setBorder(Border.NO_BORDER).add(new Paragraph("calculated"));
    Cell paidCellText =
        new Cell(1, 1).setBorder(Border.NO_BORDER).add(new Paragraph("paymentmethod"));
    // ???? what is this
    Cell paidCellValue =
        new Cell(1, 1).setBorder(Border.NO_BORDER).add(new Paragraph("finaltotal"));
    Cell lineCell =
        new Cell(1, 2).setBorder(Border.NO_BORDER).add(new Paragraph("----------------------"));
    table.setFixedPosition(490, theDocument.getBottomMargin(), 80);
    table.addCell(partsCellText);
    table.addCell(partsCellValue);
    table.addCell(laborCellText);
    table.addCell(laborCellValue);
    table.addCell(subtotalCellText);
    table.addCell(subtotalCellValue);
    table.addCell(shopFeeCellText);
    table.addCell(shopFeeCellValue);
    table.addCell(lineCell);
    table.addCell(totalCellText);
    table.addCell(totalCellValue);
    table.addCell(paidCellText);
    table.addCell(paidCellValue);
    table.setFontSize(7);
    theDocument.add(table);
  }

}
