package io.khasang.genelove.service;

import io.khasang.genelove.entity.User;
import io.khasang.genelove.entity.EMail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.List;

@Component("MailSender")
public class MailSender {

    @Autowired
    Environment environment;
    @Autowired
    private JavaMailSender mailSender;
    private JdbcTemplate jdbcTemplate;
    private EMail eMail;

    public MailSender (JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public MailSender () {}

    // creates a simple e-mail object
    public SimpleMailMessage setEmailFields (EMail eMail) {
        SimpleMailMessage eLetter = new SimpleMailMessage();
        eLetter.setTo(eMail.getRecipient());
        eLetter.setFrom(eMail.getSender());
        eLetter.setSubject(eMail.getSubject());
        eLetter.setText(eMail.getText());
        return eLetter;
    }

    public void sendEmail(User user) throws UnsupportedEncodingException {
        // Test e-Mail
        String subject = "Hi " + user.getFirstName() + " " + user.getLastName() + "!";
        String text = "This is the First test Letter from Genelove Java Mail Service.\n" +
                "Here some your personal data: \n" +
                "Your First Name is: " + user.getFirstName() + ".\n" +
                "Your Last Name is: " + user.getLastName() + ".\n" +
                "Your Gender is: " + user.getGender() + ".\n";

        eMail = new EMail(user.getEmail(),
                environment.getProperty("mail.username"), subject, text);

        mailSender.send(setEmailFields(eMail));
    }

    public void sendEmail(User user, EMail eMail) throws UnsupportedEncodingException {
        eMail.setRecipient(user.getEmail());
        mailSender.send(setEmailFields(eMail));
    }

    // send e-mail from simple html form
    public void sendEmail(EMail eMail) /*throws UnsupportedEncodingException*/ {
        mailSender.send(setEmailFields(eMail));
    }

    public void sendEmail(List<User> listOfRecipients) throws UnsupportedEncodingException, InterruptedException {
        for (User recipient: listOfRecipients) {
            sendEmail(recipient);
        }
    }

    public void sendEmail(List<User> listOfRecipients, EMail eMail) throws UnsupportedEncodingException, InterruptedException {
        for (User recipient: listOfRecipients) {
            sendEmail(recipient, eMail);
        }
    }

    public String getEmailById (int id) {
        System.out.println("SQL id: " + id);
        String request = "SELECT email FROM users WHERE id = " + id + ";";
        String response = "";
        try {
            response = jdbcTemplate.queryForObject (request, String.class);
            return response.toString();
        }
        catch (Exception e) {
            return "Select email from users failed: " + e;
        }
    }
}
