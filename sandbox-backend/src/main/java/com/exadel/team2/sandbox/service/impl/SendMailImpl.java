package com.exadel.team2.sandbox.service.impl;

import com.exadel.team2.sandbox.service.SendMail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class SendMailImpl implements SendMail {

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public void sendEmail(String sendTo, String subject, String text) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(sendTo);

        msg.setSubject(subject);
        msg.setText(text);

        javaMailSender.send(msg);
    }
}
