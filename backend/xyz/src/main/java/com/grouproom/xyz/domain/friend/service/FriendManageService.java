package com.grouproom.xyz.domain.friend.service;

import com.grouproom.xyz.domain.friend.dto.response.FriendListResponse;

public interface FriendManageService {

    // 친구목록조회, 친구끊기, 친구중에 닉네임검색, 친구중에 코드검색
    FriendListResponse findFriend(Long loginUserSeq);
    String modifyFriendToDeleted(Long loginSeq, Long userSeq);
    FriendListResponse findFriendByNickname(Long loginSeq, String nickname);
    FriendListResponse findFriendByIdentify(Long loginSeq, String identify);
}
