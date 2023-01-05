package com.example.example.answer.Controller;

import com.example.example.answer.Dto.CreateAnswerDto;
import com.example.example.answer.Dto.ResponseAnswerDto;
import com.example.example.answer.Dto.UpdateAnswerDto;
import com.example.example.answer.Entity.Answer;
import com.example.example.answer.Mapper.AnswerMapper;
import com.example.example.answer.Service.AnswerService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

@RestController
@Validated
@RequestMapping("/api/answer")
public class AnswerController {
    private final AnswerService answerService;
    private final AnswerMapper answerMapper;

    public AnswerController(AnswerService answerService, AnswerMapper answerMapper){
        this.answerService = answerService;
        this.answerMapper = answerMapper;
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseAnswerDto> create(@Valid @RequestBody CreateAnswerDto dto){
        Answer answer = answerService.createAnswer(dto);
        return ResponseEntity.ok(answerMapper.answerToResponseDto(answer));
    }

    @PatchMapping("/update")
    public ResponseEntity<ResponseAnswerDto> update(@Valid @RequestBody UpdateAnswerDto dto){
        Answer answer = answerService.updateAnswer(dto);
        return ResponseEntity.ok(answerMapper.answerToResponseDto(answer));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> delete(@Positive @RequestParam long id){
        answerService.deleteAnswer(id);
        return ResponseEntity.ok("삭제됨");
    }
}
