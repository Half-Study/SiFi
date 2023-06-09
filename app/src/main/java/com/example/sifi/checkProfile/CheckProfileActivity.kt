package com.example.sifi.checkProfile

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.sifi.R
import com.example.sifi.data.User
import com.example.sifi.databinding.ActivityCheckProfileBinding
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage

class CheckProfileActivity : AppCompatActivity() {
    val binding by lazy {
        ActivityCheckProfileBinding.inflate(layoutInflater)
    }
    val firebaseRef by lazy { Firebase.database.getReference("users") }
    val fireStorageRef by lazy { FirebaseStorage.getInstance().reference }

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val uid = intent.getStringExtra("uid")
        uid?.let {
            firebaseRef.child(uid).get().addOnSuccessListener {
                val item = it.getValue(User::class.java) ?: User()
                addHobbyList(hobbyList = item.hobby)
                addMbti(mbti = item.mbti)
                addIntroduce(introduce = item.introduce)
                addCall(call = item.call)
                binding.name.text = item.nickname
                binding.sex.text = item.sex
                binding.region.text = "${binding.region.text} ${item.region}"

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

    private fun addHobbyList(hobbyList: List<String>) {
//        val textViewList = mutableListOf<TextView>()
        hobbyList.forEach {

            val textView = TextView(this).apply {
                text = it
                setTextColor(Color.WHITE)
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

    private fun addMbti(mbti: String) {
        val mbtiNum = when (mbti) {
            "ENFJ" -> R.drawable.enfj
            "ENFP" -> R.drawable.enfp
            "ENTJ" -> R.drawable.entj
            "ENTP" -> R.drawable.entp
            "ESFJ" -> R.drawable.esfj
            "ESFP" -> R.drawable.esfp
            "ESTJ" -> R.drawable.estj
            "ESTP" -> R.drawable.estp
            "INFJ" -> R.drawable.infj
            "INFP" -> R.drawable.infp
            "INTJ" -> R.drawable.intj
            "INTP" -> R.drawable.intp
            "ISFJ" -> R.drawable.isfj
            "ISFP" -> R.drawable.isfp
            "ISTJ" -> R.drawable.istj
            "ISTP" -> R.drawable.istp
            else -> {
                -1
            }
        }
        val imageView = ImageView(this).apply {
            setImageResource(mbtiNum)
            layoutParams = LinearLayout.LayoutParams(220, 220)
        }
        val textView = TextView(this).apply {
            text = mbti
            setTextColor(Color.BLACK)
            setTypeface(null, Typeface.BOLD)
            gravity = Gravity.CENTER
            setPadding(0, 20, 0, 0)
        }

        binding.mbtiLayout.also {
            it.addView(imageView)
            it.addView(textView)
        }
    }

    private fun addIntroduce(introduce: String) {
        val textView = TextView(this).apply {
            text = "\"${introduce}\""
            setTextColor(Color.BLACK)
            setTypeface(null, Typeface.BOLD)
//            setPadding(16, 10, 16, 10)
        }
        binding.introduceLayout.addView(textView)
    }

    private fun addCall(call: String) {
        binding.call.text = "${call.substring(0, 3)}-${call.substring(3, 7)}-${call.substring(7)}"

    }
}