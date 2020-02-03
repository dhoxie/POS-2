package PDF;

import PDFParts.Part;
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
import java.util.Calendar;
/**
 * @author Spencer Curley
 *
 * */
public class PDFInvoice {
    private String fileName;
    private Document theDocument;
    private int invoiceNum;
    private Part[] parts;
    private String notes;
    public PDFInvoice(String fileName , int invoiceNum , Part[]parts  , String notes){
        if(fileName == null || parts == null || notes == null){
            throw new IllegalArgumentException("value of params cannot be null");
        }
        if(invoiceNum < 0 ){
            throw new IllegalArgumentException("invoiceNum cannot be below 0");
        }
        this.fileName = fileName;
        this.invoiceNum = invoiceNum;
        this.parts = parts;
        this.notes = notes;
    }

    /**
     * Used only in junit code
     * @return this object
     * @throws FileNotFoundException
     */
    public PDFInvoice testStart() throws FileNotFoundException {
        // only to be called in the test files
        PdfWriter writer = new  PdfWriter(fileName + ".pdf");
        PdfDocument pdf = new PdfDocument(writer);
        theDocument = new Document(pdf);
        return this;
    }

    /**
     * used for creating production pdfs
     * @return this object
     * @throws FileNotFoundException
     */

    public PDFInvoice start() throws FileNotFoundException {
        // this is of type PDFTest so if someone wants to create this class and
        // create a pdf at the same time it can be done with object chaining
        PdfWriter writer = new  PdfWriter(fileName + ".pdf");
        PdfDocument pdf = new PdfDocument(writer);
        theDocument = new Document(pdf);
        // all of the below function calls should return stuff so that the adding to the pdf is done here to make it easy to unit test
        createHeader();
        createCustInfoHeader();
        createPartsAndPriceHeader();
        for(int i = 0 ; i < parts.length ; i++){
            addPart(parts[i]);
        }
        addNotesHeader();
        addNotes(notes);
        done();

        return this;
    }

    /**
     * used for junit
     * @return fileName
     */
    public String getFileName(){
        return this.fileName;
    }

    /**
     * used for junit
     * @return returns incoiveNum
     */
    public int getInvoiceNum(){
        return this.invoiceNum;
    }

    /**
     * used for junit
     * @return returns the parts
     */
    public Part[] getParts(){
        return this.parts;
    }

    /**
     * closes and creates the pdf
     */
    private void done(){
        theDocument.close();
    }

    /**
     * used for juint
     * @return return the notes
     */
    public String getNotes(){
        return this.notes;
    }

    /**
     * creates the header
     * @return return is for junit
     */
    public Document createHeader(){
        Table table = new Table(3);
        theDocument.setProperty(Property.LEADING, new Leading(Leading.MULTIPLIED, .8f));
        table.setFontSize(8);
        Cell invoiceNumberCell = new Cell(1,1)
                 .setTextAlignment(TextAlignment.LEFT)
                .add(new Paragraph("Invoice # \n" + invoiceNum))
                .setBorder(Border.NO_BORDER);
        Cell whoWeAreCell = new Cell(1,1)
                .setTextAlignment(TextAlignment.CENTER)
                .add(new Paragraph("NORTHWEST AUTOMOTIVE CENTERS \n 324 north pines \n Spokane, WA 99216 \n 509-922-2006"))
                .setBorder(Border.NO_BORDER);
        Calendar rightNow = Calendar.getInstance();
        Cell dateCell = new Cell(1,1)
                .setTextAlignment(TextAlignment.RIGHT)
                .add(new Paragraph(rightNow.get(Calendar.YEAR)+ "/" +  rightNow.get(Calendar.MONTH) +1 + "/" + rightNow.get(Calendar.DAY_OF_MONTH)))
                .setBorder(Border.NO_BORDER);
        table.addCell(invoiceNumberCell);
        table.addCell(whoWeAreCell);
        table.addCell(dateCell);
        Text starLine = new Text("****************************************************************************************************************\n");
        theDocument.add(new Paragraph(starLine));
        theDocument.add(table);
        theDocument.add(new Paragraph(starLine));
        return theDocument;
    }

    /**
     * creates the customerinfo name address and vehicle info etc....
     * @return is for junit only
     */
    public Document createCustInfoHeader(){
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
        return theDocument;
    }

    /**
     * creates part that ays partNum Description and price infro
     * @return is for junit
     */
    public Document createPartsAndPriceHeader(){
        Table bTable = new Table(1);
        Table table = new Table((UnitValue.createPercentArray(new float[] {2,16,1,1,1,1})));
        table.setFontSize(8);
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
        return theDocument;

    }

    /**
     * adds the notes header
     * @return for junit only
     */
    public Document addNotesHeader(){
        Table bTable = new Table(1);
        Cell topCell = new Cell(1,1)
                .setBorder(new SolidBorder(1f))
                .setBorderBottom(Border.NO_BORDER)
                .setBorderLeft(Border.NO_BORDER)
                .setBorderRight(Border.NO_BORDER);
        bTable.addCell(topCell);
        theDocument.add(new Paragraph("Notes").setFontSize(8));
        theDocument.add(bTable);
        return theDocument;
    }

    /**
     * adds the param to the notes 
     * @param theNotes
     * @return
     */
    public Document addNotes(String theNotes){
        theDocument.add(new Paragraph(theNotes + "\n this not written by me").setFontSize(6));
        return theDocument;
    }

    /**
     * adds the part to the description part in the pdf
     * @param toAdd
     * @return the document for unit testing purposes
     */
    public Document addPart(Part toAdd){
        Table table = new Table((UnitValue.createPercentArray(new float[] {2,16,1,1,1,1})));
        table.setFontSize(6);
        Cell [] cells = toAdd.createInvoiceFormatCells();
        for(int i = 0 ; i < cells.length ; i++){
            table.addCell(cells[i]);
        }
        theDocument.add(table);
        return theDocument;
    }


}
