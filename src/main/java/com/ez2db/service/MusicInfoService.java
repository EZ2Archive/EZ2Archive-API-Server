package com.ez2db.service;

import com.ez2db.repository.MusicInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MusicInfoService
{
  private final MusicInfoRepository repository;

}
