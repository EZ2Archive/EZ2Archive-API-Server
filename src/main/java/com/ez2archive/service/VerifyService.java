package com.ez2archive.service;

import com.ez2archive.common.exception.business.BadRequestException;
import com.ez2archive.common.exception.business.RequestTimeoutException;
import com.ez2archive.common.exception.business.ResourceNotFoundException;
import com.ez2archive.common.handler.crypt.EmailCryptHandler;
import com.ez2archive.entity.Email;
import com.ez2archive.repository.EmailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import java.net.URI;
import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class VerifyService
{
  private final EmailRepository emailRepository;

  private final EmailCryptHandler emailCryptHandler;

  private final JavaMailSender mailSender;

  @Transactional
  public void sendVerifyMail(String address)
  {
    String encAddress = emailCryptHandler.encode(address);

    Email findEmail = emailRepository.findEmailByAddress(encAddress)
      .orElseThrow( () -> new ResourceNotFoundException("저장된 이메일 정보가 존재하지 않습니다.") );

    if( findEmail.isVerify() ) throw new BadRequestException("이미 인증이 완료된 이메일입니다.");

    URI uri = ServletUriComponentsBuilder.fromCurrentContextPath()
      .path("/verify/" + encAddress)
      .build().toUri();

    findEmail.setExpireTime(Email.getExpireTime(LocalDateTime.now()));

    mailSender.send(mimeMessage -> {
      mimeMessage.setFrom(new InternetAddress("ez2archive1@gmail.com", "EZ2Archive", "UTF-8"));
      mimeMessage.addRecipients(Message.RecipientType.TO, address);
      mimeMessage.setSubject("[EZ2Archive] Please verify your email");
      mimeMessage.setText("본 메일은 발신전용으로 아래의 링크를 클릭하여 인증을 완료하세요.\n\n" + uri);
    });

  }

  @Transactional
  public void verifyEmail(String key)
  {
    Email findEmail = emailRepository.findEmailByAddress(key)
      .orElseThrow( () -> new BadRequestException("잘못된 이메일 인증 요청입니다.") );

    if( findEmail.isVerify() ) throw new BadRequestException("이미 인증된 이메일입니다.");

    if( LocalDateTime.now().isAfter( findEmail.getExpireTime() ) ) throw new RequestTimeoutException();

    findEmail.setVerify(true);
  }
}
