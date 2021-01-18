package com.mt.brightauthorization.service.token.imp;

import com.mt.brightauthorization.dto.JwtDataResponse;
import com.mt.brightauthorization.dto.UserRequestDTO;
import com.mt.brightauthorization.entity.Users;
import com.mt.brightauthorization.repoitory.UserRepository;
import com.mt.brightauthorization.util.JwtTokenUtil;
import com.mt.brightauthorization.service.token.TokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class JwtTokenProvider implements TokenProvider {

    private JwtTokenUtil jwtTokenUtil;

    private UserRepository userRepository;

    private AuthenticationManager authenticationManager;

    @Override
    public String generateToken(UserRequestDTO user) throws Exception {

        authenticate(user.getPhone(), user.getPassword());

        final Users userDetails = userRepository.findByPhone(user.getPhone());

        return jwtTokenUtil.generateToken(userDetails);
    }

    @Override
    public JwtDataResponse validateToken(String accessToken)
    {
        String subject = jwtTokenUtil.getSubject(accessToken);

        final Users userDetails = userRepository.findByPhone(subject);

        if(Objects.isNull(userDetails)){
            throw new UsernameNotFoundException("User not exist");
        }

        Boolean status = jwtTokenUtil.validateToken(accessToken, userDetails);


       return JwtDataResponse.builder()
                .clientId(userDetails.getId())
                .phone(userDetails.getPhone())
                .username(userDetails.getUsername())
                .status(status)
                .build();
    }

    private void authenticate(String phone, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(phone, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }

    @Autowired
    public void setJwtTokenUtil(JwtTokenUtil jwtTokenUtil) {
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Autowired
    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

}
