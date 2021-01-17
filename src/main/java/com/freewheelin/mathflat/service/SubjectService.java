package com.freewheelin.mathflat.service;

import com.freewheelin.mathflat.api.SubjectForm;
import com.freewheelin.mathflat.common.CustomResponse;
import com.freewheelin.mathflat.domain.Subject;
import com.freewheelin.mathflat.dto.StudentDto;
import com.freewheelin.mathflat.dto.SubjectDto;
import com.freewheelin.mathflat.repository.SubjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SubjectService {

    private final SubjectRepository subjectRepository;

    @Transactional
    public ResponseEntity create(SubjectForm form){
        Subject subject = Subject.createSubject(form);
        ResponseEntity isValid = validSubject(subject);

        if(isValid.getStatusCode().isError()){
            return isValid;
        }

        return subjectRepository.create(subject);
    }

    private ResponseEntity validSubject(Subject subject) {
        List<Subject> byCode = subjectRepository.findByCode(subject);
        if(!byCode.isEmpty()){
            return ResponseEntity
                    .badRequest()
                    .body(new CustomResponse("과목코드가 중복됩니다."));
        }
        return ResponseEntity.ok().build();
    }

    public List<SubjectDto> subjectList(){
        return subjectRepository.findAll();
    }

    public ResponseEntity<? extends Object> findOne(Long id) {
        SubjectDto one = subjectRepository.findOne(id);
        if (one == null){
            return ResponseEntity
                    .badRequest()
                    .body(new CustomResponse("과목정보가 없습니다.", 400));
        }
        return ResponseEntity.ok(one);
    }

    public Subject getSubject(Long id) {
        return subjectRepository.getSubject(id);
    }

    @Transactional
    public ResponseEntity update(SubjectForm form) {
        Subject subject = Subject.createSubject(form);
        Subject findOne = subjectRepository.getSubject(subject.getId());
        if (findOne == null){
            return ResponseEntity
                    .badRequest()
                    .body(new CustomResponse("과목정보를 찾을 수 없습니다.", HttpStatus.NO_CONTENT.value()));
        }

        findOne.setName(subject.getName());

        return ResponseEntity
                .ok()
                .body(new CustomResponse("수정이 완료 됐습니다.", 200));
    }
}
