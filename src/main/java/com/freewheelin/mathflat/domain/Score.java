package com.freewheelin.mathflat.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.freewheelin.mathflat.api.ScoreForm;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@Setter
public class Score extends Base{

    @Id
    @GeneratedValue
    @Column(name = "score_id")
    private Long id;

    @JsonIgnore
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "student_id")
    private Student student;

    @JsonIgnore
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "subject_id")
    private Subject subject;

    private int score = 0;
    private int subjectCount;

    public static Score createScore(int formScore, Student student, Subject subject) {
        Score score = new Score();
        score.setScore(formScore);
        score.setStudent(student);
        score.setSubject(subject);
        return score;
    }

}
