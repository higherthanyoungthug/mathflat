package com.freewheelin.mathflat.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.freewheelin.mathflat.api.StudentForm;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@Setter
public class Student extends Base{

    @Id
    @GeneratedValue
    @Column(name = "student_id")
    private Long id;

    private String name;

    private String phoneNumber;

    @JsonIgnore
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private List<Score> scores = new ArrayList<>();

    public static Student createStudent(StudentForm form, Subject ...subjects){
        Student student = new Student();

        if(form.getId() != null){
            student.setId(form.getId());
        }

        student.setName(form.getName());
        student.setPhoneNumber(form.getPhoneNumber());

        return student;
    }

}
