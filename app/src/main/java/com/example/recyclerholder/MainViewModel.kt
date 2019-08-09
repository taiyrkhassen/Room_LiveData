package com.example.recyclerholder

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.os.AsyncTask

class MainViewModel(application: Application) : AndroidViewModel(application) {
    companion object {
        lateinit var database: NoteDatabase
        lateinit var notes: LiveData<List<Note>>
    }
    init {
        database = NoteDatabase.getInstance(getApplication())!!
        notes = database.notesDao().getAllNotes()
    }

    fun insertNotes(note:Note){
        insertTask().execute(note)
    }

    fun deleteNote(note:Note){
        deleteTask().execute(note)
    }

    private class insertTask: AsyncTask<Note, Void, Void>() {
        override fun doInBackground(vararg params: Note?): Void? {
            if(params.size>0 && params!=null){
                params[0]?.let { database.notesDao().insertNote(it) }
            }
            return null
        }

    }
    private class deleteTask: AsyncTask<Note, Void, Void>() {
        override fun doInBackground(vararg params: Note?): Void? {
            params[0]?.let { database.notesDao().deleteNote(it) }
            return null
        }
    }

}