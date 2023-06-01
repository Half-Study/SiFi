package com.example.sifi.Fragment2

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.sifi.R
import com.example.sifi.Utils.FBAuth
import com.example.sifi.databinding.FragmentMypageBinding
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import java.io.ByteArrayOutputStream


class MypageFragment : Fragment() {
    private lateinit var binding : FragmentMypageBinding
    private val storage = Firebase.storage

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =  FragmentMypageBinding.inflate(inflater,container,false)
        val userUid = FBAuth.getUid()

        getImageData(userUid)

        binding.addImg.setOnClickListener {
            val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
            startActivityForResult(gallery,100)

        }

        binding.btnUpload.setOnClickListener {
            imageUpload(userUid)
        }


        return binding.root
    }

    private fun getImageData(key : String){
        // Reference to an image file in Cloud Storage
        val storageReference = Firebase.storage.reference.child(key + ".png")

        val imageView = binding.addImg

        storageReference.downloadUrl.addOnCompleteListener(OnCompleteListener { task ->
            if(task.isSuccessful){
                Glide.with(this)
                    .load(task.result)
                    .into(imageView)
            }else{

            }
        })
    }

    // 갤러리에서 가져온 이미지 엑티비티 화면에 띄우는 함수
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val addImg = binding.addImg
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == Activity.RESULT_OK && requestCode == 100){
            addImg.setImageURI(data?.data)
        }
    }

    // 이미지 파이어베이스에 올리는 함수
    private fun imageUpload(key : String) {
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

}