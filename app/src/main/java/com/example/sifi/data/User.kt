package com.example.sifi.data

data class User(
    val sex: String,
    val region: String,
    val nickname: String,
    val job: String,
    val hobby: List<String>,
    val mbti: String,
    val introduce: String = "미 입력"
)
