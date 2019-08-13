package com.example.recyclerholder

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context

@Database(entities = [Note::class],  version = 1 , exportSchema = false)
abstract class NoteDatabase : RoomDatabase() {
    companion object {
        private var noteDatabase: NoteDatabase? = null
        private val DBName = "notes2.db"
        private val LOCK = Any()
        fun getInstance(context: Context): NoteDatabase? {
            synchronized(LOCK) {
                if (noteDatabase == null) {
                    noteDatabase = Room.databaseBuilder(context, NoteDatabase::class.java, DBName)
                        .build()
                }
            }
            return noteDatabase
        }
    }
    public abstract fun notesDao():NotesDAO

}
