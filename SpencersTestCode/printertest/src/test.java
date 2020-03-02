import javax.print.*;
import javax.print.attribute.Attribute;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;

public class test {
    public static void main(String[] args) throws PrintException, IOException {
        DocFlavor flavor = DocFlavor.SERVICE_FORMATTED.PAGEABLE;
        PrintRequestAttributeSet patts = new HashPrintRequestAttributeSet();
        patts.add(Sides.DUPLEX);

       PrintService[] ps = PrintServiceLookup.lookupPrintServices(null, patts);
        if (ps.length == 0) {
            throw new IllegalStateException("No Printer found");
        }
        System.out.println("Available printers: " + Arrays.asList(ps));

        PrintService myService = null;

        for (PrintService printService : ps) {
            if (printService.getName().equals("Brother HL-L6200DW series")) {
                myService = printService;
                printFunctionality(myService, "Trays", MediaTray.class);
                printFunctionality(myService, "Copies", Copies.class);
                printFunctionality(myService, "Print Quality", PrintQuality.class);
                printFunctionality(myService, "Color", ColorSupported.class);
                printFunctionality(myService, "Media Size", MediaSize.class);
                printFunctionality(myService, "Accepting Jobs", PrinterIsAcceptingJobs.class);


                break;
            }
        }
        Media[] tmp =(Media[]) myService.getSupportedAttributeValues(Media.class, flavor, null);
        for(Media media : tmp ){
            if (media instanceof MediaTray) {
                System.out.println(media.getName() + " : " + media + " - " + media.getClass().getName());
                System.out.println(media);

            }
        }

        if (myService == null) {
            throw new IllegalStateException("Printer not found");
        }

        FileInputStream fis = new FileInputStream("/Users/spencercurley/Desktop/test.pdf");
        Doc pdfDoc = new SimpleDoc(fis, DocFlavor.INPUT_STREAM.AUTOSENSE, null);
        DocPrintJob printJob = myService.createPrintJob();
        System.out.println(printJob.getAttributes());
        //printJob.print(pdfDoc, new HashPrintRequestAttributeSet());
        fis.close();








    }
    private static void printFunctionality(PrintService serv, String attrName, Class<? extends Attribute> attr) {
        boolean isSupported = serv.isAttributeCategorySupported(attr);
        System.out.println("    " + attrName + ": " + (isSupported ? "Y" : "N"));
       System.out.println(attr);

    }}
