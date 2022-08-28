package com.ez2archive.repository;

import com.ez2archive.entity.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Deprecated
@Repository
public interface FileRepository extends JpaRepository<File, Long>
{
  Optional<File> findFileByFileUUID(String fileUUID);
}
