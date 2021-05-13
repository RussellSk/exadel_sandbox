package com.exadel.team2.sandbox.service;

import com.exadel.team2.sandbox.web.MessageDTO;
import freemarker.template.TemplateException;

import java.io.IOException;

public interface SendEmailService {

    void sendEmail(MessageDTO messageDTO) throws IOException, TemplateException;

}
