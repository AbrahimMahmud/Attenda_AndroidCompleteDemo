package com.example.attenda_attempt3;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class SplashScreenActivity extends AppCompatActivity {

    FirebaseAuth firebaseAuth;
    FirebaseFirestore firebaseFirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {

                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                if (user != null) {
                    String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
                    DocumentReference documentReference = firebaseFirestore.collection("users").document(uid);
                    documentReference.get()
                            .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                @Override
                                public void onSuccess(DocumentSnapshot documentSnapshot) {
                                    if (documentSnapshot.exists()) {
                                        Intent intent = new Intent(SplashScreenActivity.this, BtnToScannerActivity.class);
                                        startActivity(intent);
                                        finish();
                                    } else {
                                        Intent intent2 = new Intent(SplashScreenActivity.this, StudentInformationFormActivity.class);
                                        startActivity(intent2);
                                        finish();
                                    }
                                }

                            });
                } else {
                    Intent loginIntent = new Intent(SplashScreenActivity.this, LoginActivityKotlin.class);
                    startActivity(loginIntent);
                    finish();
                }
            }
        };

        handler.postDelayed(runnable, 2500);

    }
}