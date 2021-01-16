package com.freewheelin.mathflat.repository;


import com.freewheelin.mathflat.common.CustomResponse;
import com.freewheelin.mathflat.domain.QSubject;
import com.freewheelin.mathflat.domain.Subject;
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

    public List<Subject> findAll() {
        QSubject subject = QSubject.subject;
        return jpaQueryFactory
                .selectFrom(subject)
                .where(subject.isDelete.eq("1"))
                .orderBy(subject.id.asc())
                .fetch();
    }

    public Subject getSubject(Long id) {
        QSubject qSubject = QSubject.subject;
        return jpaQueryFactory.selectFrom(qSubject)
                .where(qSubject.id.eq(id))
                .fetchOne();
    }

    public List<Subject> findByCode(Subject subject) {
        QSubject qSubject = QSubject.subject;
        return jpaQueryFactory.selectFrom(qSubject)
                .where(qSubject.subjectCode.eq(subject.getSubjectCode()))
                .fetch();
    }

}
