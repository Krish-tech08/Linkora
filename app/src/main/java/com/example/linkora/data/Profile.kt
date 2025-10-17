package com.example.linkora.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "profiles")
data class Profile(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val age: String,
    val bio: String,
    val interests: List<String>,
    val domain: String,
    val imageResId: Int,
    var isConnected: Boolean = false
)
