import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Properties;
/**
 * @author Spencer Curley
 *
 *  */
public class SendInvoice {
    private String filename;
    private String body;
    private String to;
    private String subject;

    /**
     * Constructor with a body of the email.
     *
     * @param filename
     * @param body
     * @param to
     * @param subject
     */
    public SendInvoice(String filename , String body, String to , String subject){
        // use the provided body
        this.filename = filename;
        this.to = to;
        this.body = body;
        this.subject = subject;
    }

    /**
     * Constructor that will use the default body for the email
     * @param filename
     * @param to
     * @param subject
     */
    public SendInvoice(String filename , String to, String subject){
        // use the default body of the email
        this.body = "this is the default body of the email ";
        this.filename = filename;
        this.to = to;
        this.subject = subject;
    }

    /**
     * Constructor that will use the default body and subject for the email
     * @param filename
     * @param to
     */
    public SendInvoice(String filename , String to){
        // use the default body of the email
        this.body = "this is the default body of the email ";
        this.filename = filename;
        this.to = to;
        this.subject = "your invoice";
    }

    /**
     * This method will do everything nessary to send the email as specified by the constructor. This will use spencercurley@spencercurley.com as the sending email address.
     * This will attach the PDF to the email, set the body of the email , set the subject of the email and send the email
     */
    public void send() {
        // can be changed to string to return error/sent message
        String host="smtp.zoho.com";
        // will change to providor that he uses
        final String user="spencercurley@spencercurley.com";//change accordingly
        // may want this be able to be changed if they want to send the email from which they send it
        // this will be the email that he wants to use after testing is done
        // might have to create a new email
        final String password="mW6q0kJdBSQa";//change accordingly
        // will change with email account
        String sendTo=this.to;//change accordingly

        //Get the session object
        Properties props = setUpProperties(host);

        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(user,password);
                    }
                });
        //Compose the message
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(user));
            message.addRecipient(Message.RecipientType.TO,new InternetAddress(sendTo));
            message.setSubject(this.subject);
            BodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setText(this.body);
            Multipart multipart = new MimeMultipart();

            // Set text message part
            multipart.addBodyPart(messageBodyPart);

            // Part two is attachment
            messageBodyPart = new MimeBodyPart();
            String filename = this.filename;
            // this will be the path to the temporary pdf
            DataSource source = new FileDataSource(filename);
            messageBodyPart.setDataHandler(new DataHandler(source));
            messageBodyPart.setFileName("attachment.pdf");
            multipart.addBodyPart(messageBodyPart);
            // have seen a bug where it attaches twice here. do not know how to fix yet
            // Send the complete message parts
            message.setContent(multipart);
            //send the message
            Transport.send(message);
            System.out.println("message sent successfully...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // could break up the above method even more but dont know if it is better or not.

    /**
     *This method will create properties needed to send an email. This was taken out of the send method so that it can be unit tested
     * @param host
     * @return The properties created by the method
     */
    public Properties setUpProperties(String host){
        // this is dev code because this is public so it can be unit tested.
        Properties props = new Properties();
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.port",  587);
        props.put("mail.smtp.host",host);
        props.put("mail.smtp.auth", "true");
        // will change to providors properties
        return props;
    }

}
