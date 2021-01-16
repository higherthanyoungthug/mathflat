package com.freewheelin.mathflat.repository;

import com.freewheelin.mathflat.common.CustomResponse;
import com.freewheelin.mathflat.domain.QScore;
import com.freewheelin.mathflat.domain.QStudent;
import com.freewheelin.mathflat.domain.QSubject;
import com.freewheelin.mathflat.domain.Score;
import com.freewheelin.mathflat.dto.ScoreDto;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ScoreRepository {
    private final EntityManager em;
    private final JPAQueryFactory jpaQueryFactory;

    public ResponseEntity save(Score score) {
        em.persist(score);
        return ResponseEntity
                .ok()
                .body(new CustomResponse("점수 추가 완료", 200));
    }

    public Score findExistScore(Score score) {
        QScore qScore = QScore.score1;

        return jpaQueryFactory.selectFrom(qScore)
                .where(qScore.student.eq(score.getStudent())
                        .and(qScore.subject.eq(score.getSubject())))
                .fetchOne();
    }

    public List<ScoreDto> findAll() {
        QScore qScore = QScore.score1;
        QStudent student = QStudent.student;
        QSubject subject = QSubject.subject;

        return jpaQueryFactory.select(
                Projections.constructor(ScoreDto.class,
                        qScore.id,
                        student.name,
                        subject.name,
                        qScore.score))
                .from(qScore)
                .join(student).on(qScore.student.id.eq(student.id))
                .join(subject).on(qScore.subject.id.eq(subject.id))
                .fetch();
    }

    public List<ScoreDto> getScoresByStudent(Long id) {
        QScore qScore = QScore.score1;
        QStudent student = QStudent.student;
        QSubject subject = QSubject.subject;

        return jpaQueryFactory.select(
                Projections.constructor(ScoreDto.class,
                        qScore.id,
                        student.name,
                        subject.name,
                        qScore.score))
                .from(qScore)
                .join(student).on(qScore.student.id.eq(student.id))
                .join(subject).on(qScore.subject.id.eq(subject.id))
                .where(qScore.student.id.eq(id))
                .orderBy(qScore.id.desc())
                .fetch();
    }

    public ScoreDto findOne(Long id) {
        QScore qScore = QScore.score1;
        QStudent student = QStudent.student;
        QSubject subject = QSubject.subject;

        return jpaQueryFactory.select(
                Projections.constructor(ScoreDto.class,
                        qScore.id,
                        student.name,
                        subject.name,
                        qScore.score))
                .where(qScore.id.eq(id))
                .join(student).on(qScore.student.id.eq(student.id))
                .join(subject).on(qScore.subject.id.eq(subject.id))
                .fetchOne();
    }

    public List<ScoreDto> getSubjectAvg(Long id) {
        QScore qScore = QScore.score1;
        QStudent student = QStudent.student;
        QSubject subject = QSubject.subject;

        return jpaQueryFactory.select(
                Projections.constructor(ScoreDto.class,
                        qScore.id,
                        student.name,
                        subject.name,
                        qScore.score))
                .from(qScore)
                .join(student).on(qScore.student.id.eq(student.id))
                .join(subject).on(qScore.subject.id.eq(subject.id))
                .where(qScore.subject.id.eq(id))
                .orderBy(qScore.id.desc())
                .fetch();
    }
}
