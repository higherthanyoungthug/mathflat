package com.freewheelin.mathflat.api;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
public class SubjectForm {

    private Long id;

    @NotEmpty(message = "과목명은 필수 입력값입니다.")
    @Size(min = 1, max = 30, message = "과목명은 1~30자로 입력해주세요.")
    private String name;

    @NotEmpty(message = "과목코드는 필수 입력값입니다.")
    @Size(min = 1, max = 3, message = "과목코드는 1~3자리로 입력해주세요.")
    @Pattern(regexp = "^[a-z]+$", message = "과목코드는 소문자 영어로 입력해주세요.")
    private String subjectCode;

    private String isDelete;
}
