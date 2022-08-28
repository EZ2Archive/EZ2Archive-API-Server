package com.ez2archive.service;

import com.ez2archive.common.exception.business.BadRequestException;
import com.ez2archive.common.exception.business.DuplicateUniqueValueException;
import com.ez2archive.common.exception.business.IllegalValueException;
import com.ez2archive.common.exception.business.RequestTimeoutException;
import com.ez2archive.common.handler.crypt.EmailCryptHandler;
import com.ez2archive.common.validator.DefaultEmailValidator;
import com.ez2archive.common.validator.Validator;
import com.ez2archive.entity.Email;
import com.ez2archive.repository.EmailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.mail.Message;
import java.net.URI;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@RequiredArgsConstructor
@Service
public class VerifyService
{
  private final EmailRepository emailRepository;

  private final EmailCryptHandler emailCryptHandler;

  private final JavaMailSender mailSender;

  private final Validator<Email> emailValidator;

  @Transactional
  public void sendVerifyMail(String address)
  {
    // 이메일 유효성 호출방식 변경 필요...
    if( !((DefaultEmailValidator)emailValidator).isValidEmail(address) ) throw new IllegalValueException("유효하지 않은 이메일 형식입니다");

    String encodedAddress = emailCryptHandler.encode(address);

    Email findEmail = emailRepository.findEmailByAddress(encodedAddress)
      .orElseGet(Email::new);

    if( findEmail.isVerify() && findEmail.getMember() != null ) throw new DuplicateUniqueValueException("이미 사용중인 이메일입니다.");

    findEmail.setAddress(encodedAddress);
    findEmail.setVerify(false);
    findEmail.setAddTime(LocalDateTime.now());

    URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
      .path('/' + encodedAddress)
      .build().toUri();

    mailSender.send(mimeMessage -> {
      mimeMessage.addRecipients(Message.RecipientType.TO, address);
      mimeMessage.setSubject("[EZ2Archive] Please verify your email");
      mimeMessage.setText(
        "아래의 링크를 클릭하여 인증을 완료하세요.\n" + uri
      );
    });

    emailRepository.save(findEmail);
  }

  @Transactional
  public void verifyEmail(String key)
  {
    Email findEmail = emailRepository.findEmailByAddress(key)
      .orElseThrow( () -> new BadRequestException("잘못된 이메일 인증 요청입니다.") );

    if( isExpired(findEmail.getAddTime()) ) throw new RequestTimeoutException();

    findEmail.setVerify(true);
  }

  private boolean isExpired(LocalDateTime time)
  {
    return LocalDateTime.now().isAfter(time.plus(30, ChronoUnit.MINUTES));
  }
}
