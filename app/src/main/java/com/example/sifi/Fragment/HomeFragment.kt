package com.example.sifi.Fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import com.example.sifi.Board.BoardListAdapter
import com.example.sifi.Board.BoardWriteActivity
import com.example.sifi.R
import com.example.sifi.data.BoardModel
import com.example.sifi.Utils.FBRef
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener


class HomeFragment : Fragment() {
    private val TAG = HomeFragment::class.java.simpleName
    private val boardDataList = mutableListOf<BoardModel>()
    private lateinit var boardListAdapter : BoardListAdapter
    private val boardKeyList = mutableListOf<String>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =  inflater.inflate(R.layout.fragment_home, container, false)

        val writeButton = view.findViewById<FloatingActionButton>(R.id.writeBtn)
        val boardListView = view.findViewById<ListView>(R.id.boardListView)

        boardListAdapter = BoardListAdapter(boardDataList) //어뎁터 엑티비티에 리스트 집어넣기
        boardListView.adapter = boardListAdapter  //리스트뷰에 어뎁터 적용

        writeButton.setOnClickListener {
            val intent = Intent(context, BoardWriteActivity::class.java)
            startActivity(intent)
        }

        getFBBoardDate()



        return view
    }
    private fun getFBBoardDate(){
        val postListener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                boardDataList.clear()

                for (dataModel in snapshot.children){
                    val item = dataModel.getValue(BoardModel::class.java)
                    boardDataList.add(item!!)
                }
                boardDataList.reverse()
                boardListAdapter.notifyDataSetChanged()
            }


            override fun onCancelled(error: DatabaseError) {
                Log.w(TAG,"asdadasd",error.toException())
            }
        }
        FBRef.boardRef.addValueEventListener(postListener)
    }


}