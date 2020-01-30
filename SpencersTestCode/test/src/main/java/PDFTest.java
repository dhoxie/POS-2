import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.property.TextAlignment;

import java.io.FileNotFoundException;

public class PDFTest {
    private String fileName;
    private String firstName;
    private String lastName;
    private String email;
    private String garbageSentance;
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
        Document document = new Document(pdf);
        Text names = new Text(lastName + ", " + firstName);
        Text theEmail = new Text(email);
        Text theTrash = new Text(garbageSentance);
        names.setFontSize(25);
        Paragraph titleParagraph = new Paragraph();
        theTrash.setFontColor(Color.BLUE);
        titleParagraph.add(names);
        titleParagraph.add("\n");
        titleParagraph.add(theEmail);
        titleParagraph.add("\n");
        titleParagraph.add(theTrash);
        titleParagraph.add("\n");
        titleParagraph.setTextAlignment(TextAlignment.CENTER);
        document.add(titleParagraph);
        document.close();
        return this;
    }

}
