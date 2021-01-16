package com.freewheelin.mathflat.domain;

import com.freewheelin.mathflat.api.StudentForm;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Student extends Base{

    @Id
    @GeneratedValue
    @Column(name = "student_id")
    private Long id;

    private String name;

    @OneToMany(mappedBy = "student")
    private List<Subject> subjectList = new ArrayList<>();

    // 생성 메소드
    public static Student createStudent(StudentForm form, Subject ...subjects){
        Student student = new Student();

        if(form.getId() != null){
            student.setId(form.getId());
        }
        student.setName(form.getName());
        for (Subject subject : subjects){
            student.addSubject(subject);
        }
        return student;
    }

    public void addSubject(Subject subject){
        subjectList.add(subject);
    }
}
