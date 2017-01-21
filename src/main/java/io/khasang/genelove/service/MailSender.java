package io.khasang.genelove.service;

import io.khasang.genelove.entity.Message;
import io.khasang.genelove.entity.User;
import io.khasang.genelove.entity.EMail;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import javax.persistence.TypedQuery;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.List;

@Component("MailSender")
public class MailSender {

    @Autowired
    Environment environment;
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private SQLService sqlService;

    private JdbcTemplate jdbcTemplate;
    private SessionFactory sessionFactory;
    private EMail eMail;

    private String addEmailIntoDB (EMail eMail) {

        String request = "INSERT into email (id, recipient, sender, subject, text)" +
                " VALUES (1, " + eMail.getRecipient() + ", " + eMail.getSender() +
                ", " + eMail.getSubject() + ", " + eMail.getText() + ");";
        return sqlService.insert(request);
    }

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
        System.out.println("RESPONSE: " + addEmailIntoDB(eMail));
    }

    public void sendEmail(User user, EMail eMail) throws UnsupportedEncodingException {
        EMail temp = new EMail(eMail);
        String msg = "Dear " + user.getFirstName() + " " + user.getLastName() + "!\n";
        msg += "You have got new mail from " + eMail.getSender() + "\n";
        temp.setText(msg + eMail.getText());
        temp.setRecipient(user.getEmail());
        mailSender.send(setEmailFields(temp));
        System.out.println("RESPONSE: " + addEmailIntoDB(eMail));
    }

    public void sendEmail(EMail eMail) /*throws UnsupportedEncodingException*/ {
        mailSender.send(setEmailFields(eMail));
    }

    public int sendEmail(List<User> listOfRecipients) throws UnsupportedEncodingException, InterruptedException {
        int count = 0;
        for (User recipient: listOfRecipients) {
            sendEmail(recipient);
            count++;
        }
        return count;
    }

    public int sendEmail(List<User> listOfRecipients, EMail eMail) throws UnsupportedEncodingException, InterruptedException {
        int count = 0;
        for (User recipient: listOfRecipients) {
            sendEmail(recipient, eMail);
            count++;
        }
        return count;
    }

    public String getEmailById (int id) {
        //SqlParameterSource namedParameters = new MapSqlParameterSource("id", id);
        System.out.println("SQL id: " + id);

        //String request = "SELECT email FROM users WHERE id = ?" ;
        //System.out.println(request);
        try {
            //String response = jdbcTemplate.queryForObject(request, String.class);
            TypedQuery query = sessionFactory.getCurrentSession().createNativeQuery
                    ( "SELECT * FROM messages WHERE id = ?", Message.class);
            query.setParameter(1, id);
            String response = query.getSingleResult().toString();
            return response.toString();
        }
        catch (Exception e) {
            return "Select email from users failed: " + e;
        }
    }
}
