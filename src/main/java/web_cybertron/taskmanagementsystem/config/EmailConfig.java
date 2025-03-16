package web_cybertron.taskmanagementsystem.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Data
@Configuration
@ConfigurationProperties(prefix = "task.email.sender")
public class EmailConfig {

    private static final int GMAIL_SMTP_PORT = 587;

    private String host;
    private String user;
    private String password;
    private Boolean debug;

    @Bean
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        // Set up Gmail config
        mailSender.setHost(host);
        mailSender.setPort(GMAIL_SMTP_PORT);

        // Set up email config
        mailSender.setUsername(user);
        mailSender.setPassword(password);

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.ssl.enable", "false");

        // Debug check
        if (debug != null) {
            props.put("mail.debug", debug.toString());
        } else {
            System.out.println("Debug info is not available.");
        }
        System.out.println("get user:" +new EmailConfig().getUser());
        System.out.println("get host:" +new EmailConfig().getHost());
        System.out.println("get ps:" +new EmailConfig().getPassword());
        System.out.println("get debug:" +new EmailConfig().getDebug());
        return mailSender;
    }
}
