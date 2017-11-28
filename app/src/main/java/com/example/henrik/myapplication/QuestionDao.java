package com.example.henrik.myapplication;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * Created by Henrik on 11/24/2017.
 */
@Dao
public interface QuestionDao {
    @Query("SELECT * FROM question")
    List<Question> getAll();
    @Insert
    void insertAll(Question... question);
    @Delete
    void delete(Question question);
}
