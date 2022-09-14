package com.ez2archive.repository;

import com.ez2archive.entity.Token;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RefreshTokenRepository extends CrudRepository<Token, String>
{
}
