package com.freewheelin.mathflat.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
public class ScoreDto {

    private Long id;
    private String studentName;
    private String subjectName;
    private int score;
    private double avgScore;

    @QueryProjection
    public ScoreDto(Long id, String studentName, String subjectName, int score) {
        this.id = id;
        this.studentName = studentName;
        this.subjectName = subjectName;
        this.score = score;
    }

    @Builder
    public ScoreDto(String studentName, String subjectName, double avg){
        this.studentName = studentName;
        this.subjectName = subjectName;
        this.avgScore = avg;
    }
}
