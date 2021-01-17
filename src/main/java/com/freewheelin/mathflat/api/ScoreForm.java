package com.freewheelin.mathflat.api;

import com.freewheelin.mathflat.domain.Student;
import com.freewheelin.mathflat.domain.Subject;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;

@Getter
@Setter
public class ScoreForm {

    private Long id;

    @NotNull(message = "점수는 필수 입력값입니다.")
    @Min(0)
    @Max(100)
    private int score;

    @NotNull(message = "학생번호는 필수 입력값입니다.")
    private Long studentId;

    @NotNull(message = "과목번호는 필수 입력값입니다.")
    private Long subjectId;

    private String isDelete;

}
