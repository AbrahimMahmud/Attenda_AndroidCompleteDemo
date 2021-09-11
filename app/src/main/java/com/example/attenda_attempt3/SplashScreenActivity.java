package com.example.attenda_attempt3;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class SplashScreenActivity extends AppCompatActivity {

    DocumentReference documentReference;
    private static final String TAG = "SplashScreenActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        Handler handler = new Handler();
        Runnable runnable = () -> {

            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();

            if (user != null) {
                String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
                ComponentName componentName = new ComponentName(SplashScreenActivity.this, StatusRemoverMidnightService.class);
                JobInfo info = new JobInfo.Builder(123, componentName)
                        .setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY)
                        .setPersisted(true)
                        .build();
                JobScheduler scheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
                int resultCode = scheduler.schedule(info);
                if (resultCode == JobScheduler.RESULT_SUCCESS) {
                    Log.d(TAG, "Job Scheduled");
                } else {
                    Log.d(TAG, "Job Scheduling Failed");
                }
                documentReference = firebaseFirestore.collection("users").document(uid);
                documentReference.get()
                        .addOnSuccessListener(documentSnapshot -> {
                            if (documentSnapshot.exists()) {
                                Intent intent = new Intent(SplashScreenActivity.this, BtnToScannerActivity.class);
                                startActivity(intent);
                                finish();
                            } else {
                                Intent intent2 = new Intent(SplashScreenActivity.this, StudentInformationFormActivity.class);
                                startActivity(intent2);
                                finish();
                            }
                        });
            } else {
                Intent loginIntent = new Intent(SplashScreenActivity.this, LoginActivityKotlin.class);
                startActivity(loginIntent);
                finish();
            }
        };

        handler.postDelayed(runnable, 1500);

    }
}