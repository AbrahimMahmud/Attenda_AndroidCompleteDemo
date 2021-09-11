package com.example.attenda_attempt3;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class StudentInformationFormActivity extends AppCompatActivity {

    Button btnNextStep;
    public static EditText etFirstAndLastName;
    EditText etSchoolID;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_information_form);

        btnNextStep = findViewById(R.id.btnUpdateInfo);
        etFirstAndLastName = findViewById(R.id.etFullName);
        etSchoolID = findViewById(R.id.etSchoolID);

        btnNextStep.setOnClickListener(view -> {
            String fullName = etFirstAndLastName.getText().toString();
            String schoolID = etSchoolID.getText().toString();

            String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
            String uid2 = FirebaseAuth.getInstance().getCurrentUser().getUid();

            Map<String, Object> data = new HashMap<>();
            data.put("userID", uid2);

            db.collection("users").document(uid).set(data)
                    .addOnSuccessListener(unused -> {
                        Map<String, Object> userdata = new HashMap<>();
                        userdata.put("studentName", fullName);
                        userdata.put("schoolID", schoolID);
                        db.collection("users").document(uid).update(userdata)
                                .addOnSuccessListener(unused1 -> {
                                    Toast.makeText(getApplicationContext(), "Data Saved", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(StudentInformationFormActivity.this, StudentScheduleSelectActivity.class);
                                    startActivity(intent);
                                    finish();
                                })
                                .addOnFailureListener(e -> Toast.makeText(getApplicationContext(), "Error Occurred, Could Not Switch Activities", Toast.LENGTH_SHORT).show());
                    })
                    .addOnFailureListener(e -> Toast.makeText(getApplicationContext(), "Error Occurred, Data Not Saved", Toast.LENGTH_SHORT).show());
        });

    }
}