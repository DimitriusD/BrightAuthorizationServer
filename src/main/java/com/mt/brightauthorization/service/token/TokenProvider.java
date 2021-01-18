package com.mt.brightauthorization.service.token;

import com.mt.brightauthorization.dto.JwtDataResponse;
import com.mt.brightauthorization.dto.UserRequestDTO;

public interface TokenProvider {

    String generateToken(UserRequestDTO user) throws Exception;

    JwtDataResponse validateToken(String accessToken);
}
