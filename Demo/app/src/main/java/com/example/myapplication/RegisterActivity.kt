package com.example.myapplication

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.Database.Model
import com.example.myapplication.ViewModel.WordViewModel

class RegisterActivity : AppCompatActivity() {

    private lateinit var Username: EditText
    private lateinit var Password: EditText
    private lateinit var Email: EditText
    private lateinit var Address: EditText
    private lateinit var Gender: EditText
    private lateinit var wordViewModel: WordViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        Username= findViewById(R.id.edusername)
        Password = findViewById(R.id.edpassword)
        Email = findViewById(R.id.edemail)
        Address = findViewById(R.id.edaddress)
        Gender = findViewById(R.id.edgioitinh)

        wordViewModel = ViewModelProvider(this).get(WordViewModel::class.java)

        val button = findViewById<Button>(R.id.save)
        button.setOnClickListener {
            if (TextUtils.isEmpty(Username.text)||TextUtils.isEmpty(Password.text)||TextUtils.isEmpty(Email.text)||TextUtils.isEmpty(Address.text)||TextUtils.isEmpty(Gender.text)) {
                Toast.makeText(applicationContext,"Not Save",Toast.LENGTH_LONG).show()
            }
            else
            {
                val username = Username.text.toString()
                val password = Password.text.toString()
                val email = Email.text.toString()
                val address = Address.text.toString()
                val gender = Gender.text.toString()
                val model : Model = Model(username,password,email,address,gender)
                wordViewModel.insert(model)
                Toast.makeText(applicationContext,"Saved",Toast.LENGTH_LONG).show()
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
            }
            finish()
        }
    }

    companion object {
        const val EXTRA_REPLY = "Username"
        const val EXTRA_REPLY_P = "Password"
        const val EXTRA_REPLY_E = "Email"
        const val EXTRA_REPLY_A = "Address"
        const val EXTRA_REPLY_G = "Gender"
    }

}
