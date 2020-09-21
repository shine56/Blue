package com.shine56.blue.model.bean

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "request")
data class RequestBean(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,

    var baseUrl: String = "",
    var get:String = ""
    //var headers: HashMap<String, String>? = null
)