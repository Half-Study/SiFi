package com.example.sifi.Board

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.sifi.R
import com.example.sifi.data.BoardModel
import com.example.sifi.Utils.FBAuth
import com.example.sifi.Utils.FBRef
import com.google.firebase.auth.FirebaseAuth

class BoardWriteActivity : AppCompatActivity() {

    private lateinit var insertBtn : Button
    private lateinit var editTitle : EditText
    private lateinit var editContent : EditText
    private lateinit var textGame : TextView
    private lateinit var textFood : TextView
    private lateinit var textSports : TextView
    private lateinit var textDrink : TextView


    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_board_write)

        editContent = findViewById(R.id.editContent)
        insertBtn  = findViewById(R.id.insertBtn)
        textDrink = findViewById(R.id.textDrink)
        textGame = findViewById(R.id.textGame)
        textFood = findViewById(R.id.textFood)
        textSports = findViewById(R.id.textSports)
        var category = ""

        var textArray = arrayOf(textDrink,textGame,textSports,textFood)

        for (i in textArray.indices){
            textArray[i].setOnClickListener {
                when(i){
                    0 -> {
                        category = "술"
                        textDrink.setTextColor(ContextCompat.getColor(applicationContext!!,R.color.pink))
                    }
                    1 -> {
                        category = "게임"
                        textGame.setTextColor(ContextCompat.getColor(applicationContext!!,R.color.pink))
                    }
                    2 -> {
                        category = "운동"
                        textSports.setTextColor(ContextCompat.getColor(applicationContext!!,R.color.pink))
                    }
                    3 -> {
                        category = "식사"
                        textFood.setTextColor(ContextCompat.getColor(applicationContext!!,R.color.pink))
                    }
                }
            }
        }

//        textGame.setOnClickListener {
//            category = "게임"
//            textGame.setTextColor(ContextCompat.getColor(applicationContext!!,R.color.pink))
//        }


        auth = FirebaseAuth.getInstance()

        insertBtn.setOnClickListener {
            val title = category
            val content = editContent.text.toString()
            val uid = FBAuth.getUid()
            val time = FBAuth.getTime()

            FBRef.boardRef
                .push()
                .setValue(BoardModel(title,content,uid,time))
            Toast.makeText(this,"게시글 입력 완료", Toast.LENGTH_LONG).show()
            finish()

        }
    }
}