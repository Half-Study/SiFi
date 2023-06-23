package com.example.sifi.Service

import android.Manifest
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.PendingIntent.FLAG_IMMUTABLE
import android.app.PendingIntent.FLAG_UPDATE_CURRENT
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.media.RingtoneManager
import android.os.Build
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.sifi.MainActivity
import com.example.sifi.R
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage


class MyFirebaseMessagingService : FirebaseMessagingService() {

    companion object {
        private const val CHANNEL_NAME = " Emoji Party"
        private const val CHANNEL_DESCRIPTION = "Emoji Party를 위한 채널"
        private const val CHANNEL_ID = "Channel Id"
    }

    override fun onNewToken(p0: String) {
        super.onNewToken(p0)
        Log.d("daeYoung", "token: ${p0}")
    }

    // 데이터 메시지
    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
        // 알림 메세지가 데이터 형태로 들어옴

        // 채널 생성 함수
        createNotificationChannel()

//        val type = message.data["type"]?.let {
//            NotificationType.valueOf(it)
//        }
        val textTitle = message.data["title"]
        val body = message.data["body"]
        Log.d("daeYoung", "received message: ${message.data}, title: ${textTitle}, message: ${body}")

//        type ?: return

        with(NotificationManagerCompat.from(this)) {
            // notificationId is a unique int for each notification that you must define
            //notify(type.id, createNotification(textTitle, body) ) // 두번째 인자로 빌더가 들어감
            if (ActivityCompat.checkSelfPermission(
                    this@MyFirebaseMessagingService,
                    Manifest.permission.POST_NOTIFICATIONS
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                notify(0, createNotification(textTitle, body) ) // 두번째 인자로 빌더가 들어감
            }
        }

//        val title = message.notification?.title
//        val message2 = message.notification?.body
//        Log.d("daeYoung", "received message: ${message.data}, title: ${message.notification?.title}, message: ${message.notification?.body}")

    }


    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT)
            channel.description = CHANNEL_DESCRIPTION

            val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    private fun createNotification(title: String?, message: String?): Notification {

        // 알림을 누르면 실행될 수 있게 intent를 만들어줌
        val intent = Intent(this,MainActivity::class.java).apply {
//            putExtra("notificationType","${type?.title} 타입")
            addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP) // 이미 해당 액티비티일때 인텐트가 연결되면 1개만 유지함
        }

//        val resultPendingIntent = PendingIntent.getActivity(this,type!!.id,intent, FLAG_UPDATE_CURRENT)
        val resultPendingIntent = PendingIntent.getActivity(this,0,intent, FLAG_UPDATE_CURRENT)

        val notificationBuilder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.baseline_circle_notifications_24)
            .setContentTitle(title)
            .setContentText(message)
            .setAutoCancel(true)
            .setContentIntent(resultPendingIntent)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .build()

        // 빌더에 type별로 속성을 추가해준다.
//        when(type){
//            NotificationType.NORMAL -> Unit
//
//            // 많은 text가 올 경우
//            NotificationType.EXPANDABLE -> {
//                builder.setStyle(NotificationCompat.BigTextStyle()
//                    .bigText(bigEmoji))
//            }
//
//            //layout을 통해 custom하기
//            NotificationType.CUSTOM -> {
//                builder.setStyle(NotificationCompat.DecoratedCustomViewStyle())
//                    .setCustomContentView(RemoteViews(packageName,R.layout.view_custom_notification)
//                        .apply {
//                            setTextViewText(R.id.title, textTitle)
//                            setTextViewText(R.id.message, body)
//                        })
//
//            }
//        }

        return notificationBuilder

    }


}