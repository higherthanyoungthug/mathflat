package com.freewheelin.mathflat.api;

import com.freewheelin.mathflat.domain.Student;
import com.freewheelin.mathflat.service.StudentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/student/")
public class StudentApiController {

    private final StudentService studentService;

    @PostMapping("createStudent")
    public ResponseEntity create(@Valid StudentForm form){
        return studentService.create(form);
    }

    @GetMapping("studentList")
    public List<Student> studentList(){
        return studentService.studentList();
    }

    @GetMapping("{id}")
    public Student getStudent(@PathVariable Long id){
        return studentService.getStudent(id);
    }

    @PutMapping("{id}")
    public ResponseEntity update(@Valid StudentForm form){
        return studentService.update(form);
    }

}

