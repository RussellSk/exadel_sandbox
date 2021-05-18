package com.exadel.team2.sandbox.service;

import com.exadel.team2.sandbox.exceptions.FlagDisabledException;
import com.exadel.team2.sandbox.web.MessageDTO;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

@ConditionalOnProperty(
        name = "features.flag.emailsender",
        havingValue = "false")
@Service
public class SendEmailDefaultService implements SendEmailService {
    @Override
    public void sendEmail(MessageDTO messageDTO) throws FlagDisabledException {
        throw new FlagDisabledException("Fail during email sending because the feature flag is disabled!");
    }
}
