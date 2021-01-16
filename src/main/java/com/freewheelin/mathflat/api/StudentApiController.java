package com.freewheelin.mathflat.api;

import com.freewheelin.mathflat.common.CommonUtils;
import com.freewheelin.mathflat.common.CustomResponse;
import com.freewheelin.mathflat.domain.Student;
import com.freewheelin.mathflat.service.StudentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/student/")
public class StudentApiController {

    private final StudentService studentService;

    @PostMapping("createStudent")
    public ResponseEntity create(@Valid StudentForm form, BindingResult result){
        ResponseEntity defaultMessage = CommonUtils.responseByBindingResult(result);
        if (defaultMessage != null) return defaultMessage;
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
    public ResponseEntity update(@PathVariable Long id,
                                 @Valid StudentForm form, BindingResult result){
        ResponseEntity defaultMessage = CommonUtils.responseByBindingResult(result);
        if (defaultMessage != null) return defaultMessage;

        form.setId(id);
        return studentService.update(form);
    }



}

