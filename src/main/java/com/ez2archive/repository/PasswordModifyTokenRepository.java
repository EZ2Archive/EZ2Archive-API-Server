package com.ez2archive.repository;

import com.ez2archive.entity.PasswordModifyToken;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PasswordModifyTokenRepository extends CrudRepository<PasswordModifyToken, String>
{
}
