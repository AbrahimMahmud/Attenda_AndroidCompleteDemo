package com.example.attenda_attempt3;

import static com.example.attenda_attempt3.StudentInformationFormActivity.etFirstAndLastName;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
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
    CheckBox checkboxBlock1, checkboxBlock2, checkboxBlock3, checkboxBlock4;

    public static String block1Text, block2Text, block3Text, block4Text;

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
        checkboxBlock1 = findViewById(R.id.checkboxBlock1);
        checkboxBlock2 = findViewById(R.id.checkboxBlock2);
        checkboxBlock3 = findViewById(R.id.checkboxBlock3);
        checkboxBlock4 = findViewById(R.id.checkboxBlock4);

        //change room number with actual room number
        //1st room, blocks 1-4
        String r111b1 = "111-1";
        String r111b2 = "111-2";
        String r111b3 = "111-3";
        String r111b4 = "111-4";

        //2nd room, blocks 1-4
        String r112b1 = "112-1";
        String r112b2 = "112-2";
        String r112b3 = "112-3";
        String r112b4 = "112-4";

        String fullName = etFirstAndLastName.getText().toString();


        btnNextStep2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //checkboxBlock1 start
                if (checkboxBlock1.isChecked()) {
                    String block1 = etBlock1.getText().toString();

                    String uid1 = FirebaseAuth.getInstance().getCurrentUser().getUid();

                    if (block1.equals(r111b1)) {
                        Map<String, Object> checkbox1 = new HashMap<>();
                        checkbox1.put("mainClass", block1);
                        checkbox1.put("studentName", fullName);

                        db.collection(block1).document(uid1).set(checkbox1)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(getApplicationContext(), "Main Class Set", Toast.LENGTH_SHORT).show();
                                    }
                                });
                    }
                    if (block1.equals(r112b1)) {
                        Map<String, Object> checkbox1 = new HashMap<>();
                        checkbox1.put("mainClass", block1);
                        checkbox1.put("studentName", fullName);

                        db.collection(block1).document(uid1).set(checkbox1)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(getApplicationContext(), "Main Class Set", Toast.LENGTH_SHORT).show();
                                    }
                                });
                    }
                }
                //checkboxBlock1 end

                //checkboxBlock2 start
                if (checkboxBlock2.isChecked()) {
                    String block2 = etBlock2.getText().toString();

                    String uid2 = FirebaseAuth.getInstance().getCurrentUser().getUid();

                    if (block2.equals(r111b2)) {
                        Map<String, Object> checkbox2 = new HashMap<>();
                        checkbox2.put("mainClass", block2);
                        checkbox2.put("studentName", fullName);

                        db.collection(block2).document(uid2).set(checkbox2)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(getApplicationContext(), "Main Class Set", Toast.LENGTH_SHORT).show();
                                    }
                                });
                    }
                    if (block2.equals(r112b2)) {
                        Map<String, Object> checkbox2 = new HashMap<>();
                        checkbox2.put("mainClass", block2);
                        checkbox2.put("studentName", fullName);

                        db.collection(block2).document(uid2).set(checkbox2)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(getApplicationContext(), "Main Class Set", Toast.LENGTH_SHORT).show();
                                    }
                                });
                    }
                }
                //checkboxBlock2 end

                //checkboxBlock3 start
                if (checkboxBlock3.isChecked()) {
                    String block3 = etBlock3.getText().toString();

                    String uid3 = FirebaseAuth.getInstance().getCurrentUser().getUid();

                    if (block3.equals(r111b3)) {
                        Map<String, Object> checkbox3 = new HashMap<>();
                        checkbox3.put("mainClass", block3);
                        checkbox3.put("studentName", fullName);

                        db.collection(block3).document(uid3).set(checkbox3)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(getApplicationContext(), "Main Class Set", Toast.LENGTH_SHORT).show();
                                    }
                                });
                    }
                    if (block3.equals(r112b3)) {
                        Map<String, Object> checkbox3 = new HashMap<>();
                        checkbox3.put("mainClass", block3);
                        checkbox3.put("studentName", fullName);

                        db.collection(block3).document(uid3).set(checkbox3)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(getApplicationContext(), "Main Class Set", Toast.LENGTH_SHORT).show();
                                    }
                                });
                    }
                }
                //checkboxBlock3 end

                //checkboxBlock4 start
                if (checkboxBlock4.isChecked()) {
                    String block4 = etBlock4.getText().toString();

                    String uid4 = FirebaseAuth.getInstance().getCurrentUser().getUid();
                    if (block4.equals(r111b4)) {
                        Map<String, Object> checkbox4 = new HashMap<>();
                        checkbox4.put("mainClass", block4);
                        checkbox4.put("studentName", fullName);

                        db.collection(block4).document(uid4).set(checkbox4)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(getApplicationContext(), "Main Class Set", Toast.LENGTH_SHORT).show();
                                    }
                                });
                    }
                    if (block4.equals(r112b4)) {
                        Map<String, Object> checkbox4 = new HashMap<>();
                        checkbox4.put("mainClass", block4);
                        checkbox4.put("studentName", fullName);

                        db.collection(block4).document(uid4).set(checkbox4)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(getApplicationContext(), "Main Class Set", Toast.LENGTH_SHORT).show();
                                    }
                                });
                    }
                }
                //checkboxBlock4 end

                String block1 = etBlock1.getText().toString();
                String block2 = etBlock2.getText().toString();
                String block3 = etBlock3.getText().toString();
                String block4 = etBlock4.getText().toString();

                String block1Text = block1;
                String block2Text = block2;
                String block3Text = block3;
                String block4Text = block4;

                String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();

                Map<String, Object> schedule = new HashMap<>();
                schedule.put("block1", block1);
                schedule.put("block2", block2);
                schedule.put("block3", block3);
                schedule.put("block4", block4);

                db.collection("users").document(uid).update(schedule)
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