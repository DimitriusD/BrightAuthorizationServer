package com.mt.brightauthorization.dto;

import java.io.Serializable;

public class JwtResponse implements Serializable {

    private static final long serialVersionUID = 540L;

    private String jwtAccessToken;

    public JwtResponse(String jwtToken) {
        this.jwtAccessToken = jwtToken;
    }

    public String getJwtToken() {
        return jwtAccessToken;
    }
}
