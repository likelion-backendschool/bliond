package com.likelion.bliond.util;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JwtDto {

    private String accessToken;
//    private String refreshToken;
    private String bearer_type;
    private Long expiresIn;
}
