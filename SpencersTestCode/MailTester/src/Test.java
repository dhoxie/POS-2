import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;
public class Test {
    public static void main(String [] args ) throws MessagingException {
        String host="smtp.zoho.com";
        final String user="spencercurley@spencercurley.com";//change accordingly
        final String password="mW6q0kJdBSQa";//change accordingly

        String to="spencercc128@yahoo.com";//change accordingly

        //Get the session object
        Properties props = new Properties();
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.port",  587);
        props.put("mail.smtp.host",host);
        props.put("mail.smtp.auth", "true");

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
            message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
            message.setSubject("attachment test");
            BodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setText("This is simple program of sending email using JavaMail API");
            Multipart multipart = new MimeMultipart();

            // Set text message part
            multipart.addBodyPart(messageBodyPart);

            // Part two is attachment
            messageBodyPart = new MimeBodyPart();
            String filename = "/Users/spencercurley/Desktop/winter2020/cscd488/project/POS-2/SpencersTestCode/test/test.pdf";
            DataSource source = new FileDataSource(filename);
            messageBodyPart.setDataHandler(new DataHandler(source));
            messageBodyPart.setFileName(filename);
            multipart.addBodyPart(messageBodyPart);

            // Send the complete message parts
            message.setContent(multipart);
            //send the message
            Transport.send(message);

            System.out.println("message sent successfully...");

        } catch (javax.mail.SendFailedException e) {
            e.printStackTrace();
            System.out.println(e.getInvalidAddresses()[0]);
        }
    }

}
