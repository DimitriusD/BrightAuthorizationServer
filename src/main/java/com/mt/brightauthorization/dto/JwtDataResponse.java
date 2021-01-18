package com.mt.brightauthorization.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter @Setter
public class JwtDataResponse {

    private long clientId;

    private String username;

    private String phone;

    private String scope;

    private Boolean status;

}
