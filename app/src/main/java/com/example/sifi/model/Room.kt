package com.example.sifi.model

// 채팅방을 생성하고 목록에서 보여주기 위해 사용되는 클래스
class Room {
    var id : String = ""        // 방 아이디
    var title : String = ""     //방이름
    var users: String = ""

    constructor()
    constructor(title: String, creatorName: String) {
        this.title = title
        users = creatorName
    }
}