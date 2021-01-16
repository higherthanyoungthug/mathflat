package com.freewheelin.mathflat.api;

import com.freewheelin.mathflat.common.CommonUtils;
import com.freewheelin.mathflat.common.CustomResponse;
import com.freewheelin.mathflat.domain.Subject;
import com.freewheelin.mathflat.service.SubjectService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/subject/")
public class SubjectApiController {
    
    private final SubjectService subjectService;
    
    @PostMapping("createSubject")
    public ResponseEntity create(@Valid SubjectForm form, BindingResult result){
        ResponseEntity defaultMessage = CommonUtils.responseByBindingResult(result);
        if (defaultMessage != null) return defaultMessage;
        return subjectService.create(form);
    }

    @GetMapping("subjectList")
    public List<Subject> subjectList(){
        return subjectService.subjectList();
    }

    @GetMapping("{id}")
    public Subject getSubject(@PathVariable Long id){
        return subjectService.getSubject(id);
    }

    @PutMapping("{id}")
    public ResponseEntity update(@PathVariable Long id,
                                 SubjectForm form){
        form.setId(id);
        return subjectService.update(form);
    }
    
}
