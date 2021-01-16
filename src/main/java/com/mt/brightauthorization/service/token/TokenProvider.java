package com.mt.brightauthorization.service.token;

import com.mt.brightauthorization.entity.Users;

public interface TokenProvider {

    String generateToken(String phone);
}
