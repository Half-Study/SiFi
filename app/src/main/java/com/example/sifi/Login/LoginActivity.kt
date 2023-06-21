package com.example.sifi.Login

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.sifi.MainActivity
import com.example.sifi.R
import com.example.sifi.signup.SignUpActivity
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    lateinit var emailEt: EditText
    lateinit var passwordEt: EditText
    lateinit var loginBtn: Button
    lateinit var auth : FirebaseAuth
    lateinit var newBtn : Button
    lateinit var createBtn : Button

//    override fun onStart() {
//        super.onStart()
//        startActivity(Intent(this, ProfileSettingActivity::class.java))
//    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseApp.initializeApp(this@LoginActivity)
        setContentView(R.layout.activity_login)
        auth = FirebaseAuth.getInstance()


        emailEt = findViewById(R.id.editEmail)
        passwordEt = findViewById(R.id.editPass)
        loginBtn = findViewById(R.id.btnLogin)
        createBtn = findViewById(R.id.btnCreate)

        loginBtn.setOnClickListener {
            if (emailEt.text.toString().isEmpty() || passwordEt.text.toString().isEmpty()){
                Toast.makeText(this, "email 혹은 password를 반드시 입력하세요.", Toast.LENGTH_SHORT).show()
            } else {
                auth.signInWithEmailAndPassword(emailEt.text.toString(), passwordEt.text.toString())
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(ContentValues.TAG, "signInWithEmail:success")
                            if(auth.currentUser!=null){
                                var intent = Intent(this, MainActivity::class.java)
                                startActivity(intent)
                            }

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(ContentValues.TAG, "signInWithEmail:failure", task.exception)
                            Toast.makeText(baseContext, "로그인 실패",
                                Toast.LENGTH_SHORT).show()

                        }
                    }
            }
        }


        createBtn.setOnClickListener {
            var intent = Intent(this@LoginActivity, SignUpActivity::class.java)
            startActivity(intent)
        }
    }
}