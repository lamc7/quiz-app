package com.example.henrik.myapplication;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "question")
public class Question {
    @PrimaryKey (autoGenerate = true)
    @ColumnInfo(name = "qid")
    private int qid;

    @ColumnInfo(name = "question")
    private String question;

    @ColumnInfo(name = "answerA")
    private String answerA;

    @ColumnInfo(name = "answerB")
    private String answerB;

    @ColumnInfo(name = "answerC")
    private String answerC;

    @ColumnInfo(name = "answerD")
    private String answerD;

    @ColumnInfo(name = "answerE")
    private String answerE;

    @ColumnInfo(name = "correct")
    private String correct;

    public int getQid(){
        return qid;
    }
    public void setQid(int qid){
        this.qid = qid;
    }
    public String getQuestion(){
        return question;
    }
    public void setQuestion(String question){
        this.question = question;
    }

    public String getAnswerA(){
        return answerA;
    }
    public void setAnswerA(String answerA){
        this.answerA = answerA;
    }
    public String getAnswerB(){
        return answerB;
    }
    public void setAnswerB(String answerB){
        this.answerB = answerB;
    }
    public String getAnswerC(){
        return answerC;
    }
    public void setAnswerC(String answerC){
        this.answerC = answerC;
    }
    public String getAnswerD(){
        return answerD;
    }
    public void setAnswerD(String answerD){
        this.answerD = answerD;
    }
    public String getAnswerE(){
        return answerE;
    }
    public void setAnswerE(String answerE){
        this.answerE = answerE;
    }
    public String getCorrect(){
        return correct;
    }
    public void setCorrect(String correct){
        this.correct = correct;
    }
}
