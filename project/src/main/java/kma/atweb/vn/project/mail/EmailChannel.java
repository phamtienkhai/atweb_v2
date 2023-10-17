package kma.atweb.vn.project.mail;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Map;
import java.util.Properties;

public final class EmailChannel extends MessageChannel {
    private static final String userName = "";
    private static final String password = "";
    private static final String SUBJECT = "subject";
    private static final String TEXT = "text";
    private static final String MAIL_TO = "mail_to";
    public EmailChannel() {
        super();
    }

    @Override
    protected void _send(Map<String, Object> input) {
        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true");
        Session session = Session.getInstance(prop, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(userName, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(input.get(MAIL_TO).toString()));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(input.get(MAIL_TO).toString()));
            message.setSubject(input.get(SUBJECT).toString());
            message.setText(input.get(TEXT).toString());

            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
