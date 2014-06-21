package com.example.example;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by ziyang on 6/19/14.
 */
public class QuestionAnswerGenerator {
    private ArrayList<QuestionAnswerPair> questionList= new ArrayList<QuestionAnswerPair>();

    public QuestionAnswerGenerator(){
        QuestionAnswerPair question1 = new QuestionAnswerPair("In which direction does the Sun Rise?", "East","West");
        QuestionAnswerPair question2 = new QuestionAnswerPair("Is Simon sexy?", "Yes","No");
        QuestionAnswerPair question3 = new QuestionAnswerPair("Does Simon looks like Andrew?", "Definitely","Not a bit");
        QuestionAnswerPair question4 = new QuestionAnswerPair("Are we awesome?", "Hell Yeah", "Not Really");
        QuestionAnswerPair question5 = new QuestionAnswerPair("Is Simon a Beast?", "Without doubt","Of course not");
        QuestionAnswerPair question6 = new QuestionAnswerPair("Do you understand the tutorial?", "Yes","No");

        questionList.add(question1);
        questionList.add(question2);
        questionList.add(question3);
        questionList.add(question4);
        questionList.add(question5);
        questionList.add(question6);
    }

    public QuestionAnswerPair getRandomQuestion(){
        Random rand = new Random();
        return questionList.get(rand.nextInt((6 - 1) + 1) + 1);
    }
}
