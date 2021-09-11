package com.example.attenda_attempt3;

import static com.example.attenda_attempt3.StudentInformationFormActivity.etFirstAndLastName;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class StudentScheduleSelectActivity2 extends AppCompatActivity {

    Button btnOnward;
    EditText etBlock5;
    EditText etBlock6;
    EditText etBlock7;
    EditText etBlock8;
    CheckBox checkboxBlock1, checkboxBlock2, checkboxBlock3, checkboxBlock4, checkboxBlock5, checkboxBlock6, checkboxBlock7, checkboxBlock8;

    public static String block5Text, block6Text, block7Text, block8Text;

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
        checkboxBlock1 = findViewById(R.id.checkboxBlock1);
        checkboxBlock2 = findViewById(R.id.checkboxBlock2);
        checkboxBlock3 = findViewById(R.id.checkboxBlock3);
        checkboxBlock4 = findViewById(R.id.checkboxBlock4);
        checkboxBlock5 = findViewById(R.id.checkboxBlock5);
        checkboxBlock6 = findViewById(R.id.checkboxBlock6);
        checkboxBlock7 = findViewById(R.id.checkboxBlock7);
        checkboxBlock8 = findViewById(R.id.checkboxBlock8);

        //change room number with actual room number
        //1st room, blocks 5-8
        String r111b5 = "111-5";
        String r111b6 = "111-6";
        String r111b7 = "111-7";
        String r111b8 = "111-8";

        //2nd room, blocks 5-8
        String r112b5 = "112-5";
        String r112b6 = "112-6";
        String r112b7 = "112-7";
        String r112b8 = "112-8";

        String fullName = etFirstAndLastName.getText().toString();

        btnOnward.setOnClickListener(view -> {
            //checkboxBlock5 start
            if (checkboxBlock5.isChecked()) {
                String block5 = etBlock5.getText().toString();
                String uid5 = FirebaseAuth.getInstance().getCurrentUser().getUid();

                if (block5.equals(r111b5)) {
                    Map<String, Object> checkbox5 = new HashMap<>();
                    checkbox5.put("mainClass", block5);
                    checkbox5.put("studentName", fullName);

                    db.collection(block5).document(uid5).set(checkbox5)
                            .addOnSuccessListener(unused -> Toast.makeText(getApplicationContext(), "Main Class Set", Toast.LENGTH_SHORT).show());
                }
                if (block5.equals(r112b5)) {
                    Map<String, Object> checkbox5 = new HashMap<>();
                    checkbox5.put("mainClass", block5);
                    checkbox5.put("studentName", fullName);


                    db.collection(block5).document(uid5).set(checkbox5)
                            .addOnSuccessListener(unused -> Toast.makeText(getApplicationContext(), "Main Class Set", Toast.LENGTH_SHORT).show());
                }
            }
            //checkboxBlock5 end

            //checkboxBlock6 start
            if (checkboxBlock6.isChecked()) {


                String block6 = etBlock6.getText().toString();

                String uid6 = FirebaseAuth.getInstance().getCurrentUser().getUid();

                if (block6.equals(r111b6)) {
                    Map<String, Object> checkbox6 = new HashMap<>();
                    checkbox6.put("mainClass", block6);
                    checkbox6.put("studentName", fullName);

                    db.collection(block6).document(uid6).set(checkbox6)
                            .addOnSuccessListener(unused -> Toast.makeText(getApplicationContext(), "Main Class Set", Toast.LENGTH_SHORT).show());
                }
                if (block6.equals(r112b6)) {
                    Map<String, Object> checkbox6 = new HashMap<>();
                    checkbox6.put("mainClass", block6);
                    checkbox6.put("studentName", fullName);

                    db.collection(block6).document(uid6).set(checkbox6)
                            .addOnSuccessListener(unused -> Toast.makeText(getApplicationContext(), "Main Class Set", Toast.LENGTH_SHORT).show());
                }
            }
            //checkboxBlock6 end

            //checkboxBlock7 start
            if (checkboxBlock7.isChecked()) {
                String block7 = etBlock7.getText().toString();

                String uid7 = FirebaseAuth.getInstance().getCurrentUser().getUid();

                if (block7.equals(r111b7)) {
                    Map<String, Object> checkbox7 = new HashMap<>();
                    checkbox7.put("mainClass", block7);
                    checkbox7.put("studentName", fullName);

                    db.collection(block7).document(uid7).set(checkbox7)
                            .addOnSuccessListener(unused -> Toast.makeText(getApplicationContext(), "Main Class Set", Toast.LENGTH_SHORT).show());
                }
                if (block7.equals(r112b7)) {
                    Map<String, Object> checkbox7 = new HashMap<>();
                    checkbox7.put("mainClass", block7);
                    checkbox7.put("studentName", fullName);

                    db.collection(block7).document(uid7).set(checkbox7)
                            .addOnSuccessListener(unused -> Toast.makeText(getApplicationContext(), "Main Class Set", Toast.LENGTH_SHORT).show());
                }
            }
            if (checkboxBlock8.isChecked()) {
                String block8 = etBlock8.getText().toString();

                String uid8 = FirebaseAuth.getInstance().getCurrentUser().getUid();
                if (block8.equals(r111b8)) {
                    Map<String, Object> checkbox8 = new HashMap<>();
                    checkbox8.put("mainClass", block8);
                    checkbox8.put("studentName", fullName);

                    db.collection(block8).document(uid8).set(checkbox8)
                            .addOnSuccessListener(unused -> Toast.makeText(getApplicationContext(), "Main Class Set", Toast.LENGTH_SHORT).show());
                }
                if (block8.equals(r112b8)) {
                    Map<String, Object> checkbox8 = new HashMap<>();
                    checkbox8.put("mainClass", block8);
                    checkbox8.put("studentName", fullName);

                    db.collection(block8).document(uid8).set(checkbox8)
                            .addOnSuccessListener(unused -> Toast.makeText(getApplicationContext(), "Main Class Set", Toast.LENGTH_SHORT).show());
                }
            }


            //send data of ALL blocks to "users" collection for notifications
            String block5 = etBlock5.getText().toString();
            String block6 = etBlock6.getText().toString();
            String block7 = etBlock7.getText().toString();
            String block8 = etBlock8.getText().toString();

            String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();

            Map<String, Object> schedule2 = new HashMap<>();
            schedule2.put("block5", block5);
            schedule2.put("block6", block6);
            schedule2.put("block7", block7);
            schedule2.put("block8", block8);

            db.collection("users").document(uid).update(schedule2)
                    .addOnSuccessListener(unused -> {
                        Toast.makeText(getApplicationContext(), "Data Saved", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(StudentScheduleSelectActivity2.this, BtnToScannerActivity.class);
                        startActivity(intent);
                        finish();
                    })
                    .addOnFailureListener(e -> Toast.makeText(getApplicationContext(), "Error Occurred, Data Not Saved", Toast.LENGTH_SHORT).show());
        });
    }
}