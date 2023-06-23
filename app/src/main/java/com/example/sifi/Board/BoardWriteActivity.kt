package com.example.sifi.Board

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.sifi.Utils.BoardModel
import com.example.sifi.Utils.FBAuth
import com.example.sifi.Utils.FBRef
import com.example.sifi.databinding.ActivityBoardWriteBinding
import com.google.firebase.auth.FirebaseAuth

class BoardWriteActivity : AppCompatActivity() {

    private lateinit var insertBtn: Button
    private lateinit var editTitle: EditText
    private lateinit var editContent: EditText
    private lateinit var textGame: TextView
    private lateinit var textFood: TextView
    private lateinit var textSports: TextView
    private lateinit var textDrink: TextView


    private val auth: FirebaseAuth by lazy { FirebaseAuth.getInstance() }
    private val binding by lazy { ActivityBoardWriteBinding.inflate(layoutInflater) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        editContent = binding.editContent
        insertBtn = binding.insertBtn
        textDrink = binding.textDrink
        textGame = binding.textGame
        textFood = binding.textFood
        textSports = binding.textSports
        var category = ""
        val categoryList = listOf(
            binding.textDrink,
            binding.textSports,
            binding.textFood,
            binding.textGame,
        )

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)



        categoryList.forEach { textView ->
            val remainList = categoryList.filter { it != textView }

            textView.setOnClickListener {

                it.isSelected = !(it.isSelected)
                remainList.forEach { it.isSelected = false }
                category = if (it.isSelected) {
                    textView.text.toString()
                } else {
                    ""
                }
                insertBtn.isEnabled =
                    editContent.text.toString().isNotEmpty() && category.isNotEmpty()
            }
        }

        insertBtn.isEnabled = false

        editContent.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                insertBtn.isEnabled =
                    editContent.text.toString().isNotEmpty() && category.isNotEmpty()
                editContent.isSelected = editContent.text.toString().isNotEmpty()

            }

            override fun afterTextChanged(p0: Editable?) {}
        })

        insertBtn.setOnClickListener {
            val title = category
            val content = editContent.text.toString()
            val uid = FBAuth.getUid()
            val time = FBAuth.getTime()

            FBRef.boardRef
                .push()
                .setValue(BoardModel(title, content, uid, time))
            Toast.makeText(this, "게시글 입력 완료", Toast.LENGTH_LONG).show()
            finish()

        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}