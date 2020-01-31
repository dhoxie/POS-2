import com.itextpdf.io.font.FontConstants;
import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.border.Border;
import com.itextpdf.layout.border.SolidBorder;
import com.itextpdf.layout.element.*;
import com.itextpdf.layout.property.Leading;
import com.itextpdf.layout.property.Property;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.UnitValue;

import java.io.FileNotFoundException;
import java.io.IOException;

public class PDFTest {
    private String fileName;
    private String firstName;
    private String lastName;
    private String email;
    private String garbageSentance;
    private Document theDocument;

    public PDFTest(String fileName , String firstName , String lastName ,  String email, String garbageSentance ){
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.garbageSentance = garbageSentance;
        this.fileName = fileName;
    }
    public PDFTest start() throws FileNotFoundException {
        // this is of type PDFTest so if someone wants to create this class and
        // create a pdf at the same time it can be done with object chaining
        PdfWriter writer = new  PdfWriter(fileName + ".pdf");
        PdfDocument pdf = new PdfDocument(writer);
        theDocument = new Document(pdf);
        createHeader();
        createCustInfoHeader();
        createPartsAndPrice();
        done();

        return this;
    }
    private void done(){
        theDocument.close();
    }
    private void createHeader(){
        Table table = new Table(3);
        theDocument.setProperty(Property.LEADING, new Leading(Leading.MULTIPLIED, .8f));
        table.setFontSize(8);
        Cell invoiceNumberCell = new Cell(1,1)
                 .setTextAlignment(TextAlignment.LEFT)
                .add(new Paragraph("Invoice # \n the number goes here"))
                .setBorder(Border.NO_BORDER);
        Cell whoWeAreCell = new Cell(1,1)
                .setTextAlignment(TextAlignment.CENTER)
                .add(new Paragraph("NORTHWEST AUTOMOTIVE CENTERS \n 324 north pines \n Spokane, WA 99216 \n 509-922-2006"))
                .setBorder(Border.NO_BORDER);
        Cell dateCell = new Cell(1,1)
                .setTextAlignment(TextAlignment.RIGHT)
                .add(new Paragraph("the date will go here "))
                .setBorder(Border.NO_BORDER);
        table.addCell(invoiceNumberCell);
        table.addCell(whoWeAreCell);
        table.addCell(dateCell);
        Text starLine = new Text("****************************************************************************************************************\n");
        theDocument.add(new Paragraph(starLine));
        theDocument.add(table);
        theDocument.add(new Paragraph(starLine));
    }
    private void createCustInfoHeader(){
        Table table = new Table(3);
        table.setFontSize(8);
        Cell nameAddrCell = new Cell(1,1)
                .setTextAlignment(TextAlignment.LEFT)
                .add(new Paragraph("LNAME: the name \nFNAME: the name \nADRES: the address here \nCY/ST: the city state here \nTECH: NW \nMANAGER: NW \nNOTES: notes here"))
                .setBorder(Border.NO_BORDER);
        Cell PhoneCell = new Cell(1,1)
                .setTextAlignment(TextAlignment.CENTER)
                .add(new Paragraph("HPHONE: home number \nWPHONE: work phone \nzip: zip code\nPO number: po box\nMANUF: car"))
                .setBorder(Border.NO_BORDER);
        Cell VehicleInfo = new Cell(1,1)
                .setTextAlignment(TextAlignment.RIGHT)
                .add(new Paragraph("TAG: plate \nYR: 9999 MAKE: the make\nMODEL: the MODEL \nMILEAGE: 123412\nVIN: 14512451245"))
                .setBorder(Border.NO_BORDER);
        table.addCell(nameAddrCell);
        table.addCell(PhoneCell);
        table.addCell(VehicleInfo);
        theDocument.add(table);
    }
    private void createPartsAndPrice(){
        Table bTable = new Table(1);
        Table table = new Table((UnitValue.createPercentArray(new float[] {2,16,1,1,1,1})));
        table.setFontSize(6);
        Cell topCell = new Cell(1,1)
                .setBorder(new SolidBorder(1f))
                .setBorderBottom(Border.NO_BORDER)
                .setBorderLeft(Border.NO_BORDER)
                .setBorderRight(Border.NO_BORDER);
        bTable.addCell(topCell);
        Cell partCell = new Cell(1,1)
                .setBorder(Border.NO_BORDER)
                .add(new Paragraph("partNum"));
        Cell descriptionCell = new Cell(1,1)
                .setBorder(Border.NO_BORDER)
                .add(new Paragraph("Description"));
        Cell quantityCell = new Cell(1,1)
                .setBorder(Border.NO_BORDER)
                .add(new Paragraph("qty"));
        Cell priceCell = new Cell(1,1)
                .setBorder(Border.NO_BORDER)
                .add(new Paragraph("price" ));
        Cell discCell = new Cell(1,1)
                .setBorder(Border.NO_BORDER)
                .add(new Paragraph("DISC"));
        Cell totalCell = new Cell(1,1)
                .setBorder(Border.NO_BORDER)
                .add(new Paragraph("TOTAL"));
        table.addCell(partCell);
        table.addCell(descriptionCell);
        table.addCell(quantityCell);
        table.addCell(priceCell);
        table.addCell(discCell);
        table.addCell(totalCell);
        theDocument.add(table);
        theDocument.add(bTable);

    }
}
