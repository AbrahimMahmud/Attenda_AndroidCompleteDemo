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

public class StudentInformationFormActivity extends AppCompatActivity {

    Button btnNextStep;
    EditText etFirstName;
    EditText etLastName;
    EditText etSchoolID;
    FirebaseFirestore firebaseFirestore;


    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_information_form);

        btnNextStep = findViewById(R.id.btnUpdateInfo);
        etFirstName = findViewById(R.id.etUpdateFirstName);
        etLastName = findViewById(R.id.etUpdateLastName);
        etSchoolID = findViewById(R.id.etUpdateSchoolID);

        btnNextStep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String firstName = etFirstName.getText().toString();
                String lastName = etLastName.getText().toString();
                String schoolID = etSchoolID.getText().toString();

                String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
                String uid2 = FirebaseAuth.getInstance().getCurrentUser().getUid();

                Map<String, Object> data = new HashMap<>();
                data.put("User ID", uid2);

                db.collection("Users").document(uid).set(data)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Map<String, Object> userdata = new HashMap<>();
                                userdata.put("firstName", firstName);
                                userdata.put("lastName", lastName);
                                userdata.put("schoolID", schoolID);
                                db.collection("Users").document(uid).update(userdata)
                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void unused) {
                                                Toast.makeText(getApplicationContext(), "Data Saved", Toast.LENGTH_SHORT).show();
                                                Intent intent = new Intent(StudentInformationFormActivity.this, StudentScheduleSelectActivity.class);
                                                startActivity(intent);
                                                finish();
                                            }
                                        })
                                        .addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                Toast.makeText(getApplicationContext(), "Error Occurred, Could Not Switch Activities", Toast.LENGTH_SHORT).show();
                                            }
                                        });
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