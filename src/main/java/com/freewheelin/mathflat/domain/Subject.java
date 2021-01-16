package com.freewheelin.mathflat.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
@Setter
@Getter
public class Subject  extends Base{

    @Id
    @GeneratedValue
    @Column(name = "subject_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name="student_id")
    private Student student;

    private String name;

    private int score;

}
