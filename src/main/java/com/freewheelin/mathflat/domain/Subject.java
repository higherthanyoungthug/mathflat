package com.freewheelin.mathflat.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.freewheelin.mathflat.api.SubjectForm;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Entity
@Setter
@Getter
public class Subject  extends Base{

    @Id
    @GeneratedValue
    @Column(name = "subject_id")
    private Long id;

    private String name;

    private String subjectCode;

    @JsonIgnore
    @OneToMany(mappedBy = "subject", cascade = CascadeType.ALL)
    private List<Score> scores = new ArrayList<>();

    public static Subject createSubject(SubjectForm form, Student ...students){
        Subject subject = new Subject();
        if(form.getId() != null){
            subject.setId(form.getId());
        }
        subject.setName(form.getName());
        subject.setSubjectCode(form.getSubjectCode());

        return subject;
    }

}
