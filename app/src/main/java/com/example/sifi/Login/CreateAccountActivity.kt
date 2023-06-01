package com.example.sifi.Login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.sifi.R
import com.example.sifi.Fragment.SexFragment
import com.google.firebase.auth.FirebaseAuth

class CreateAccountActivity : AppCompatActivity() {

    lateinit var editEmail: EditText
    lateinit var editPass: EditText
    lateinit var nextBtn : Button
    lateinit var container : FrameLayout
    lateinit var backImg : ImageView

    private lateinit var auth: FirebaseAuth
    private val TAG : String = "CreateAccount"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_account)

        auth = FirebaseAuth.getInstance()

        editEmail = findViewById(R.id.editEmail)
        editPass = findViewById(R.id.editPass)
        nextBtn = findViewById(R.id.btnNext)
        container = findViewById(R.id.container)
        backImg = findViewById(R.id.backImage)


        nextBtn.setOnClickListener {
            if(editEmail.text.toString().isEmpty() || editPass.text.toString().isEmpty()){
                Toast.makeText(this, "email 혹은 password를 반드시 입력하세요.", Toast.LENGTH_SHORT).show()
            }
            else{
                auth.createUserWithEmailAndPassword(editEmail.text.toString(),editPass.text.toString())
                    .addOnCompleteListener(this){ task ->
                        if(task.isSuccessful){
                            Log.d(TAG, "createUserWithEmail:success")
                            finish()
                        }else{
                            Log.w(TAG, "createUserWithEmail:failure", task.exception)
                            Toast.makeText(
                                baseContext, "Authentication failed.",
                                Toast.LENGTH_SHORT
                            ).show()
                            editEmail?.setText("")
                            editPass?.setText("")
                            editEmail.requestFocus()
                        }
                    }
            }
        }
        nextBtn.setOnClickListener {
            container.visibility = View.VISIBLE
            loadFragment(SexFragment())
        }

        backImg.setOnClickListener {
            finish()
        }



    }
    private fun loadFragment(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container,fragment)
        transaction.commit()
    }
}