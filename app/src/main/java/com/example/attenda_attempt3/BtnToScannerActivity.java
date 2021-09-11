package com.example.attenda_attempt3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class BtnToScannerActivity extends AppCompatActivity {

    public static TextView scanResultText;
    Button btnStartScan;
    Button btnEditProfile;
    private FirebaseAuth firebaseAuth;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_btn_to_scanner);

        btnStartScan = findViewById(R.id.btnStartScan);
        btnEditProfile = findViewById(R.id.btnEditProfile);
        scanResultText = findViewById(R.id.scanResultText);
        firebaseAuth = FirebaseAuth.getInstance();

        btnStartScan.setOnClickListener(view -> startActivity(new Intent(getApplicationContext(), QRScannerActivity.class)));

        btnEditProfile.setOnClickListener(view -> {
            Intent intent = new Intent(BtnToScannerActivity.this, EditProfileActivity.class);
            startActivity(intent);
            finish();
        });
    }
}