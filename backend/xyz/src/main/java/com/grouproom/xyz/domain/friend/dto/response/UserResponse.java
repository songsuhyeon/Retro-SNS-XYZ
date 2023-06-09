package com.grouproom.xyz.domain.friend.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserResponse {

    private Long userSeq;
    private String nickname;
    private String profileImage;
    private String identify;
    private String relation;
}
