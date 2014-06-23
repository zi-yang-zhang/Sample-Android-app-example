package com.example.example;

/**
 * Created by ziyang on 6/19/14.
 */
public class QuestionAnswerPair {
    private String question;
    private String rightAnswer;
    private String wrongAnswer;
    private int id;

    public QuestionAnswerPair(){

    }
    public QuestionAnswerPair(String question, String rightAnswer, String wrongAnswer){
        this.question=question;
        this.rightAnswer = rightAnswer;
        this.wrongAnswer = wrongAnswer;
    }
    public QuestionAnswerPair(int id, String question, String rightAnswer, String wrongAnswer){
        this.id = id;
        this.question=question;
        this.rightAnswer = rightAnswer;
        this.wrongAnswer = wrongAnswer;
    }
    public int getID(){
        return this.id;
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
    public void setID(int id){
        this.id = id;
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

    @Override
    public String toString(){
        return "Question: " + this.question + ", Right Answer: " + this.rightAnswer + ", Wrong Answer: " + this.wrongAnswer;
    }
}
