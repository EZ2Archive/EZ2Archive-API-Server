package com.ez2archive.repository;

import com.ez2archive.entity.Email;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmailRepository extends JpaRepository<Email, Long>
{
  Optional<Email> findEmailByAddress(String address);
}
