package com.example.recyclerholder

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query

@Dao
interface NotesDAO {  //data access object
    @Query("SELECT * FROM notes ORDER BY priority")
    fun getAllNotes():LiveData<List<Note>>

    @Insert
    fun insertNote(note:Note)

    @Delete
    fun deleteNote(note:Note)

    @Query("DELETE FROM notes")
    fun deleteAllNotes()
}