package com.freewheelin.mathflat.service;

import com.freewheelin.mathflat.api.StudentForm;
import com.freewheelin.mathflat.common.CustomResponse;
import com.freewheelin.mathflat.domain.Student;
import com.freewheelin.mathflat.domain.Subject;
import com.freewheelin.mathflat.dto.StudentDto;
import com.freewheelin.mathflat.repository.StudentRepository;
import com.freewheelin.mathflat.repository.SubjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StudentService {

    private final StudentRepository studentRepository;

    @Transactional
    public ResponseEntity create(StudentForm form){
        Student student = Student.createStudent(form);
        ResponseEntity isValid = validStudent(student);

        if(isValid.getStatusCode().isError()){
            return isValid;
        }

        return studentRepository.create(student);
    }

    private ResponseEntity validStudent(Student student) {
        String phoneNumber = student.getPhoneNumber();

        List<StudentDto> findMembers = studentRepository.findByPhoneNumber(phoneNumber);
        if(!findMembers.isEmpty()){
            return ResponseEntity
                    .badRequest()
                    .body(new CustomResponse("이미 존재하는 학생입니다."));
        }

        return ResponseEntity.ok().build();
    }

    public List<StudentDto> studentList() {
        return studentRepository.findAll();
    }

    public Student getStudent(Long id){
        return studentRepository.getStudent(id);
    }

    @Transactional
    public ResponseEntity update(StudentForm form) {
        Student student = Student.createStudent(form);
        Student getOne = studentRepository.getStudent(student.getId());

        if (getOne == null){
            return ResponseEntity
                    .badRequest()
                    .body(new CustomResponse("학생정보를 찾을 수 없습니다.", HttpStatus.NO_CONTENT.value()));
        }

        getOne.setName(student.getName());
        getOne.setPhoneNumber(student.getPhoneNumber());

        return ResponseEntity
                .ok()
                .body(new CustomResponse("수정이 완료 됐습니다.", 200));
    }

    public ResponseEntity findOne(Long id) {
        StudentDto one = studentRepository.findOne(id);
        if (one == null){
            return ResponseEntity
                    .badRequest()
                    .body(new CustomResponse("학생정보가 없습니다.", 400));
        }
        return ResponseEntity.ok(one);
    }
}
