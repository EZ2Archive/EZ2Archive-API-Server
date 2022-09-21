package com.ez2archive.service;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;

@Service
@RequiredArgsConstructor
public class EmailService
{
  private static final String TITLE_SUBJECT = "[EZ2Archive] ";
  private final JavaMailSender mailSender;

  public void send(String to, String subject, String text)
  {
    mailSender.send(mimeMessage -> {
      mimeMessage.setFrom(new InternetAddress("ez2archive1@gmail.com", "EZ2Archive", "UTF-8"));
      mimeMessage.addRecipients(Message.RecipientType.TO, to);
      mimeMessage.setSubject(TITLE_SUBJECT + subject);
      mimeMessage.setText(text);
    });
  }
}
