package com.yevheniir.hwtp.service;

import com.yevheniir.hwtp.model.Stuff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.activation.DataSource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.mail.util.ByteArrayDataSource;
import java.util.List;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender emailSender;

    @Autowired
    private StorageService storageService;

    public void sendSimpleMessage(String to, String subject, String text, List<Stuff> stuffs) throws MessagingException {

        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(text);

        stuffs.forEach((stuff -> {

            DataSource file = new ByteArrayDataSource(storageService.getFile(stuff.getFile()), "text/plain");
            try {
                helper.addAttachment(stuff.getFile(), file);
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        }));

        emailSender.send(message);

    }
}
