package web_cybertron.taskmanagementsystem.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import web_cybertron.taskmanagementsystem.config.EmailConfig;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final EmailConfig emailConfig;
    private final JavaMailSender javaMailSender;

    private final static String EMAIL_CONFIRMATION_SUBJECT = "Confirm your Task Management System platform account";
    private static final Logger logger = LoggerFactory.getLogger(EmailService.class);

    public void sendConfirmationEmail(String confirmationCode, String emailTo) {
        String confirmationMessage = "Welcome to Task Management System, your confirmation code is: " + confirmationCode;
        send(emailTo, emailConfig.getUser(), confirmationMessage);
    }

    @Async
    public void send(String emailTo, String emailFrom, String confirmationMessage) {
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
            helper.setFrom(emailFrom);
            helper.setTo(emailTo);
            helper.setSubject(EMAIL_CONFIRMATION_SUBJECT);
            helper.setText(confirmationMessage);
            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            logger.error("Failed to send email to: {} from: {}", emailTo, emailFrom, e);
            throw new IllegalStateException("Failed to send email to: " + emailTo, e);
        }
    }
}
