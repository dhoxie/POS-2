import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;

import java.io.FileNotFoundException;

public class test {
    public static void main(String [] args) throws FileNotFoundException {
        PdfWriter writer = new  PdfWriter("test.pdf");
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf);
        document.add(new Paragraph("Hello World!"));
        document.close();
        PDFTest test = new PDFTest("test" , "Spencer" , "Curley" , "thisIsAFake@email.com" , "Why am I blue").start();
    }

}
