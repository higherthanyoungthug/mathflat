package com.freewheelin.mathflat.api;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class StudentForm {

    private Long id;

    @NotEmpty(message = "이름은 필수 입력값입니다.")
    private String name;

    private String isDelete;
}
