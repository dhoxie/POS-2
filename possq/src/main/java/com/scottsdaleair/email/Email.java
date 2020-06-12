package com.scottsdaleair.email;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Multipart;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;

public class Email {
  /**
   * This class takes in the input for the email body and be what the email sender expects as
   * input. The only thing is that this will not handle that the sender needs is the subject
   * unless we dicide to have the same subject for each email.
   */
  private Multipart theEmail;

  /**
   * Create an email object from a message body and a PDF.
   * @param body    Body f the email to create
   * @param pdfName PDF to send with email
   */
  public Email(String body, String pdfName) {
    try {
      BodyPart messageBodyPart = new MimeBodyPart();
      messageBodyPart.setText(body);
      Multipart multipart = new MimeMultipart();
      // Set text message part
      multipart.addBodyPart(messageBodyPart);
      // Part two is attachment
      messageBodyPart = new MimeBodyPart();
      // This will be the path to the temporary pdf
      DataSource source = new FileDataSource(pdfName);
      messageBodyPart.setDataHandler(new DataHandler(source));
      messageBodyPart.setFileName("Invoice.pdf");
      // This should be the name of the person that the invoice is attached to
      // date along with the
      multipart.addBodyPart(messageBodyPart);
      theEmail = multipart;
    } catch (Exception e) {
      System.err.println("failed create message ");
    }

  }

  Object getEmail() {
    // this should be package visibility because only the sendInvoice should be able to call
    // this method
    return theEmail;
  }



}
