package com.freewheelin.mathflat.common;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CustomResponse {
    private String message;
    private int status;

    public CustomResponse(String message){
        this.message = message;
        this.status = 400;
    }

    public CustomResponse(String message, int status){
        this.message = message;
        this.status = status;
    }
}
