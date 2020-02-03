import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;
public class Test {
    public static void main(String [] args ) {
        SendInvoice tmp = new SendInvoice("/Users/spencercurley/Desktop/winter2020/cscd488/project/POS-2/SpencersTestCode/test/test.pdf" , "this is a test of the body " , "spencercc128@yahoo.com" , "this is the subject");
                                            // should change this to be just the file name based on the tempoary directory that i can save the pdfs in
        tmp.send();
        return ;
    }

}
