package com.freewheelin.mathflat.api;

import com.freewheelin.mathflat.common.CommonUtils;
import com.freewheelin.mathflat.common.CustomResponse;
import com.freewheelin.mathflat.domain.Score;
import com.freewheelin.mathflat.dto.ScoreDto;
import com.freewheelin.mathflat.service.ScoreService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/score/")
public class ScoreApiController {

    private final ScoreService scoreService;
    private final ModelMapper modelMapper;

    @PostMapping("createScore")
    public ResponseEntity create(@Valid ScoreForm form, BindingResult result){
        ResponseEntity defaultMessage = CommonUtils.responseByBindingResult(result);
        if (defaultMessage != null) return defaultMessage;
        return scoreService.create(form);
    }

    @GetMapping("allScores")
    public List<ScoreDto> allScores(){
        return scoreService.allScores();
    }

    @GetMapping("{id}")
    public List<ScoreDto> getScoresByStudent(@PathVariable Long id){
        //학생 PK를 받아와야 한다.
        return scoreService.getScoresByStudent(id);
    }

    @GetMapping("{id}/avg")
    public ResponseEntity getAvgScore(@PathVariable Long id){
        //학생 PK를 받아와야 한다.
        return scoreService.getAvgScore(id);
    }

    @GetMapping("{id}/subjectAvg")
    public ResponseEntity getSubjectAvg(@PathVariable Long id){
        //과목 PK를 받아와야 한다.
        return scoreService.getSubjectAvg(id);
    }

    @PutMapping("{id}")
    public ResponseEntity update(ScoreForm form){
        //점수 PK를 받아와야 한다.
        Score existScore = scoreService.findExistScore(form);
        if(existScore == null){
            return ResponseEntity
                    .badRequest()
                    .body(new CustomResponse("점수정보를 찾을 수 없습니다."));
        }else{
            existScore.setScore(form.getScore());
            existScore.setIsDelete(form.getIsDelete());
        }
        return ResponseEntity
                .ok()
                .body(new CustomResponse("수정이 완료됐습니다."));
    }

}
