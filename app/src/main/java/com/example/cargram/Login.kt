package com.example.cargram

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import com.google.firebase.auth.FirebaseAuth

class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        var email= findViewById<EditText>(R.id.emailfield)
        var password= findViewById<EditText>(R.id.passwordfield)

        var login= findViewById<AppCompatButton>(R.id.loginbtn)
        var auth= FirebaseAuth.getInstance()

        login.setOnClickListener {
            if(email.text.toString().isNotEmpty() && password.text.toString().isNotEmpty()){
                auth.signInWithEmailAndPassword(email.text.toString(),password.text.toString())
                    .addOnCompleteListener {
                    if(it.isSuccessful){
                        startActivity(Intent(this, HomeScreen::class.java))
                    }
                }
                    .addOnFailureListener{
                        Toast.makeText(this, "Invalid username or Password", Toast.LENGTH_SHORT).show()
                    }
            }
            else{
                email.error = "Please enter email"
                password.error = "Please enter password"
            }
        }

        findViewById<TextView>(R.id.signuptxt).setOnClickListener {
            startActivity(Intent(this, SignUp::class.java))
        }

        if(auth.currentUser!=null){
            startActivity(Intent(this, HomeScreen::class.java))
        }
    }
}