package com.freewheelin.mathflat.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.*;


@Data
@Getter
@Setter
@NoArgsConstructor
public class StudentDto {

    private Long id;
    private String name;
    private String phoneNumber;

    @QueryProjection
    public StudentDto(Long id, String name, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }
}
