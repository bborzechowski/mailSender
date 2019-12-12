package bborzechowski.mail_sender.service;

import bborzechowski.mail_sender.model.MyEmail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class EmailSenderService implements EmailSender {

    @Value("${mail.login}")
    private String hostEmailAddress;

    private static final Logger logger = LoggerFactory.getLogger(EmailSenderService.class);
    private JavaMailSender javaMailSender;

    public EmailSenderService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Override
    public MyEmail sendEmail(MyEmail myEmail) {
        MimeMessage mail = javaMailSender.createMimeMessage(); //wysyłanie meila
        MimeMessageHelper helper;  //ustawienia wysyłania meila

        try {
            helper = new MimeMessageHelper(mail, true); //ustawienia wysyłania meila
            helper.setTo(myEmail.getAddress()); // do kogo wysyłąmy
            helper.setReplyTo(hostEmailAddress); //wysyłamy też sobie na meila np powiadoniemie czy meil zostal wysłany
            helper.setFrom(hostEmailAddress); // z jakiego maila wysyłąmy wiadomość
            helper.setSubject(myEmail.getSubject()); //temat wiadomości
            helper.setText(myEmail.getBody(), true); //treść wiadomości

        } catch (MessagingException e) {
            logger.error("error while sending email: {}", e.getMessage());
            return null;
        }
        javaMailSender.send(mail);
        return myEmail;
    }
}

