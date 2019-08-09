package com.example.recyclerholder

import android.provider.BaseColumns
import android.provider.BaseColumns._ID

final class NotesEntry: BaseColumns{
    companion object {
        val TABLE_NAME: String = "notes"
        val COLUMN_TITILE: String = "title"
        val COLUMN_DESCRIPTION: String = "description"
        val COLUMN_DAY_OF_WEEK: String = "date"
        val COLUMN_PRIORITY: String = "priority"
        val COLUMN_ID: String = _ID

        val TYPE_TEXT: String = "TEXT"
        val TYPE_INTEGER: String = "INTEGER"

        val CREATE_COMMAND: String = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME +
                "(" + _ID + " " + TYPE_INTEGER + " PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_TITILE + " " + TYPE_TEXT + ", " + COLUMN_DESCRIPTION + " " + TYPE_TEXT +
                ", " + COLUMN_DAY_OF_WEEK + " " + TYPE_TEXT + ", " + COLUMN_PRIORITY + " " + TYPE_INTEGER + ")"

        val DROP_COMMAND: String = "DROP TABLE IF EXISTS " + TABLE_NAME


    }
}