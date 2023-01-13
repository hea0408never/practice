package com.example.example.question.Entity;

import com.example.example.answer.Entity.Answer;
import com.example.example.global.BaseTime;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor
public class Question extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @Column
    private String content;

    @Column
    private String nickname;

    @OneToMany(mappedBy = "question", orphanRemoval = true)
    private List<Answer> answers = new ArrayList<>();

    public void createQuestion(String title, String content, String nickname){
        this.title = title;
        this.content = content;
        this.nickname = nickname;
    }

    public void updateQuestion(String title, String content, String nickname){
        this.title = title;
        this.content = content;
        this.nickname = nickname;
    }

}
