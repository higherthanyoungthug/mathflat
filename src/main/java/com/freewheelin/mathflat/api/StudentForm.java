package com.freewheelin.mathflat.api;

import lombok.Getter;
import lombok.Setter;

import javax.annotation.RegEx;
import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class StudentForm {

    private Long id;

    @NotEmpty(message = "이름은 필수 입력값입니다.")
    @Size(min = 1, max = 30, message = "이름은 최소 1자 ~ 최대 30자까지 입력 가능합니다.")
    private String name;

    @NotEmpty(message = "전화번호는 필수 입력값입니다.")
    @Size(min = 10, max = 11, message = "전화번호는 최소 10자, 최대 11자로 입력해주세요.")
    @Pattern(regexp = "^[0-9]*$", message = "전화번호는 숫자만 입력해주세요. 예)01012345678")
    private String phoneNumber;

    private List<String> subjectIds = new ArrayList<>();

    private String isDelete;
}
