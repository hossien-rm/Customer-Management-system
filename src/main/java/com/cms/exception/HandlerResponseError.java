package com.cms.exception;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class HandlerResponseError {

    private int status;
    private String message;
    private Long timeStamp;

}
