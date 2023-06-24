package com.example.sifi.Chat

import android.icu.text.SimpleDateFormat
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sifi.Utils.FBAuth
import com.example.sifi.databinding.ActivityChatRoomBinding
import com.example.sifi.databinding.ItemMsgListBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.util.*


class ChatRoomActivity: AppCompatActivity() {
    val binding by lazy { ActivityChatRoomBinding.inflate(layoutInflater) }
    val database = Firebase.database
    val userUid = FBAuth.getUid()
    lateinit var msgRef: DatabaseReference

    var roomId: String = ""
    var roomTitle: String = ""



    val msgList = mutableListOf<com.example.sifi.model.Message>()  // 메시지 목록 데이터
    lateinit var adapter: MsgListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        // 인텐트로 전달된 방 정보와 사용자 정보를 꺼낸다.
        roomId = intent.getStringExtra("roomId") ?: "none"
        roomTitle = intent.getStringExtra("roomTitle") ?: "없음"

        msgRef = database.getReference("rooms").child(roomId).child("messages")


        adapter = MsgListAdapter(msgList)
        with(binding) {
            recyclerMsgs.adapter = adapter
            recyclerMsgs.layoutManager = LinearLayoutManager(baseContext)
            textTitle.setText(roomTitle)
            btnBack.setOnClickListener { finish() }
            btnSend.setOnClickListener { sendMsg() }
        }
        loadMsgs()
    }

    // 메시지 목록을 읽어오는 메소드
    fun loadMsgs() {
        msgRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                msgList.clear()
                for (item in snapshot.children) {
                    item.key
                    item.getValue(com.example.sifi.model.Message::class.java)?.let { msg ->
                        msgList.add(msg)        // 메시지 목록에 추가
                    }
                }
                adapter.notifyDataSetChanged()      // 어댑터 갱신
            }

            override fun onCancelled(error: DatabaseError) {
                print(error.message)
            }
        })
    }

    // 메시지를 파이어베이스에 전송하는 메소드
    fun sendMsg() {
        with(binding) {
            if (editMsg.text.isNotEmpty()) {
                val message = com.example.sifi.model.Message(
                    editMsg.text.toString()
                )
                val msgId = msgRef.push().key!!
                message.id = msgId
                msgRef.child(msgId).setValue(message)
                editMsg.setText("")     // 메시지 전송 후 입력 필드 초기화화            }
            }
        }
    }
    fun getFormattedTimestamp(timestamp: Long): String {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss") // 원하는 날짜 형식을 지정합니다.
        val date = Date(timestamp)
        return dateFormat.format(date)
    }
}
class MsgListAdapter(val msgList: MutableList<com.example.sifi.model.Message>) :
    RecyclerView.Adapter<MsgListAdapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding =
            ItemMsgListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val msg = msgList.get(position)
        holder.setMsg(msg)
    }

    override fun getItemCount(): Int {
        return msgList.size
    }

    class Holder(val binding: ItemMsgListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun setMsg(msg: com.example.sifi.model.Message) {
            val database = Firebase.database
            val userUid = FBAuth.getUid()
            val userName = database.getReference("users").child("$userUid").child("nickname").get().addOnSuccessListener {
                binding.textName.text = it.value.toString()
            }
            binding.textMsg.setText(msg.msg)
            val formattedTimestamp = (itemView.context as ChatRoomActivity).getFormattedTimestamp(msg.timestamp)
            binding.textDate.text = formattedTimestamp
        }
    }
}
