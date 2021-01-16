package com.freewheelin.mathflat.repository;

import com.freewheelin.mathflat.api.StudentForm;
import com.freewheelin.mathflat.domain.QStudent;
import com.freewheelin.mathflat.domain.QSubject;
import com.freewheelin.mathflat.domain.Student;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.validation.Valid;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class StudentRepository {

    private final EntityManager em;
    private final JPAQueryFactory jpaQueryFactory;

    public ResponseEntity create(Student student) {
        em.persist(student);
        return new ResponseEntity(HttpStatus.OK);
    }

    public List<Student> findByName(String name) {
        QStudent student = QStudent.student;
        return jpaQueryFactory
                .selectFrom(student)
                .where(student.name.eq(name))
                .fetch();
    }

    public List<Student> findAll() {
        QStudent student = QStudent.student;
        return jpaQueryFactory
                .selectFrom(student)
                .orderBy(student.id.asc())
                .fetch();
    }

    public Student getStudent(Long id) {
        QStudent student = QStudent.student;
        return jpaQueryFactory.selectFrom(student)
                .where(student.id.eq(id))
                .fetchOne();
    }
}
