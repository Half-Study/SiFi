package com.example.sifi.model

// 로그인, 회원가입 화면에서 사용자의 아이디와 비밀번호, 별명을 입력받는 코드
class User {
    var id : String = ""        // 사용자 아이디
    var password : String = ""  // 비밀번호
    var name : String = ""  // 별명

    constructor()
    constructor(id: String, password: String, name:String) {
        this.id = id
        this.password = password
        this.name = name
    }
}