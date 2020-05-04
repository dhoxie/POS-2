package com.scottsdaleair.email;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Multipart;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;
public class Email {
    // this class takes in the input for the email body and be what the email sender expects as input
    // the only thing that this will not handle that the sender needs is the subject unless we dicide to have the same subject for each email
    private Multipart theEmail;
    public Email(String body , String pdfName ) {
        try{
            BodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setText(body);
            Multipart multipart = new MimeMultipart();
            // Set text message part
            multipart.addBodyPart(messageBodyPart);
            // Part two is attachment
            messageBodyPart = new MimeBodyPart();
            // this will be the path to the temporary pdf
            DataSource source = new FileDataSource(pdfName);
            messageBodyPart.setDataHandler(new DataHandler(source));
            messageBodyPart.setFileName("Invoice.pdf");
            // this should be the name of the person that the invoice is attached to along with the date
            multipart.addBodyPart(messageBodyPart);
            theEmail = multipart;
        }catch(Exception e){
            System.err.println("failed create message ");
        }

    }
    Object getEmail(){
        // this should be package visibility because only the sendInvoice should be able to call this method
        return theEmail;
    }



}
