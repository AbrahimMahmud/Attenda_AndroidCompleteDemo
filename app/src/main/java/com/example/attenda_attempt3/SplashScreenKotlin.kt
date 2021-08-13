package com.example.attenda_attempt3

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore

class SplashScreenKotlin : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    var db = FirebaseFirestore.getInstance()
    var uid3 = FirebaseAuth.getInstance().currentUser!!.uid
    private val userRef: DocumentReference = db.collection("users").document(uid3)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen_kotlin)

        auth = FirebaseAuth.getInstance()
        val user = auth.currentUser

        Handler().postDelayed({
            if (user != null) {
                userRef.get()
                    .addOnSuccessListener { documentSnapshot ->
                        if (documentSnapshot.exists()) {
                            val userdata = documentSnapshot.data
                            val intent2 = Intent(this, BtnToScannerActivity::class.java)
                            startActivity(intent2)
                            finish()
                        } else {
                            val StudentFormIntent =
                                Intent(this, StudentInformationFormActivity::class.java)
                            startActivity(StudentFormIntent)
                            finish()
                        }
                    }
            } else {
                val LoginIntent = Intent(this, LoginActivityKotlin::class.java)
                startActivity(LoginIntent)
                finish()
            }
        },2500)
    }
}