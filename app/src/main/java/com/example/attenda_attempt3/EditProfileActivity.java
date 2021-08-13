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
import com.google.firebase.database.annotations.NotNull;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class EditProfileActivity extends AppCompatActivity {

    Button btnUpdateInfo;
    EditText etUpdateFullName;
    EditText etUpdateSchoolID;

    FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        btnUpdateInfo = findViewById(R.id.btnUpdateInfo);
        etUpdateFullName = findViewById(R.id.etFullName);
        etUpdateSchoolID = findViewById(R.id.etSchoolID);

        btnUpdateInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String updatedStudentName = etUpdateFullName.getText().toString();
                String updatedSchoolID = etUpdateSchoolID.getText().toString();

                String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();

                Map<String, Object> updateUserInfo = new HashMap<>();
                updateUserInfo.put("studentName", updatedStudentName);
                updateUserInfo.put("schoolID", updatedSchoolID);


                firebaseFirestore.collection("Users").document(uid).update(updateUserInfo)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(getApplicationContext(), "Data Updated", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(EditProfileActivity.this, BtnToScannerActivity.class);
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