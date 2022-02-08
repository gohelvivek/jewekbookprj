package com.avh.jewelbook

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RemoteViews
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class forgotpassword : AppCompatActivity() {


    lateinit var notificationManager: NotificationManager
    lateinit var notificationChannel: NotificationChannel
    lateinit var builder: Notification.Builder
    private val channelId = "i.apps.notifications"
    private val description = "Test notification"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_forgotpassword)

        val notibtn = findViewById<Button>(R.id.otp)
        val mtxtt = findViewById<EditText>(R.id.mnoo)
        val mtx = mtxtt.text

        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        notibtn.setOnClickListener {

            if (mtx?.isEmpty() == true) {
                Toast.makeText(this, "Enter Mobile Number", Toast.LENGTH_LONG).show()
            } else {

                val mnbo: String = mtxtt.getText().toString()

                val db = DBHelper(this, null)

                if (db.getMno(mnbo)) {
                    val intent = Intent(this@forgotpassword, otp::class.java)
                    val message1 = mtxtt.text.toString()
                    intent.putExtra("message_key", message1)
                    startActivity(intent)

                    val pendingIntent = PendingIntent.getActivity(
                        this,
                        0,
                        intent,
                        PendingIntent.FLAG_UPDATE_CURRENT
                    )
                    val contentView = RemoteViews(packageName, R.layout.activity_afternoti)

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        notificationChannel =
                            NotificationChannel(
                                channelId,
                                description,
                                NotificationManager.IMPORTANCE_HIGH
                            )
                        notificationChannel.enableLights(true)
                        notificationChannel.lightColor = Color.GREEN
                        notificationChannel.enableVibration(false)
                        notificationManager.createNotificationChannel(notificationChannel)

                        builder = Notification.Builder(this, channelId)
                            .setContent(contentView)
                            .setSmallIcon(R.drawable.ic_launcher_background)
                            .setLargeIcon(
                                BitmapFactory.decodeResource(
                                    this.resources,
                                    R.drawable.ic_launcher_background
                                )
                            )
                            .setContentIntent(pendingIntent)
                    } else {

                        builder = Notification.Builder(this)
                            .setContent(contentView)
                            .setSmallIcon(R.drawable.ic_launcher_background)
                            .setLargeIcon(
                                BitmapFactory.decodeResource(
                                    this.resources,
                                    R.drawable.ic_launcher_background
                                )
                            )
                            .setContentIntent(pendingIntent)
                    }
                    notificationManager.notify(1234, builder.build())
                } else {

                    Toast.makeText(
                        applicationContext,
                        "Number does not match !",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }

        val clobttn = findViewById<Button>(R.id.cols)
        clobttn.setOnClickListener {

            super.onBackPressed()
        }
    }
}