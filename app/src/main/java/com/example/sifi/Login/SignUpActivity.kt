package com.example.sifi.Login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.Toast
import com.example.sifi.R
import com.example.sifi.profileaddsetting.ProfileSettingActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException

class SignUpActivity : AppCompatActivity() {

    lateinit var editEmail: EditText
    lateinit var editPass: EditText
    lateinit var nextBtn: Button
    lateinit var container: FrameLayout
    lateinit var backImg: ImageView

    private lateinit var auth: FirebaseAuth
    private val TAG: String = "CreateAccount"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_account)

        auth = FirebaseAuth.getInstance()

        editEmail = findViewById(R.id.editEmail)
        editPass = findViewById(R.id.editPass)
        nextBtn = findViewById(R.id.btnNext)
        backImg = findViewById(R.id.backImage)


        nextBtn.setOnClickListener {
            if (editEmail.text.toString().isEmpty() || editPass.text.toString().isEmpty()) {
                Toast.makeText(this, "email 혹은 password를 반드시 입력하세요.", Toast.LENGTH_SHORT).show()
            } else {
                auth.createUserWithEmailAndPassword(
                    editEmail.text.toString(),
                    editPass.text.toString()
                ).addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // 가지고 realtimeDB에 접근
                        Log.d("daeYoung", "현재 유저: ${auth.currentUser?.uid}")
                        finish()
                        val intent = Intent(this, ProfileSettingActivity::class.java).also {
                            it.putExtra("uid", auth.currentUser?.uid)
                        }
                        startActivity(intent)
                    } else {
                        Log.d("daeYoung", "fail: ${task.exception}")
                    }
                }.addOnFailureListener { exception ->
                    when (exception) {
                        is FirebaseAuthWeakPasswordException -> {
                            Toast.makeText(this, "password를 6자리 이상 입력하세요.", Toast.LENGTH_SHORT)
                                .show()
                        }
                        is FirebaseAuthInvalidCredentialsException -> {
                            Toast.makeText(this, "이메일 형식에 맞게 입력하세요", Toast.LENGTH_SHORT).show()
                        }
                        is FirebaseAuthUserCollisionException -> {
                            Toast.makeText(this, "이미 가입되어있는 이메일입니다.", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }

        backImg.setOnClickListener {
            finish()
        }
    }
}