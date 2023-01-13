package com.example.example.answer.Entity;

import com.example.example.global.BaseTime;
import com.example.example.question.Entity.Question;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor
public class Answer extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String content;

    @Column
    private String nickname;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id")
    private Question question;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Answer parent;

    @OneToMany(mappedBy = "parent", orphanRemoval = true)
    private List<Answer> children = new ArrayList<>();

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

    public void setParent(Answer parent){
        if(this.parent != null){
            this.parent.getChildren().remove(this);
        }
        this.parent = parent;
        parent.getChildren().add(this);
    }
}
