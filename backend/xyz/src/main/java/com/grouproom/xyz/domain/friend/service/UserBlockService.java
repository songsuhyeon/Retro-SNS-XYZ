package com.grouproom.xyz.domain.friend.service;

public interface UserBlockService {

    // 차단, 차단해제
    String saveUserBlock(Long loginSeq, Long userSeq);
}
