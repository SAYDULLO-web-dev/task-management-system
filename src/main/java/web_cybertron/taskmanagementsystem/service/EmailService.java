package web_cybertron.taskmanagementsystem.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmailService {

    private final static String EMAIL_CONFIRMATION_SUBJECT = "Confirm your Task Management System platform account";
    private static final Logger logger = LoggerFactory.getLogger(EmailService.class);

    private final JavaMailSender javaMailSender;

    public void sendConfirmationEmail(String token, String email) {
        // Build email content
        String confirmationMessage = "Welcome to Task Management System, test token: " + token;
        String from ="no_reply@task-system-management.org"; // Correct email format
        send(email, from, confirmationMessage);
    }

    @Async
    public void send(String to, String from, String email) {
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(EMAIL_CONFIRMATION_SUBJECT);
            helper.setText(email);
            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            logger.error("Failed to send email to: " + to + " with message: " + email, e);
            throw new IllegalStateException("Failed to send email to: " + to, e);
        }
    }
}
