package com.example.recyclerholder

import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "notes")
class Note {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
    var title: String? = null
    var date: String? = null
    var description: String? = null
    var priority: Int = 0

    internal constructor(id: Int, title: String, date: String, description: String, priority: Int) {
        this.id = id
        this.title = title
        this.description = description
        this.priority = priority
    }

    @Ignore
    constructor(title: String, date: String, description: String, priority: Int) {
        this.title = title
        this.date = date
        this.description = description
        this.priority = priority
    }
}
