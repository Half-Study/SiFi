package com.example.sifi.Utils

import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class FBRef {
    companion object {
        private val database = Firebase.database

        val boardRef = database.getReference("board")
        val userRef = database.getReference("users")
    }
}