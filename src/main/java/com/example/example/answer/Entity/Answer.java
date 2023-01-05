package com.example.example.answer.Entity;

import com.example.example.question.Entity.Question;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String content;

    @Column
    private String nickname;

    @Column
    private LocalDateTime createdAt = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;

    public void createAnswer(String content, String nickname){
        this.content = content;
        this.nickname = nickname;
    }

    public void updateAnswer(String content, String nickname){
        this.content = content;
        this.nickname = nickname;
    }

    public void setQuestion(Question question){
        if(this.question != null){
            this.question.getAnswers().remove(this);
        }
        this.question = question;
        question.getAnswers().add(this);
    }
}
