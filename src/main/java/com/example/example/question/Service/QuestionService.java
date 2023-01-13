package com.example.example.question.Service;

import com.example.example.answer.Entity.Answer;
import com.example.example.answer.Repository.AnswerRepository;
import com.example.example.exception.BusinessLogicException;
import com.example.example.exception.ExceptionCode;
import com.example.example.question.Dto.CreateQuestionDto;
import com.example.example.question.Dto.QuestionAndAnswerDto;
import com.example.example.question.Dto.ResponseQuestionDto;
import com.example.example.question.Dto.UpdateQuestionDto;
import com.example.example.question.Entity.Question;
import com.example.example.question.Mapper.QuestionMapper;
import com.example.example.question.Repository.QuestionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@Slf4j
public class QuestionService {
    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;
    private final QuestionMapper questionMapper;

    public QuestionService(QuestionRepository questionRepository,
                           AnswerRepository answerRepository, QuestionMapper questionMapper){
        this.questionRepository = questionRepository;
        this.answerRepository = answerRepository;
        this.questionMapper = questionMapper;
    }

    public Question createQuestion(CreateQuestionDto dto) {
        Question question = new Question();
        question.createQuestion(dto.getTitle(), dto.getContent(), dto.getNickname());
        return questionRepository.save(question);
    }

    public Question updateQuestion(UpdateQuestionDto dto) {
        Question question = findQuestion(dto.getId());
        question.updateQuestion(dto.getTitle(), dto.getContent(), dto.getNickname());
        return questionRepository.save(question);
    }

    public Question findQuestion(Long id){
        return questionRepository.findById(id).orElseThrow(() -> new BusinessLogicException(ExceptionCode.QUESTION_NOT_FOUND));
    }

    public void deleteQuestion(long id) {
        Question question = findQuestion(id);
        questionRepository.delete(question);
    }

    public QuestionAndAnswerDto questionAndAnswerDto(Long id, int page, int size, String sort){
        Question question = findQuestion(id);
        QuestionAndAnswerDto questionAndAnswerDto = new QuestionAndAnswerDto();
        List<Answer> answers = question.getAnswers();
        List<QuestionAndAnswerDto.AnswerCard> cards = new ArrayList<>();
        for(Answer answer : answers){
            cards.add(new QuestionAndAnswerDto.AnswerCard(answer));
        }
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by(sort).descending());
        int start = (int)pageRequest.getOffset();
        int end = Math.min((start+pageRequest.getPageSize()),cards.size());
        Page<QuestionAndAnswerDto.AnswerCard> cardPage = new PageImpl<>(cards.subList(start,end),pageRequest,cards.size());
        questionAndAnswerDto.setTitle(question.getTitle());
        questionAndAnswerDto.setContent(question.getContent());
        questionAndAnswerDto.setNickname(question.getNickname());
        questionAndAnswerDto.setCreatedAt(question.getCreatedAt());
        questionAndAnswerDto.setAnswers(cardPage);
        return questionAndAnswerDto;
    }

    public Page<ResponseQuestionDto> listQuestion(int page, int size, String sort) {
        List<Question> questions = questionRepository.findAll();
        List<ResponseQuestionDto> questionDtoList = new ArrayList<>();
        for (Question question : questions){
            questionDtoList.add(questionMapper.questionToResponseDto(question));
        }
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by(sort).descending());
        int start = (int)pageRequest.getOffset();
        int end = Math.min((start+pageRequest.getPageSize()),questionDtoList.size());
        return new PageImpl<>(questionDtoList.subList(start,end),pageRequest,questionDtoList.size());
    }
}
