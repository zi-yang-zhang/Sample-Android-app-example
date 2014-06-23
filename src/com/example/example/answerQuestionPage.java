package com.example.example;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.*;
import android.widget.*;

import java.util.Random;

/**
 * Created by ziyang on 6/19/14.
 */
public class answerQuestionPage extends Activity {
    RadioButton answerOneButton;
    RadioButton answerTwoButton;
    RadioGroup buttonGroup;
    TextView questionText;
    ImageView faces;
    String answer;
    QuestionAnswerPair question;
    QuestionDatabaseHelper dbHelper;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.answer_question_page);
        answerOneButton = (RadioButton) findViewById(R.id.answerOneRadioButton);
        answerTwoButton = (RadioButton) findViewById(R.id.answerTwoRadioButton);
        questionText = (TextView) findViewById(R.id.questionTextView);
        buttonGroup = (RadioGroup) findViewById(R.id.buttonGroup);
        faces= (ImageView) findViewById(R.id.faceImageView);
        dbHelper = new QuestionDatabaseHelper(this);
        if(sharedPref.getBoolean("Default Questions Generated", false)==false){
            generateDefaultQuestions();
            sharedPref.edit().putBoolean("Default Questions Generated", true).commit();
        }
        postQuestion();
        answerOneButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    answer = buttonView.getText().toString();
                }
            }
        });
        answerTwoButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    answer = buttonView.getText().toString();
                }
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        QuestionAnswerPair newQuestion = new QuestionAnswerPair();
        switch (item.getItemId()) {
            case R.id.add_question:
                createQuestionDialog();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    public void evaluate(View view) {
        faces.setVisibility(ImageView.VISIBLE);
        if(question.evaluateAnswer(answer)){
            faces.setImageResource(R.drawable.smile_face);
        }else{
            faces.setImageResource(R.drawable.sad_face);
        }
    }

    public void refresh(View view){
        postQuestion();
    }

    public void createQuestionDialog(){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            // Get the layout inflater
            LayoutInflater inflater = this.getLayoutInflater();
            View promptsView = inflater.inflate(R.layout.question_input, null);
            // Inflate and set the layout for the dialog
            // Pass null as the parent view because its going in the dialog layout
            builder.setView(inflater.inflate(R.layout.question_input, null))
                    // Add action buttons
                    .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            Dialog dialogView = (Dialog) dialog;
                            EditText questionField = (EditText) dialogView.findViewById(R.id.questionEditText);
                            EditText rightAnswerField = (EditText) dialogView.findViewById(R.id.rightAnswerEditText);
                            EditText wrongAnswerField = (EditText) dialogView.findViewById(R.id.wrongAnswerEditText);
                            QuestionAnswerPair newQuestion = new QuestionAnswerPair(questionField.getText().toString(),rightAnswerField.getText().toString(),wrongAnswerField.getText().toString());
                            Log.i("new question:", newQuestion.toString());
                            dbHelper.addQuestion(newQuestion);
                            Toast.makeText(getBaseContext(), "Question Added", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });

            builder.create().show();
    }

    public void postQuestion(){
        question = getRandomQuestion();
        Random rand = new Random();
        int choice= rand.nextInt(2);
        switch (choice){
            case 0: answerOneButton.setText(question.getRightAnswer());
                answerTwoButton.setText(question.getWrongAnswer());
                break;

            case 1: answerOneButton.setText(question.getWrongAnswer());
                answerTwoButton.setText(question.getRightAnswer());
                break;
        }
        questionText.setText(question.getQuestion());
        buttonGroup.clearCheck();
        faces.setVisibility(ImageView.INVISIBLE);
    }

    public void generateDefaultQuestions(){
        QuestionAnswerPair question1 = new QuestionAnswerPair("In which direction does the Sun Rise?", "East","West");
        QuestionAnswerPair question2 = new QuestionAnswerPair("Is Simon sexy?", "Yes","No");
        QuestionAnswerPair question3 = new QuestionAnswerPair("Does Simon looks like Andrew?", "Definitely","Not a bit");
        QuestionAnswerPair question4 = new QuestionAnswerPair("Are we awesome?", "Hell Yeah", "Not Really");
        QuestionAnswerPair question5 = new QuestionAnswerPair("Is Simon a Beast?", "Without doubt","Of course not");
        QuestionAnswerPair question6 = new QuestionAnswerPair("Do you understand the tutorial?", "Yes","No");

        dbHelper.addQuestion(question1);
        dbHelper.addQuestion(question2);
        dbHelper.addQuestion(question3);
        dbHelper.addQuestion(question4);
        dbHelper.addQuestion(question5);
        dbHelper.addQuestion(question6);
    }

    public QuestionAnswerPair getRandomQuestion(){
        Random rand = new Random();
        int index = rand.nextInt(dbHelper.getQuestionsCount())+1;
        return dbHelper.getQuestion(index);
    }
}