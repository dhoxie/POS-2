package com.scottsdaleair.email;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.scottsdaleair.utils.Config;
/**
 * A Class used to send an invoice via email.
 * 
 * @author Spencer Curley
 *
 */
public class SendInvoice {
  private String to;
  private String subject;
  private Email theEmail;

  /**
   * Create a new SendInvoice object with the specified to address and email to send from.
   * @param to        String email of the recieving address
   * @param theEmail  Email object of the sending address
   */
  public SendInvoice(String to, Email theEmail) {
    this.subject = "Your invoice from NorthWest Automotive Center ";
    this.to = to;
    this.theEmail = theEmail;
  }

  /**
   * This method will do everything nessary to send the email as specified by the
   * constructor. This will use spencercurley@spencercurley.com as the sending
   * email address. This will attach the PDF to the email, set the body of the
   * email , set the subject of the email and send the email
   */
  public void send() {

    String host = "smtp.zoho.com";
    final String user = "invoices@scottsdaleairport.tech";
    final String password = Config.getConfig().getEmailPassword();

    String sendTo = this.to;
    Properties props = setUpProperties(host);

    Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
      protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(user, password);
      }
    });
    // Compose the message
    try {
      MimeMessage message = new MimeMessage(session);
      message.setFrom(new InternetAddress(user));
      message.addRecipient(Message.RecipientType.TO, new InternetAddress(sendTo));
      message.setSubject(this.subject);
      message.setContent((Multipart) theEmail.getEmail());
      // this could throw exception later
      // send the message
      Transport.send(message);
      System.out.println("message sent successfully...");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  // could break up the above method even more but dont know if it is better or
  // not.

  /**
   * This method will create properties needed to send an email. This was taken
   * out of the send method so that it can be unit tested
   * 
   * @param host Host address of the mail server
   * @return The properties created by the method
   */
  public Properties setUpProperties(String host) {
    // this is dev code because this is public so it can be unit tested.
    Properties props = new Properties();
    props.put("mail.smtp.host", host);
    props.put("mail.smtp.port", "587");
    props.put("mail.smtp.auth", "true");
    props.put("mail.smtp.starttls.enable", "true");
    return props;
  }
  // thisisthepassword42
}
