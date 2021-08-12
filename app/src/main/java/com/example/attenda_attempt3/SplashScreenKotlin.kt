package com.example.attenda_attempt3

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class SplashScreenKotlin : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    var db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen_kotlin)

        auth = FirebaseAuth.getInstance()
        val user = auth.currentUser

        Handler().postDelayed({
            if (user != null) {
                val StudentFormIntent = Intent(this, StudentInformationFormActivity::class.java)
                startActivity(StudentFormIntent)
                finish()
            } else {
                val LoginIntent = Intent(this, LoginActivityKotlin::class.java)
                startActivity(LoginIntent)
                finish()
            }
        },2500)
    }
}