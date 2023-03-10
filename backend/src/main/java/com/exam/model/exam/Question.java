package com.exam.model.exam;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int quesId;
    @NotNull(message = "content can't be empty")
    private String content;
    @NotNull
    private String image;
    @NotNull
    private String option1;
    @NotNull
    private String option2;
    @NotNull
    private String option3;
    @NotNull
    private String option4;
    @NotNull
    @JsonIgnore
    private String answer;
    @Transient
    private String givenAnswer;
    @ManyToOne(fetch = FetchType.EAGER)
    private Quiz quiz;
}