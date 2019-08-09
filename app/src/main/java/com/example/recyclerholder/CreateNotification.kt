package com.example.recyclerholder

import android.content.ContentValues
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;
import android.view.View
import android.widget.RadioButton
import android.widget.Toast

import kotlinx.android.synthetic.main.content_create_notification.*

class CreateNotification : AppCompatActivity() {
    lateinit var notesDBHelper:NotesDBHelper
    lateinit var database:SQLiteDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.content_create_notification)
        notesDBHelper =  NotesDBHelper(this)
        database = notesDBHelper.writableDatabase
    }

    fun safeNote(view: View){
        val title:String = editTextTitle.text.toString().trim()
        val description:String = editTextDescription.text.toString().trim()
        val dayWeek:String? = spinnerDays.selectedItem.toString()
        val priority:Int? = radioGrupuPriority.checkedRadioButtonId
        val radioButton:RadioButton? = priority?.let { findViewById(it) }
        if(!title.isEmpty() && !description.isEmpty()) {
            var contentValues = ContentValues()
            contentValues.put(NotesEntry.COLUMN_TITILE, title)
            contentValues.put(NotesEntry.COLUMN_DESCRIPTION, description)    //записывать данные
            contentValues.put(NotesEntry.COLUMN_PRIORITY, radioButton?.text.toString())
            contentValues.put(NotesEntry.COLUMN_DAY_OF_WEEK, dayWeek)
            database.insert(NotesEntry.TABLE_NAME, null, contentValues)
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            Toast.makeText(this, "Сохранено!", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Все поля должны быть заполнены", Toast.LENGTH_SHORT).show()
        }
    }


}
