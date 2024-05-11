package com.example.cargram

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import com.google.firebase.auth.FirebaseAuth

class SignUp : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        var email= findViewById<EditText>(R.id.emailfield)
        var password= findViewById<EditText>(R.id.passwordfield)
        var auth= FirebaseAuth.getInstance()


        var signUP= findViewById<AppCompatButton>(R.id.signupbtn)
        signUP.setOnClickListener {
            if(email.text.toString().isNotEmpty() && password.text.toString().isNotEmpty()){
                auth.createUserWithEmailAndPassword(email.text.toString(),password.text.toString())
                    .addOnCompleteListener {
                        Toast.makeText(this, "User Created", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this, HomeScreen::class.java))
                    }
                    .addOnFailureListener{
                        Toast.makeText(this, "Error in Creating User", Toast.LENGTH_SHORT).show()
                    }
            }
            else{
                email.error = "Please enter email"
                password.error = "Please enter password"
            }
        }

        findViewById<TextView>(R.id.logintxt).setOnClickListener {
            startActivity(Intent(this, Login::class.java))
        }





    }
}