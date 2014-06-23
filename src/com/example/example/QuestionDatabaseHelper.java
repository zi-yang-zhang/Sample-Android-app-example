package com.example.example;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ZiYang on 2014-06-21.
 */
public class QuestionDatabaseHelper extends SQLiteOpenHelper {

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "exampleDatabase";

    // Contacts table name
    private static final String TABLE_QUESTIONS  = "questions";

    // Contacts Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_QUESTION = "question";
    private static final String KEY_RIGHT_ANSWER = "right_answer";
    private static final String KEY_WRONG_ANSWER = "wrong_answer";

    public QuestionDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_QUESTIONS_TABLE = "CREATE TABLE " + TABLE_QUESTIONS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_QUESTION + " TEXT,"
                + KEY_RIGHT_ANSWER + " TEXT," + KEY_WRONG_ANSWER + " TEXT" + ")";
        db.execSQL(CREATE_QUESTIONS_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_QUESTIONS);

        // Create tables again
        onCreate(db);
    }

    public void addQuestion(QuestionAnswerPair question){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_QUESTION, question.getQuestion());
        values.put(KEY_RIGHT_ANSWER, question.getRightAnswer());
        values.put(KEY_WRONG_ANSWER, question.getWrongAnswer());
        // Inserting Row
        db.insert(TABLE_QUESTIONS, null, values);
        db.close(); // Closing database connection
    }

    // Getting single question
    public QuestionAnswerPair getQuestion(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_QUESTIONS, new String[] { KEY_ID,
                        KEY_QUESTION, KEY_RIGHT_ANSWER, KEY_WRONG_ANSWER }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        QuestionAnswerPair question = new QuestionAnswerPair(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2), cursor.getString(3));
        // return contact
        return question;
    }
    // Getting All Questions
    public List<QuestionAnswerPair> getAllQuestions() {
        List<QuestionAnswerPair> questionList = new ArrayList<QuestionAnswerPair>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_QUESTIONS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                QuestionAnswerPair question = new QuestionAnswerPair();
                question.setID(Integer.parseInt(cursor.getString(0)));
                question.setQuestion(cursor.getString(1));
                question.setRightAnswer(cursor.getString(2));
                question.setWrongAnswer(cursor.getString(3));
                // Adding question to list
                questionList.add(question);
            } while (cursor.moveToNext());
        }

        // return question list
        return questionList;
    }
    // Getting questions Count
    public int getQuestionsCount() {

        String countQuery = "SELECT  * FROM " + TABLE_QUESTIONS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int questionCounts = cursor.getCount();
        cursor.close();

        // return count
        return questionCounts;
    }
    // Updating single question
    public int updateContact(QuestionAnswerPair question) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_QUESTION, question.getQuestion());
        values.put(KEY_RIGHT_ANSWER, question.getRightAnswer());
        values.put(KEY_WRONG_ANSWER, question.getWrongAnswer());

        // updating row
        return db.update(TABLE_QUESTIONS, values, KEY_ID + " = ?",
                new String[] { String.valueOf(question.getID()) });
    }

    // Deleting single question
    public void deleteContact(QuestionAnswerPair question) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_QUESTIONS, KEY_ID + " = ?",
                new String[] { String.valueOf(question.getID()) });
        db.close();
    }

}
