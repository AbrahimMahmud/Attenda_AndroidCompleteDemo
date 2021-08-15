package com.example.attenda_attempt3;

import static com.example.attenda_attempt3.QRScannerActivity.dateOfQRScan;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class DaysOneToFourHandlerActivity extends AppCompatActivity {

    public static String day;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_status_handler);

        //test date
        String August14 = "08/14/2021";

        //october weekdays
        String October1 = "10/01/2021";
        String October4 = "10/04/2021";
        String October5 = "10/05/2021";
        String October6 = "10/06/2021";
        String October7 = "10/07/2021";
        String October8 = "10/08/2021";
        String October11 = "10/11/2021";
        String October12 = "10/12/2021";
        String October13 = "10/13/2021";
        String October14 = "10/14/2021";
        String October15 = "10/15/2021";
        String October18 = "10/18/2021";
        String October19 = "10/19/2021";
        String October20 = "10/20/2021";
        String October21 = "10/21/2021";
        String October22 = "10/22/2021";
        String October25 = "10/25/2021";
        String October26 = "10/26/2021";
        String October27 = "10/27/2021";
        String October28 = "10/28/2021";
        String October29 = "10/29/2021";

        //november weekdays
        String November1 = "11/01/2021";
        String November2 = "11/02/2021";
        String November3 = "11/03/2021";
        String November4 = "11/04/2021";
        String November5 = "11/05/2021";
        String November8 = "11/08/2021";
        String November9 = "11/09/2021";
        String November10 = "11/10/2021";
        String November11 = "11/11/2021";
        String November12 = "11/12/2021";
        String November15 = "11/15/2021";
        String November16 = "11/16/2021";
        String November17 = "11/17/2021";
        String November18 = "11/18/2021";
        String November19 = "11/19/2021";
        String November22 = "11/22/2021";
        String November23 = "11/23/2021";
        String November24 = "11/24/2021";
        //winter holidays follow

        //school schedule days 1-4
        String Day1 = "day1";
        String Day2 = "day2";
        String Day3 = "day3";
        String Day4 = "day4";

        String dateOfScan = dateOfQRScan;

        //test date
        if (dateOfScan.equals(August14)) {
            day = Day4;
        }

        //october weekdays
        if (dateOfScan.equals(October1)) {
            day = Day4;
        }
        if (dateOfScan.equals(October4)) {
            day = Day1;
        }
        if (dateOfScan.equals(October5)) {
            day = Day2;
        }
        if (dateOfScan.equals(October6)) {
            day = Day3;
        }
        if (dateOfScan.equals(October7)) {
            day = Day4;
        }
        if (dateOfScan.equals(October8)) {
            day = Day1;
        }
        if (dateOfScan.equals(October11)) {
            day = Day2;
        }
        if (dateOfScan.equals(October12)) {
            day = Day3;
        }
        if (dateOfScan.equals(October13)) {
            day = Day4;
        }
        if (dateOfScan.equals(October14)) {
            day = Day1;
        }
        if (dateOfScan.equals(October15)) {
            day = Day2;
        }
        if (dateOfScan.equals(October18)) {
            day = Day3;
        }
        if (dateOfScan.equals(October19)) {
            day = Day4;
        }
        if (dateOfScan.equals(October20)) {
            day = Day1;
        }
        if (dateOfScan.equals(October21)) {
            day = Day2;
        }
        if (dateOfScan.equals(October22)) {
            day = Day3;
        }
        if (dateOfScan.equals(October25)) {
            day = Day4;
        }
        if (dateOfScan.equals(October26)) {
            day = Day1;
        }
        if (dateOfScan.equals(October27)) {
            day = Day2;
        }
        if (dateOfScan.equals(October28)) {
            day = Day3;
        }
        if (dateOfScan.equals(October29)) {
            day = Day4;
        }

        //november weekdays
        if (dateOfScan.equals(November1)) {
            day = Day1;
        }
        if (dateOfScan.equals(November2)) {
            day = Day2;
        }
        if (dateOfScan.equals(November3)) {
            day = Day3;
        }
        if (dateOfScan.equals(November4)) {
            day = Day4;
        }
        if (dateOfScan.equals(November5)) {
            day = Day1;
        }
        if (dateOfScan.equals(November8)) {
            day = Day2;
        }
        if (dateOfScan.equals(November9)) {
            day = Day3;
        }
        if (dateOfScan.equals(November10)) {
            day = Day4;
        }
        if (dateOfScan.equals(November11)) {
            day = Day1;
        }
        if (dateOfScan.equals(November12)) {
            day = Day2;
        }
        if (dateOfScan.equals(November15)) {
            day = Day3;
        }
        if (dateOfScan.equals(November16)) {
            day = Day4;
        }
        if (dateOfScan.equals(November17)) {
            day = Day1;
        }
        if (dateOfScan.equals(November18)) {
            day = Day2;
        }
        if (dateOfScan.equals(November19)) {
            day = Day3;
        }
        if (dateOfScan.equals(November22)) {
            day = Day4;
        }
        if (dateOfScan.equals(November23)) {
            day = Day1;
        }
        if (dateOfScan.equals(November24)) {
            day = Day2;
        }

        Map<String, Object> dayOfScan = new HashMap<>();
        dayOfScan.put("dayOneToFour`", day);

        db.collection("dayOneThroughFour").document("dayOneThoughFour").set(dayOfScan)
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(), "Error, Day Not Set", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}