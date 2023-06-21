package com.example.sifi.Board

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.sifi.R
import com.example.sifi.Utils.FBAuth
import com.example.sifi.data.BoardModel
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage

class BoardListAdapter(val boardList : MutableList<BoardModel>): BaseAdapter() {
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
        val database = Firebase.database
        var view = convertView
        if(view == null){

            view = LayoutInflater.from(parent?.context).inflate(R.layout.board_list_item,parent,false)
        }


        val title = view?.findViewById<TextView>(R.id.titleArea)
        val content = view?.findViewById<TextView>(R.id.contentArea)
        val time = view?.findViewById<TextView>(R.id.timeArea)
        val myName = view?.findViewById<TextView>(R.id.myName)
        val myRegion = view?.findViewById<TextView>(R.id.myRegion)

        title!!.text = boardList[position].title
        content!!.text = boardList[position].content
        time!!.text = boardList[position].time
        val uid = boardList[position].uid

        Log.i("uid", "Got value {$uid}")

        val userName = database.getReference("users").child("$uid").child("nickname").get()
            .addOnSuccessListener {
                Log.i("firebase", "Got value ${it.value}")
                myName!!.text = it.value.toString()
            }

        val userRegion = database.getReference("users").child("$uid").child("region").get()
            .addOnSuccessListener {
                Log.i("firebase", "Got value ${it.value}")
                myRegion!!.text = it.value.toString()

            }







        return view!!
    }



}