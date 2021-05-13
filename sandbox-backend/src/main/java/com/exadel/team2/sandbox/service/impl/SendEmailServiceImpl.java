package com.exadel.team2.sandbox.service.impl;

import com.exadel.team2.sandbox.service.SendEmailService;
import com.exadel.team2.sandbox.web.MessageDTO;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class SendEmailServiceImpl implements SendEmailService {

    private final JavaMailSender javaMailSender;
    private final FreeMarkerConfigurer freeMarkerConfigurer;

    @Value("${email.from.address}")
    private String emailFrom;


    @Override
    public void sendEmail(MessageDTO messageDTO) throws IOException, TemplateException {
        String text = getText(messageDTO.getProperties(), messageDTO.getTemplateName());

        messageDTO.setFrom(emailFrom);
        messageDTO.setText(text);

        MimeMessageHelper email = createEmail(messageDTO);
        javaMailSender.send(email.getMimeMessage());
    }


    private MimeMessageHelper createEmail(MessageDTO messageDTO) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = null;
        try {
            mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            mimeMessageHelper.setFrom(messageDTO.getFrom());
            mimeMessageHelper.setTo(messageDTO.getTo());
            mimeMessageHelper.setSubject(messageDTO.getSubject());
            mimeMessageHelper.setText(messageDTO.getText(), true);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return mimeMessageHelper;
    }

    private String getText(Map<String, Object> properties, String tmpName) throws IOException, TemplateException {
        Template template = freeMarkerConfigurer.getConfiguration().getTemplate(tmpName);
        return FreeMarkerTemplateUtils.processTemplateIntoString(template, properties);
    }

}

