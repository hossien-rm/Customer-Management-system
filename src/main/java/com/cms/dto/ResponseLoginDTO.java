package com.cms.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ResponseLoginDTO {

    private String status;
    private String jwtToken;


}
