package service;

import dao.TextsDao;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class MailService {
    private String from;
    private String to;
    private String subject;
    private String text;

    public MailService(String from, String to) {
        this.from = from;
        this.to = to;
    }

    public boolean sendEMail(String subject, String text) {
        try {
            Session session =
                   Session.getInstance(getProperties(),
                           new MyAuthenticator());

            this.subject = subject;
            this.text = text;

            Message message = createMessage(session);
            Transport.send(message);

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private Message createMessage(Session session)
            throws MessagingException {
        Message message = new MimeMessage(session);

        message.setFrom(new InternetAddress(from));

        message.setRecipients(Message.RecipientType.TO,
                InternetAddress.parse(to));

        message.setSubject(subject);

        message.setText(text);
        return message;
    }

    private Properties getProperties() {
        Properties props = new Properties();
        props.put("mail.smtp.auth",
                TextsDao.getProperty("mail.smtp.auth"));

        props.put("mail.smtp.starttls.enable",
                TextsDao.getProperty("mail.smtp.starttls.enable"));

        props.put("mail.smtp.host",
                TextsDao.getProperty("mail.smtp.host"));

        props.put("mail.smtp.port",
                TextsDao.getProperty("mail.smtp.port"));
        return props;
    }

    class MyAuthenticator extends Authenticator {
        String username = TextsDao.getProperty("sender.username");
        String password = TextsDao.getProperty("sender.password");

        @Override
        protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(username, password);
        }
    }
}
