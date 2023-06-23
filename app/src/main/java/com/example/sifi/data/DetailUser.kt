package com.example.sifi.data

import android.net.Uri

data class DetailUser(
    val tall: Int = 0,
    val school: String = "",
    val call: String = "",
    val religion: String = "",
    val smoking: String = "",
    val drinking: String = "",
    val image: Uri? = null,
    val user: User = User()
)
