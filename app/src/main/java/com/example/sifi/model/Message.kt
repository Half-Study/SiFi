package com.example.sifi.model

// 메시지를 주고받기 위해 사용되는 클래스 (채팅방에서 채팅목록을 보여주기 위해 사용)
data class Message(
    var id : String = "",        // 메시지 아이디 (자동생성)
    var msg : String = "asd",       // 메시지
    var userName : String = "",  //사용자명
    var timestamp : Long = System.currentTimeMillis(),    //전송시간 timestamp
)
