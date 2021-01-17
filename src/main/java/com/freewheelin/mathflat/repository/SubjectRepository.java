package com.freewheelin.mathflat.repository;


import com.freewheelin.mathflat.common.CustomResponse;
import com.freewheelin.mathflat.domain.QSubject;
import com.freewheelin.mathflat.domain.Subject;
import com.freewheelin.mathflat.dto.StudentDto;
import com.freewheelin.mathflat.dto.SubjectDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class SubjectRepository {

    private final EntityManager em;
    private final JPAQueryFactory jpaQueryFactory;

    public ResponseEntity create(Subject subject) {
        em.persist(subject);
        return ResponseEntity
                .ok()
                .body(new CustomResponse("과목 추가 완료", 200));
    }

    public List<SubjectDto> findAll() {
        QSubject subject = QSubject.subject;
        return jpaQueryFactory
                .select(
                        Projections.constructor(SubjectDto.class,
                                subject.id,
                                subject.name,
                                subject.subjectCode)
                )
                .from(subject)
                .where(subject.isDelete.eq("1"))
                .orderBy(subject.id.asc())
                .fetch();
    }

    public SubjectDto findOne(Long id) {
        QSubject subject = QSubject.subject;
        return jpaQueryFactory
                .select(
                        Projections.constructor(SubjectDto.class,
                                subject.id,
                                subject.name,
                                subject.subjectCode)
                )
                .from(subject)
                .where(subject.isDelete.eq("1")
                        .and(subject.id.eq(id)))
                .orderBy(subject.id.asc())
                .fetchOne();
    }

    public Subject getSubject(Long id) {
        QSubject qSubject = QSubject.subject;
        return jpaQueryFactory.selectFrom(qSubject)
                .where(qSubject.id.eq(id)
                        .and(qSubject.isDelete.eq("1")))
                .fetchOne();
    }

    public List<Subject> findByCode(Subject subject) {
        QSubject qSubject = QSubject.subject;
        return jpaQueryFactory.selectFrom(qSubject)
                .where(qSubject.subjectCode.eq(subject.getSubjectCode())
                        .and(qSubject.isDelete.eq("1")))
                .fetch();
    }

}
