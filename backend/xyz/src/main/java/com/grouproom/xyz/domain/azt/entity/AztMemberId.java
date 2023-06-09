package com.grouproom.xyz.domain.azt.entity;

import lombok.*;

import java.io.Serializable;

@Data
@Getter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class AztMemberId implements Serializable {

    private Long user;
    private Long azt;
}
