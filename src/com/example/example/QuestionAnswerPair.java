package com.example.example;

/**
 * Created by ziyang on 6/19/14.
 */
public class QuestionAnswerPair {
    private String question;
    private String rightAnswer;
    private String wrongAnswer;


    public QuestionAnswerPair(String question, String rightAnswer, String wrongAnswer){
        this.question=question;
        this.rightAnswer = rightAnswer;
        this.wrongAnswer = wrongAnswer;
    }
    public String getQuestion(){
        return this.question;
    }

    public String getRightAnswer(){
        return this.rightAnswer;
    }
    public String getWrongAnswer(){
        return this.wrongAnswer;
    }
    public void setQuestion(String question){
        this.question=question;
    }
    public void setRightAnswer(String rightAnswer){
        this.rightAnswer = rightAnswer;
    }
    public void setWrongAnswer(String wrongAnswer){
        this.wrongAnswer = wrongAnswer;
    }
    public boolean evaluateAnswer(String answer){
        if(answer.equalsIgnoreCase(this.rightAnswer)){
            return true;
        }
        return false;
    }
}
