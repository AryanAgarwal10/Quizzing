package com.example.quizzing;

public class QuizzModel {
    private boolean answer;
    private int question;

    public QuizzModel(boolean answer, int question) {
        this.answer = answer;
        this.question = question;
    }

    public boolean isAnswer() {
        return answer;
    }

    public void setAnswer(boolean answer) {
        this.answer = answer;
    }

    public int getQuestion() {
        return question;
    }

    public void setQuestion(int question) {
        this.question = question;
    }
}
