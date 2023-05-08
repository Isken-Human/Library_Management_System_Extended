package kg.edu.alatoo.springWeb.services;

import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import javax.mail.MessagingException;


@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;


    public void sendVerificationEmail(String to, String subject, String token) throws MessagingException, jakarta.mail.MessagingException {
        jakarta.mail.internet.MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText("<h1>Email Verification</h1>"
                + "<p>Please click the link below to verify your email address:</p>"
                + "<a href=\"http://localhost:8080/verify-email?token=" + token + "\">Verify Email</a>", true);

        javaMailSender.send(message);
    }

    public void sendPasswordResetEmail(String to, String subject, String token) throws MessagingException, jakarta.mail.MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText("<h1>Password Reset</h1>"
                + "<p>Please click the link below to reset your password:</p>"
                + "<a href=\"http://localhost:8082/reset-password?token=" + token + "\">Reset Password</a>", true);

        System.out.println("Message was sent !");

        javaMailSender.send(message);
    }
}
