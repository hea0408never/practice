package com.example.example.question.Dto;

import com.example.example.answer.Entity.Answer;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class QuestionAndAnswerDto {
    private String title;
    private String content;
    private String nickname;
    private LocalDateTime createdAt;
    private Page<AnswerCard> answers;

    @Getter
    @NoArgsConstructor
    public static class AnswerCard{
        private Long id;
        private String content;
        private String nickname;
        private LocalDateTime createdAt;

        public AnswerCard(Answer answer){
            this.id = answer.getId();
            this.content = answer.getContent();
            this.nickname = answer.getNickname();
            this.createdAt = answer.getCreatedAt();
        }
    }
}
