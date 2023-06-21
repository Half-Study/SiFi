package com.example.sifi.Fragment

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.sifi.R
import com.example.sifi.Utils.FBAuth
import com.example.sifi.data.User
import com.example.sifi.databinding.FragmentMypageBinding
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import java.io.ByteArrayOutputStream


class MypageFragment : Fragment() {
    private lateinit var binding: FragmentMypageBinding
    private val storage = Firebase.storage
    private lateinit var myName: TextView
    private lateinit var mySex: TextView
    private lateinit var myRegion: TextView
    private lateinit var myIntroduce: TextView
    private lateinit var myJob: TextView
    private lateinit var myMbti: TextView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMypageBinding.inflate(inflater, container, false)
        val database = Firebase.database
        val userUid = FBAuth.getUid()


        val Mbti = database.getReference("users").child("$userUid").child("mbti").get()
            .addOnSuccessListener {
                var userMbti = it.value.toString()
                when (userMbti) {
                    "ENFJ" -> binding.mbtiImage.setImageResource(R.drawable.enfj)
                    "ENFP" -> binding.mbtiImage.setImageResource(R.drawable.enfp)
                    "ENTJ" -> binding.mbtiImage.setImageResource(R.drawable.entj)
                    "ENTP" -> binding.mbtiImage.setImageResource(R.drawable.entp)
                    "ESFJ" -> binding.mbtiImage.setImageResource(R.drawable.esfj)
                    "ESFP" -> binding.mbtiImage.setImageResource(R.drawable.esfp)
                    "ESTJ" -> binding.mbtiImage.setImageResource(R.drawable.estj)
                    "ESTP" -> binding.mbtiImage.setImageResource(R.drawable.estp)
                    "INFJ" -> binding.mbtiImage.setImageResource(R.drawable.infj)
                    "INFP" -> binding.mbtiImage.setImageResource(R.drawable.infp)
                    "INTJ" -> binding.mbtiImage.setImageResource(R.drawable.intj)
                    "INTP" -> binding.mbtiImage.setImageResource(R.drawable.intp)
                    "ISFJ" -> binding.mbtiImage.setImageResource(R.drawable.isfj)
                    "ISFP" -> binding.mbtiImage.setImageResource(R.drawable.isfp)
                    "ISTJ" -> binding.mbtiImage.setImageResource(R.drawable.istj)
                    "ISTP" -> binding.mbtiImage.setImageResource(R.drawable.istp)
                }
            }


        getImageData(userUid)
        getMyPageData()

        binding.addImg.setOnClickListener {
            val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
            startActivityForResult(gallery, 100)

        }

        binding.btnUpload.setOnClickListener {
            imageUpload(userUid)
        }


        return binding.root
    }

    private fun getImageData(key: String) {
        // Reference to an image file in Cloud Storage
        val storageReference = Firebase.storage.reference.child(key + ".png")

        val imageView = binding.addImg

        storageReference.downloadUrl.addOnCompleteListener(OnCompleteListener { task ->
            if (task.isSuccessful) {
                Glide.with(imageView.context)
                    .load(task.result)
                    .into(imageView)
            } else {

            }
        })
    }

    // 갤러리에서 가져온 이미지 엑티비티 화면에 띄우는 함수
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val addImg = binding.addImg
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == 100) {
            addImg.setImageURI(data?.data)
        }
    }

    // 이미지 파이어베이스에 올리는 함수
    private fun imageUpload(key: String) {
        val storageRef = storage.reference
        val mountainsRef = storageRef.child(key + ".png")
        val addImage = binding.addImg

        addImage.isDrawingCacheEnabled = true
        addImage.buildDrawingCache()
        val bitmap = (addImage.drawable as BitmapDrawable).bitmap
        val baos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        val data = baos.toByteArray()

        var uploadTask = mountainsRef.putBytes(data)
        uploadTask.addOnFailureListener {

        }.addOnSuccessListener { taskSnapshot ->
        }
    }

    private fun getMyPageData() {
        val database = Firebase.database
        val userUid = FBAuth.getUid()

        myName = binding.myNameTv
        mySex = binding.mySexTv
        myRegion = binding.myRegionTv
        myIntroduce = binding.myIntroduceTv
        myJob = binding.myJobTv
        myMbti = binding.myMbtiTv


        val userName = database.getReference("users").child("$userUid").child("nickname").get()
            .addOnSuccessListener {
                Log.i("firebase", "Got value ${it.value}")
                myName.text = it.value.toString()
            }

        val userSex = database.getReference("users").child("$userUid").child("sex").get()
            .addOnSuccessListener {
                Log.i("firebase", "Got value ${it.value}")
                mySex.text = it.value.toString()

            }
        val userRegion = database.getReference("users").child("$userUid").child("region").get()
            .addOnSuccessListener {
                Log.i("firebase", "Got value ${it.value}")
                myRegion.text = it.value.toString()

            }
        val userIntroduce = database.getReference("users").child("$userUid").child("introduce").get()
                .addOnSuccessListener {
                    Log.i("firebase", "Got value ${it.value}")
                    myIntroduce.text = it.value.toString()

                }
        val userJob = database.getReference("users").child("$userUid").child("job").get()
            .addOnSuccessListener {
                Log.i("firebase", "Got value ${it.value}")
                myJob.text = it.value.toString()

            }
        val userMbti = database.getReference("users").child("$userUid").child("mbti").get()
            .addOnSuccessListener {
                Log.i("firebase", "Got value ${it.value}")
                myMbti.text = it.value.toString()

            }



    }

}