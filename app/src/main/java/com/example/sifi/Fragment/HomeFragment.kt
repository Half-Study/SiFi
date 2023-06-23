package com.example.sifi.Fragment2

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.fragment.app.Fragment
import com.example.sifi.Board.BoardListAdapter
import com.example.sifi.Board.BoardWriteActivity
import com.example.sifi.MainActivity
import com.example.sifi.R
import com.example.sifi.Utils.BoardModel
import com.example.sifi.checkProfile.CheckProfileActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage


class HomeFragment : Fragment() {
    private val TAG = HomeFragment::class.java.simpleName
    private val boardDataList = mutableListOf<BoardModel>()
    private lateinit var boardListAdapter: BoardListAdapter
    lateinit var mainActivity: MainActivity
    private val storage: FirebaseStorage by lazy { FirebaseStorage.getInstance() }
    val database by lazy { Firebase.database }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        // 2. Context를 액티비티로 형변환해서 할당
        mainActivity = context as MainActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =  inflater.inflate(R.layout.fragment_home, container, false)

        val writeButton = view.findViewById<FloatingActionButton>(R.id.writeBtn)
        val boardListView = view.findViewById<ListView>(R.id.boardListView)

        boardListAdapter =
            BoardListAdapter(
                boardDataList,
                onItemClick = OnItemClick
            ) //어뎁터 엑티비티에 리스트 집어넣기
        boardListView.adapter = boardListAdapter  //리스트뷰에 어뎁터 적용

        writeButton.setOnClickListener {
            val intent = Intent(context, BoardWriteActivity::class.java)
            startActivity(intent)
        }

        getFBBoardDate()

        return view
    }

    private fun getFBBoardDate() {
        // 파이어베이스 스토리지
        val storageReference = storage.reference
        val databaseReference = database.reference

        databaseReference.child("board").addValueEventListener(
            object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {

                    boardDataList.clear()

                    for (dataModel in snapshot.children) {
                        val item = dataModel.getValue(BoardModel::class.java)
                        Log.d("daeYoung", "dataModel: ${dataModel.value}")

                        boardDataList.add(item!!)
                    }
                    boardDataList.reverse()
                    boardListAdapter.notifyDataSetChanged()
                }


                override fun onCancelled(error: DatabaseError) {
                    Log.w(TAG, "asdadasd", error.toException())
                }
            }
        )
    }

    private val OnItemClick:(String) -> Unit = { uid ->
        val intent = Intent(activity, CheckProfileActivity::class.java).also {
            it.putExtra("uid", uid)
        }
        startActivity(intent)
    }


}