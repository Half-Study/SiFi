package com.example.sifi.checkProfile

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.marginEnd
import androidx.core.view.setPadding
import com.bumptech.glide.Glide
import com.example.sifi.R
import com.example.sifi.data.User
import com.example.sifi.databinding.ActivityCheckProfileBinding
import com.example.sifi.databinding.ActivityProfileSettingBinding
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage

class CheckProfileActivity : AppCompatActivity() {
    val binding by lazy {
        ActivityCheckProfileBinding.inflate(layoutInflater)
    }
    val firebaseRef by lazy { Firebase.database.getReference("users") }
    val fireStorageRef by lazy { FirebaseStorage.getInstance().reference }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val uid = intent.getStringExtra("uid")
        uid?.let {
            firebaseRef.child(uid).get().addOnSuccessListener {
                val item = it.getValue(User::class.java) ?: User()
                hobbyList(hobbyList = item.hobby)
            }.addOnFailureListener {
                Log.d("daeYoung", "user data download fail")
            }
            fireStorageRef.child("$uid.png").downloadUrl.addOnSuccessListener {
                Glide.with(binding.profileImage.context)
                    .load(it)
                    .error(R.drawable.image_note_found)
                    .into(binding.profileImage)
            }.addOnFailureListener {
                Log.d("daeYoung", "user image data download fail")
            }
        }
    }

    fun hobbyList(hobbyList: List<String>) {
//        val textViewList = mutableListOf<TextView>()
        hobbyList.forEach {
//            textViewList.add(
//                TextView(this).apply {
//                    text = it
//                    setTextColor(Color.WHITE)
//                    setBackgroundColor(getColor(R.color.main_blue))
//                }
//            )
            val textView = TextView(this).apply {
                text = it
                setTextColor(Color.WHITE)
//                setBackgroundColor(getColor(R.color.main_blue))
                setPadding(16, 10, 16, 10)

                val marginLayoutParams = ViewGroup.MarginLayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
                marginLayoutParams.setMargins(0, 10, 10, 10)
                layoutParams = marginLayoutParams

                val backgroundDrawable = GradientDrawable().apply {
                    shape = GradientDrawable.RECTANGLE
                    cornerRadius = 20f
                    setColor(getColor(R.color.main_blue))
                }
                background = backgroundDrawable

            }
            binding.hobbyLayout.addView(textView)
        }
    }
}