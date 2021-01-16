package com.freewheelin.mathflat.common;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

public class CommonUtils {

    public static ResponseEntity responseByBindingResult(BindingResult result) {
        if (result.hasErrors()) {
            String defaultMessage = result.getFieldError().getDefaultMessage();
            return ResponseEntity
                    .badRequest()
                    .body(new CustomResponse(defaultMessage));
        }
        return null;
    }


}
