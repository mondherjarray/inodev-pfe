package com.brightcoding.app.ws.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class SendMailService {
    @Autowired
    JavaMailSender emailSender;
    public void sendSimpleMessage(
            String to, String body, String topic) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("moundherj@gmail.com");
        message.setTo(to);
        message.setSubject(topic);
        message.setText(body);
        emailSender.send(message);
    }
}
