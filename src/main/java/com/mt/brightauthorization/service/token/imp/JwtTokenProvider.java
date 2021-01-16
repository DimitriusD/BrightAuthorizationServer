package com.mt.brightauthorization.service.token.imp;

import com.mt.brightauthorization.entity.Users;
import com.mt.brightauthorization.repoitory.UserRepository;
import com.mt.brightauthorization.service.UserService;
import com.mt.brightauthorization.util.JwtTokenUtil;
import com.mt.brightauthorization.service.token.TokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class JwtTokenProvider implements TokenProvider {

    private UserRepository userRepository;

    private JwtTokenUtil jwtTokenUtil;

    @Override
    public String generateToken(String phone) {

        final Users userDetails = userRepository.findByPhone(phone);

        return jwtTokenUtil.generateToken(userDetails);
    }

    @Autowired
    public void setJwtTokenUtil(JwtTokenUtil jwtTokenUtil) {
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


}
