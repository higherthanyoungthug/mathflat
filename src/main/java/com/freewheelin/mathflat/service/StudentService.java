package com.freewheelin.mathflat.service;

import com.freewheelin.mathflat.api.StudentForm;
import com.freewheelin.mathflat.common.CustomResponse;
import com.freewheelin.mathflat.domain.Student;
import com.freewheelin.mathflat.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StudentService {

    private final StudentRepository studentRepository;

    @Transactional
    public ResponseEntity create(@Valid StudentForm form){
        Student student = Student.createStudent(form);
        try{
            validStudent(student);
        }catch(IllegalStateException e){
            return ResponseEntity
                    .badRequest()
                    .body(new CustomResponse("이미 존재하는 회원입니다"));
        }
        return studentRepository.create(student);
    }

    private void validStudent(Student student) {
        //Exception
        List<Student> findMembers = studentRepository.findByName(student.getName());
        if(!findMembers.isEmpty()){
            throw new IllegalStateException("이미 존재하는 회원입니다");
        }
    }

    public List<Student> studentList() {
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
                    .body(new CustomResponse("학생정보를 찾을 수 없습니다.", 404));
        }
        getOne.setName(student.getName());
        getOne.setSubjectList(student.getSubjectList());
        getOne.setIsDelete(student.getIsDelete());

        return ResponseEntity.ok().body(new CustomResponse("수정이 완료 됐습니다.", 200));
    }

}
