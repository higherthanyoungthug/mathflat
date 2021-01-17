package com.freewheelin.mathflat.repository;

import com.freewheelin.mathflat.common.CustomResponse;
import com.freewheelin.mathflat.domain.QStudent;
import com.freewheelin.mathflat.domain.Student;
import com.freewheelin.mathflat.dto.StudentDto;
import com.querydsl.core.types.Projections;
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

    public List<StudentDto> findByPhoneNumber(String pn) {
        QStudent student = QStudent.student;
        return jpaQueryFactory
                .select(Projections.constructor(
                        StudentDto.class,
                        student.id,
                        student.name,
                        student.phoneNumber
                ))
                .from(student)
                .where(student.phoneNumber.eq(pn)
                        .and(student.isDelete.eq("1")))
                .fetch();
    }

    public List<StudentDto> findAll() {
        QStudent student = QStudent.student;
        return jpaQueryFactory
                .select(Projections.constructor(
                        StudentDto.class,
                        student.id,
                        student.name,
                        student.phoneNumber
                ))
                .from(student)
                .where(student.isDelete.eq("1"))
                .fetch();
    }

    public StudentDto findOne(Long id) {
        QStudent student = QStudent.student;
        return jpaQueryFactory
                .select(Projections.constructor(
                        StudentDto.class,
                        student.id,
                        student.name,
                        student.phoneNumber
                ))
                .from(student)
                .where(student.id.eq(id))
                .fetchOne();
    }

    public Student getStudent(Long id) {
        QStudent student = QStudent.student;
        return jpaQueryFactory.selectFrom(student)
                .where(student.id.eq(id))
                .fetchOne();
    }

    public void test() {
        QStudent student = QStudent.student;

    }
}
