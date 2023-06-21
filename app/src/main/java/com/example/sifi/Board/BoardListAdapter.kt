package com.example.sifi.Board

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.sifi.R
import com.example.sifi.Utils.BoardModel
import com.google.firebase.storage.FirebaseStorage

class BoardListAdapter(
    val boardList: MutableList<BoardModel>,
    private val context: Context,
    val onItemClick: (String) -> Unit,
) :
    BaseAdapter() {
    lateinit var image: ImageView


    override fun getCount(): Int {
        return boardList.size
    }

    override fun getItem(position: Int): Any {
        return boardList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view = convertView
        if (view == null) {

            view = LayoutInflater.from(parent?.context)
                .inflate(R.layout.board_list_item, parent, false)
        }


        val title = view?.findViewById<TextView>(R.id.titleArea)
        val content = view?.findViewById<TextView>(R.id.contentArea)
        val time = view?.findViewById<TextView>(R.id.timeArea)
        image = view!!.findViewById<ImageView>(R.id.imageArea)

        title!!.text = boardList[position].title
        content!!.text = boardList[position].content
        time!!.text = boardList[position].time
        val uid = boardList[position].uid

        // Reference to an image file in Cloud Storage

        Log.d("daeYoung", "")

        image.setOnClickListener {
            onItemClick(uid)
        }



        FirebaseStorage.getInstance().reference.child(uid + ".png").downloadUrl
            .addOnSuccessListener {
                Log.d("daeYoung", "it의 값: ${it}")
                Glide.with(image.context)
                    .load(it)
                    .error(R.drawable.image_note_found)
                    .circleCrop()
                    .into(image!!)
            }.addOnFailureListener {
                Log.d("daeYoung", "이미지 로드 실패")
            }
        return view
    }


}