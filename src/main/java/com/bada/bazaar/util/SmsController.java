package com.bada.bazaar.util;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class SmsController {

  @Value("${twilio.accountSid}")
  public String ACCOUNT_SID;

  @Value("${twilio.authToken}")
  public String AUTH_TOKEN;

  @PostConstruct
  public void initTwilio() {
    Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
  }

  public void sendSms(String phoneNumber, String content) {
    Message message = Message.creator(
      new PhoneNumber("+919146942321"),
      new PhoneNumber("+12058596559"),
        content)
    .create();

    log.info(message.getSid());
  }


}