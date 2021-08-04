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

public class StudentScheduleSelectActivity extends AppCompatActivity {

    Button btnNextStep2;
    EditText etBlock1;
    EditText etBlock2;
    EditText etBlock3;
    EditText etBlock4;

    FirebaseFirestore db = FirebaseFirestore.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_schedule_select);

        btnNextStep2 = findViewById(R.id.btnNextStep2);
        etBlock1 = findViewById(R.id.etBlock1);
        etBlock2 = findViewById(R.id.etBlock2);
        etBlock3 = findViewById(R.id.etBlock3);
        etBlock4 = findViewById(R.id.etBlock4);

        btnNextStep2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String block1 = etBlock1.getText().toString();
                String block2 = etBlock2.getText().toString();
                String block3 = etBlock3.getText().toString();
                String block4 = etBlock4.getText().toString();

                String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();

                Map<String, Object> schedule = new HashMap<>();
                schedule.put("Block 1", block1);
                schedule.put("Block 2", block2);
                schedule.put("Block 3", block3);
                schedule.put("Block 4", block4);

                db.collection("Users").document(uid).update(schedule)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(getApplicationContext(), "Data Saved", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(StudentScheduleSelectActivity.this, StudentScheduleSelectActivity2.class);
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
