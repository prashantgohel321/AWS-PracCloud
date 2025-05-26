package com.example.learningapptry2.ui.quiz;

import java.util.List;

public class QuizQuestion {
    private String question;
    private List<String> options;
    private String correctAnswer; // ✅ Add correct answer field

    // ✅ Constructor
    public QuizQuestion(String question, List<String> options, String correctAnswer) {
        this.question = question;
        this.options = options;
        this.correctAnswer = correctAnswer;
    }

    // ✅ Getter methods
    public String getQuestion() {
        return question;
    }

    public List<String> getOptions() {
        return options;
    }

    public String getCorrectAnswer() { // ✅ This method was missing
        return correctAnswer;
    }
}
