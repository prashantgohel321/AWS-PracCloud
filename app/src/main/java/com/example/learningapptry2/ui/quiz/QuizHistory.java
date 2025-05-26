package com.example.learningapptry2.ui.quiz;

public class QuizHistory {
    private String date;
    private int score;

    public QuizHistory(String date, int score) {
        this.date = date;
        this.score = score;
    }

    public String getDate() {
        return date;
    }

    public int getScore() {
        return score;
    }
}

