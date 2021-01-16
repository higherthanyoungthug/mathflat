package com.freewheelin.mathflat.repository;

import com.freewheelin.mathflat.common.CustomResponse;
import com.freewheelin.mathflat.domain.QStudent;
import com.freewheelin.mathflat.domain.Student;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class StudentRepository {

    private final EntityManager em;
    private final JPAQueryFactory jpaQueryFactory;

    public ResponseEntity create(Student student) {
        em.persist(student);
        return ResponseEntity
                .ok()
                .body(new CustomResponse("학생 추가 완료", 200));
    }

    public List<Student> findByPhoneNumber(String pn) {
        QStudent student = QStudent.student;
        return jpaQueryFactory
                .selectFrom(student)
                .where(student.phoneNumber.eq(pn))
                .fetch();
    }

    public List<Student> findAll() {
        QStudent student = QStudent.student;
        return jpaQueryFactory
                .selectFrom(student)
                .where(student.isDelete.eq("1"))
                .orderBy(student.id.asc())
                .fetch();
    }

    public Student getStudent(Long id) {
        QStudent student = QStudent.student;
        return jpaQueryFactory.selectFrom(student)
                .where(student.id.eq(id))
                .fetchOne();
    }

    public void test(){
        QStudent student = QStudent.student;

    }
}
