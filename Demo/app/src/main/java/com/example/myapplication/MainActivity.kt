package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private lateinit var username: TextView
    private lateinit var password: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        username = findViewById(R.id.tvusername)
        password = findViewById(R.id.tvpassword)

        val intent = getIntent()
        val bundle = intent.extras

        if(bundle!=null)
        {
            username.text = bundle.getString("username")
            password.text = bundle.getString("password")
        }
        else
        {
            username.text = "thach"
            password.text = "1"
        }
    }
}
