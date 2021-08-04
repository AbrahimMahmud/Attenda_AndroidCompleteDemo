package com.example.attenda_attempt3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class StudentScheduleSelectActivity2 extends AppCompatActivity {

    Button btnOnward;
    EditText etBlock5;
    EditText etBlock6;
    EditText etBlock7;
    EditText etBlock8;

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_schedule_select2);

        btnOnward = findViewById(R.id.btnOnward);
        etBlock5 = findViewById(R.id.etBlock5);
        etBlock6 = findViewById(R.id.etBlock6);
        etBlock7 = findViewById(R.id.etBlock7);
        etBlock8 = findViewById(R.id.etBlock8);

        btnOnward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String block5 = etBlock5.getText().toString();
                String block6 = etBlock6.getText().toString();
                String block7 = etBlock7.getText().toString();
                String block8 = etBlock8.getText().toString();

                String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();

                Map<String, Object> schedule2 = new HashMap<>();
                schedule2.put("Block 5", block5);
                schedule2.put("Block 6", block6);
                schedule2.put("Block 7", block7);
                schedule2.put("Block 8", block8);

                db.collection("Users").document(uid).update(schedule2)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(getApplicationContext(), "Data Saved", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(StudentScheduleSelectActivity2.this, WelcomeUserFeaturesActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull @NotNull Exception e) {
                                Toast.makeText(getApplicationContext(), "Error Occurred, Data Not Saved", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
    }
}