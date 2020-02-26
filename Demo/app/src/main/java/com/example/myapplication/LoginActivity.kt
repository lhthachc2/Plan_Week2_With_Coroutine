package com.example.myapplication

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.ViewModel.WordViewModel
import kotlinx.android.synthetic.main.activity_login.*
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T



class LoginActivity : AppCompatActivity() {

    private lateinit var Username: EditText
    private lateinit var Password: EditText
    private lateinit var loginButton: Button
    private lateinit var registerButton: Button
    private lateinit var checklogin: CheckBox
    private lateinit var wordViewModel: WordViewModel
    private lateinit var sharedpreferences : SharedPreferences
    private lateinit var editTor: SharedPreferences.Editor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        Username = findViewById(R.id.username)
        Password = findViewById(R.id.password)
        loginButton = findViewById(R.id.login)
        checklogin = findViewById(R.id.checklogin)
        registerButton = findViewById(R.id.register)

        wordViewModel = ViewModelProvider(this).get(WordViewModel::class.java)


        sharedpreferences=PreferenceManager.getDefaultSharedPreferences(this)
        //sharedpreferences  = getSharedPreferences("name", Context.MODE_PRIVATE);
        editTor = sharedpreferences.edit()
        var check : String? = sharedpreferences.getString("checkbox","False")
        var username1 : String? = sharedpreferences.getString("username","")
        var pass : String? = sharedpreferences.getString("password","")

        Username.setText(username1)
        Password.setText(pass)

        loginButton.setOnClickListener {
            if (checklogin.isChecked)
            {
                editTor.putString("checkbox","True")
                editTor.commit()

                var username : String = Username.text.toString()
                editTor.putString("username",username)
                editTor.commit()

                var pass : String = Password.text.toString()
                editTor.putString("password",pass)
                editTor.commit()
            }
            else
            {
                editTor.putString("checkbox","False")
                editTor.commit()

                editTor.putString("username","")
                editTor.commit()

                editTor.putString("password","")
                editTor.commit()
            }
            val isValid = wordViewModel.checkValidLogin(username.text.toString(), password.text.toString())
            if (isValid)
            {
                Toast.makeText(this, "Successfully Logged In!", Toast.LENGTH_LONG).show()
                val intent = Intent(this, MainActivity::class.java)
                val bundle = Bundle()
                val username = Username.text.toString()
                val password = Password.text.toString()
                bundle.putString("username",username)
                bundle.putString("password",password)
                intent.putExtras(bundle)
                startActivity(intent)
            }
            else
            {
                Toast.makeText(this, "Invalid Login!", Toast.LENGTH_SHORT).show()
            }
        }
        registerButton.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivityForResult(intent, 1)
        }

    }


}
