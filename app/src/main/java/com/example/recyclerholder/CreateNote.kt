package com.example.recyclerholder

import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity;
import android.view.View
import android.widget.RadioButton
import android.widget.Toast

import kotlinx.android.synthetic.main.content_create_notification.*

class CreateNote : AppCompatActivity() {
    lateinit var database:NoteDatabase
    lateinit var viewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.content_create_notification)
        database = NoteDatabase.getInstance(this)!!
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
    }

    fun safeNote(view: View){
        val title:String = editTextTitle.text.toString().trim()
        val description:String = editTextDescription.text.toString().trim()
        val dayWeek:String = spinnerDays.selectedItem.toString()
        val priority:Int? = radioGrupuPriority.checkedRadioButtonId
        val radioButton:RadioButton? = priority?.let { findViewById(it) }
        if(!title.isEmpty() && !description.isEmpty()) {
            viewModel.insertNotes(Note(title, dayWeek, description, radioButton!!.text.toString().toInt()))
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            Toast.makeText(this, "Сохранено!", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Все поля должны быть заполнены", Toast.LENGTH_SHORT).show()
        }
    }


}
