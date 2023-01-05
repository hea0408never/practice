package com.example.example.question.Controller;

import com.example.example.question.Dto.CreateQuestionDto;
import com.example.example.question.Dto.QuestionAndAnswerDto;
import com.example.example.question.Dto.ResponseQuestionDto;
import com.example.example.question.Dto.UpdateQuestionDto;
import com.example.example.question.Entity.Question;
import com.example.example.question.Mapper.QuestionMapper;
import com.example.example.question.Service.QuestionService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

@RestController
@Validated
@RequestMapping("/api/question")
public class QuestionController {
    private final QuestionService questionService;
    private final QuestionMapper questionMapper;

    public QuestionController(QuestionService questionService, QuestionMapper questionMapper){
        this.questionService = questionService;
        this.questionMapper = questionMapper;
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseQuestionDto> create(@Valid @RequestBody CreateQuestionDto dto){
        Question question = questionService.createQuestion(dto);
        return ResponseEntity.ok(questionMapper.questionToResponseDto(question));
    }

    @GetMapping("/read")
    public ResponseEntity<QuestionAndAnswerDto> read(@Positive @RequestParam long id,
                                                     @Positive @RequestParam(value = "page", defaultValue = "1") int page,
                                                     @Positive @RequestParam(value = "size", defaultValue = "5") int size,
                                                     @RequestParam(value = "sort", defaultValue = "createdAt") String sort){
        QuestionAndAnswerDto questionAndAnswerDto = questionService.questionAndAnswerDto(id,page-1,size,sort);
        return ResponseEntity.ok(questionAndAnswerDto);
    }

    @PatchMapping("/update")
    public ResponseEntity<ResponseQuestionDto> update(@Valid @RequestBody UpdateQuestionDto dto){
        Question question = questionService.updateQuestion(dto);
        return ResponseEntity.ok(questionMapper.questionToResponseDto(question));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> delete(@Positive @RequestParam long id){
        questionService.deleteQuestion(id);
        return ResponseEntity.ok("삭제됨");
    }
}
