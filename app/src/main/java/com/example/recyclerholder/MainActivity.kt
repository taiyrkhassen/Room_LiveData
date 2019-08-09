package com.example.recyclerholder

import android.content.ContentValues
import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.ActionBar
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.util.Log
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var dbHelper:NotesDBHelper
    lateinit var  database:SQLiteDatabase

    companion object {
        var arrayFromDb = ArrayList<Note>()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        dbHelper = NotesDBHelper(this)
        database = dbHelper.writableDatabase
        var actionBar = actionBar
        if(actionBar!=null){
            actionBar.hide()
        }
        getFromDb()

        recyclerNotes.layoutManager = LinearLayoutManager(this)
        val notesAdapter= NotesAdapter(arrayFromDb)
        recyclerNotes.adapter =notesAdapter

        val swipeObject = ItemTouchHelper(
                object: ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT){
                    override fun onMove(p0: RecyclerView, p1: RecyclerView.ViewHolder, p2: RecyclerView.ViewHolder): Boolean {
                        return false
                    }

                    override fun onSwiped(p0: RecyclerView.ViewHolder, p1: Int) {
                        remove(p0.adapterPosition)
                        notesAdapter.notifyDataSetChanged()
                    }
                }
        )

        swipeObject.attachToRecyclerView(recyclerNotes)

        notesAdapter.setClickListener {
            Toast.makeText(this, it.toString(), Toast.LENGTH_SHORT).show()
            notesAdapter.notifyDataSetChanged()
        }

    }

    /*fun addToArrayNotes(){
        if(arrayNotes.isEmpty()) {
            arrayNotes.add(Note("Парикмахер", "Понедельник", "Запись к парикмахеру в 15:00", 2))

            Log.d("mt", arrayNotes.get(0).description)
            arrayNotes.add(Note("Прогулка с Сымбат", "Вторник", "Прогулка назначена в 14:00 в меге", 2))

            arrayNotes.add(Note("Написать письмо Айганым", "Четверг", "Вечером после работы", 1))

            arrayNotes.add(Note("Купить одежду", "Воскресенье", "Купить стиль :р", 2))

            arrayNotes.add(Note("Аквапарк с одноклашками", "Пятница", "Отпроситься с работы и пойти в аккву", 1))
        }
    }
    fun addToDb(){
        for(item in arrayNotes){
            var contentValues = ContentValues()
            contentValues.put(NotesEntry.COLUMN_TITILE, item.title)
            contentValues.put(NotesEntry.COLUMN_DESCRIPTION, item.description)    //записывать данные
            contentValues.put(NotesEntry.COLUMN_PRIORITY, item.priority)
            contentValues.put(NotesEntry.COLUMN_DAY_OF_WEEK, item.date)
            database.insert(NotesEntry.TABLE_NAME, null, contentValues)

        }
    }*/
    fun getFromDb(){
        arrayFromDb.clear()
        var cursor:Cursor = database.query(NotesEntry.TABLE_NAME, null, null, null, null, null, NotesEntry.COLUMN_PRIORITY)
        while(cursor.moveToNext()){  // CURSOR чтобы читать с базы
            arrayFromDb.add(Note(
                cursor.getInt(cursor.getColumnIndex(NotesEntry.COLUMN_ID)) ,
                cursor.getString(cursor.getColumnIndex(NotesEntry.COLUMN_TITILE)),
                cursor.getString(cursor.getColumnIndex(NotesEntry.COLUMN_DAY_OF_WEEK)),
                cursor.getString(cursor.getColumnIndex(NotesEntry.COLUMN_DESCRIPTION)),
                cursor.getInt(cursor.getColumnIndex(NotesEntry.COLUMN_PRIORITY))

                ))
        }
        cursor.close()
    }

    fun addNote(view: View){
        var intent = Intent(this, CreateNotification::class.java)
        startActivity(intent)

    }
    fun remove(position:Int){
        val where:String = NotesEntry.COLUMN_ID + " = ?"
        val id:Int = arrayFromDb[position].id
        val condition = arrayOf(id.toString())
        database.delete(NotesEntry.TABLE_NAME, where, condition)
        getFromDb()
    }
}
