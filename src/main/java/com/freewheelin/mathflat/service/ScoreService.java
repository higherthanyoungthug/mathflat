package com.freewheelin.mathflat.service;

import com.freewheelin.mathflat.api.ScoreForm;
import com.freewheelin.mathflat.common.CustomResponse;
import com.freewheelin.mathflat.domain.Score;
import com.freewheelin.mathflat.domain.Student;
import com.freewheelin.mathflat.domain.Subject;
import com.freewheelin.mathflat.dto.ScoreDto;
import com.freewheelin.mathflat.repository.ScoreRepository;
import com.freewheelin.mathflat.repository.StudentRepository;
import com.freewheelin.mathflat.repository.SubjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ScoreService {

    private final ScoreRepository scoreRepository;
    private final StudentRepository studentRepository;
    private final SubjectRepository subjectRepository;

    @Transactional
    public ResponseEntity create(ScoreForm form){
        Score score = makeScoreObject(form);
        Score existScore = scoreRepository.findExistScore(score);
        if(existScore != null){
            existScore.setScore(form.getScore());
            return ResponseEntity
                    .ok()
                    .body(new CustomResponse("학생의 기존 점수가 수정됐습니다."));
        }
        return scoreRepository.save(score);
    }

    public Score findExistScore(ScoreForm form) {
        Score score = makeScoreObject(form);
        return scoreRepository.findExistScore(score);
    }

    public List<ScoreDto> allScores() {
        return scoreRepository.findAll();
    }

    public List<ScoreDto> getScoresByStudent(Long id) {
//        ScoreDto one = scoreRepository.findOne(id);
        return scoreRepository.getScoresByStudent(id);
    }

    private Score makeScoreObject(ScoreForm form) {
        Student student = studentRepository.getStudent(form.getStudentId());
        Subject subject = subjectRepository.getSubject(form.getSubjectId());
        int formScore = form.getScore();
        return Score.createScore(formScore, student, subject);
    }

    public ResponseEntity getAvgScore(Long id) {
        List<ScoreDto> scoresByStudent = scoreRepository.getScoresByStudent(id);
        if(!scoresByStudent.isEmpty()){
            String name = scoresByStudent.stream()
                    .findFirst()
                    .map(ScoreDto::getStudentName)
                    .get();

            double v = scoresByStudent.stream()
                    .mapToInt(ScoreDto::getScore)
                    .average()
                    .orElse(Double.NaN);

            ScoreDto build = ScoreDto.builder()
                    .studentName(name)
                    .avg(v)
                    .build();
            return ResponseEntity.ok(build);
        }
        return ResponseEntity
                .badRequest()
                .body(new CustomResponse("해당 학생의 점수가 없습니다."));
    }

    public ResponseEntity getSubjectAvg(Long id) {
        List<ScoreDto> subjectAvg = scoreRepository.getSubjectAvg(id);
        if (!subjectAvg.isEmpty()){
            String name = subjectAvg.stream()
                    .findFirst()
                    .map(ScoreDto::getSubjectName)
                    .get();

            double v = subjectAvg.stream()
                    .mapToInt(ScoreDto::getScore)
                    .average()
                    .orElse(Double.NaN);

            ScoreDto build = ScoreDto.builder()
                    .subjectName(name)
                    .avg(v)
                    .build();
            return ResponseEntity.ok(build);
        }
        return ResponseEntity
                .badRequest()
                .body(new CustomResponse("해당 과목의 점수가 없습니다."));
    }
}
