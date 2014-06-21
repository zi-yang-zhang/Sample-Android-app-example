package com.example.example;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

/**
 * Created by ziyang on 6/19/14.
 */
public class answerQuestionPage extends Activity {
    RadioButton answerOneButton;
    RadioButton answerTwoButton;
    QuestionAnswerGenerator questionGenerator;
    RadioGroup buttonGroup;
    TextView questionText;
    ImageView faces;
    String answer;
    QuestionAnswerPair question;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.answer_question_page);

        answerOneButton = (RadioButton) findViewById(R.id.answerOneRadioButton);
        answerTwoButton = (RadioButton) findViewById(R.id.answerTwoRadioButton);
        questionText = (TextView) findViewById(R.id.questionTextView);
        buttonGroup = (RadioGroup) findViewById(R.id.buttonGroup);
        faces= (ImageView) findViewById(R.id.faceImageView);
        postQuestion();
        final Button newQuestion = (Button) findViewById(R.id.restart_button);
        newQuestion.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                postQuestion();
            }
        });
        final Button evaluate = (Button) findViewById(R.id.evaluate_button);
        newQuestion.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                evaluate();
            }
        });

    }
    public void evaluate() {
        answer = (String) answerOneButton.getText();
        if(question.evaluateAnswer(answer)){
            faces.setImageResource(R.drawable.smile_face);
        }else{
            faces.setImageResource(R.drawable.sad_face);
        }
    }


    public void postQuestion(){
        questionGenerator = new QuestionAnswerGenerator();
        question = questionGenerator.getRandomQuestion();
        answerOneButton.setText(question.getRightAnswer());
        answerTwoButton.setText(question.getWrongAnswer());
        questionText.setText(question.getQuestion());
        answerOneButton.setChecked(false);
        answerTwoButton.setChecked(false);
    }


}