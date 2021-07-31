package com.example.attenda_attempt3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.google.firebase.auth.FirebaseAuth

class SplashScreenKotlin : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen_kotlin)

        mAuth = FirebaseAuth.getInstance()
        val user = mAuth.currentUser

        Handler().postDelayed({
            if (user != null){
                val StudentFormIntent = Intent(this, StudentInformationFormActivity::class.java)
                startActivity(StudentFormIntent)
                finish()
            }else{
                val LoginIntent = Intent(this, LoginActivityKotlin::class.java)
                startActivity(LoginIntent)
                finish()
            }
        },2500)
    }
}