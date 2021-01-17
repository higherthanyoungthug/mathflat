package com.freewheelin.mathflat.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Data
@Getter
@Setter
@NoArgsConstructor
public class SubjectDto {

    private Long id;
    private String name;
    private String subjectCode;

    @QueryProjection
    public SubjectDto(Long id, String name, String subjectCode) {
        this.id = id;
        this.name = name;
        this.subjectCode = subjectCode;
    }
}

