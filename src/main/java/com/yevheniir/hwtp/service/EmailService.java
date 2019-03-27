package com.yevheniir.hwtp.service;

import com.yevheniir.hwtp.model.Stuff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.List;

@Service
public class EmailService {

    @Autowired
    public JavaMailSender emailSender;

    public void sendSimpleMessage(String to, String subject, String text, List<Stuff> stuffs) throws MessagingException {

        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(text);

        stuffs.forEach((stuff -> {
            FileSystemResource file
                    = new FileSystemResource(new File("src/main/resources/files/" + stuff.getFile()));
            try {
                helper.addAttachment(stuff.getFile(), file);

            } catch (MessagingException e) {
                e.printStackTrace();
            }
        }));

        emailSender.send(message);

    }
}
