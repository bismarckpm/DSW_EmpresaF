package ucab.dsw.servicio.recuperacion;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class RecuperacionEmail {

  private final Properties props = new Properties();


  public RecuperacionEmail() {
    props.put("mail.smtp.auth", "true");
    props.put("mail.smtp.starttls.enable", "true");
    props.put("mail.smtp.host", "smtp.gmail.com");
    props.put("mail.smtp.port", "587");

    props.put("from", "empresafdsw@gmail.com");
    props.put("username", "empresafdsw@gmail.com");
    props.put("password", "empresafdsw123");
  }

  public void enviarMail(String to, String subject, String content) throws MessagingException {
    Session session = Session.getInstance(props, new javax.mail.Authenticator() {
      protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(props.getProperty("username"), props.getProperty("password"));
      }
    });

    Message message = new MimeMessage(session);
    message.setFrom(new InternetAddress(props.getProperty("from")));
    message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
    message.setSubject(subject);
    message.setText(content);
    Transport.send(message);

    System.out.println("mensaje enviado!");
  }
}
