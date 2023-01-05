package com.example.example.answer.Service;

import com.example.example.answer.Dto.CreateAnswerDto;
import com.example.example.answer.Dto.UpdateAnswerDto;
import com.example.example.answer.Entity.Answer;
import com.example.example.answer.Repository.AnswerRepository;
import com.example.example.exception.BusinessLogicException;
import com.example.example.exception.ExceptionCode;
import com.example.example.question.Service.QuestionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AnswerService {
    private final AnswerRepository answerRepository;
    private final QuestionService questionService;

    public AnswerService(AnswerRepository answerRepository, QuestionService questionService){
        this.answerRepository = answerRepository;
        this.questionService = questionService;
    }
    public Answer createAnswer(CreateAnswerDto dto) {
        Answer answer = new Answer();
        answer.createAnswer(dto.getContent(), dto.getNickname());
        answer.setQuestion(questionService.findQuestion(dto.getQuestionId()));
        return answerRepository.save(answer);
    }

    public Answer updateAnswer(UpdateAnswerDto dto) {
        Answer answer = findAnswer(dto.getId());
        answer.updateAnswer(dto.getContent(), dto.getNickname());
        return answerRepository.save(answer);
    }

    public Answer findAnswer(Long id) {
        return answerRepository.findById(id).orElseThrow(() -> new BusinessLogicException(ExceptionCode.ANSWER_NOT_FOUND));
    }

    public void deleteAnswer(long id){
        Answer answer = findAnswer(id);
        answerRepository.delete(answer);
    }
}
