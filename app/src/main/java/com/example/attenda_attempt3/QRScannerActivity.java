package com.example.attenda_attempt3;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.zxing.Result;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class QRScannerActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler {

    public static String dateOfQRScan;
    ZXingScannerView scannerView;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    String day;
    String timeForStatus;
    String timeOfScan;
    String r111b1 = "111-1";
    String r111b2 = "111-2";
    String r111b3 = "111-3";
    String r111b4 = "111-4";
    String r111b5 = "111-5";
    String r111b6 = "111-6";
    String r111b7 = "111-7";
    String r111b8 = "111-8";
    String r112b1 = "112-1";
    String r112b2 = "112-2";
    String r112b3 = "112-3";
    String r112b4 = "112-4";
    String r112b5 = "112-5";
    String r112b6 = "112-6";
    String r112b7 = "112-7";
    String r112b8 = "112-8";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        scannerView = new ZXingScannerView(this);
        setContentView(scannerView);

        Dexter.withContext(getApplicationContext())
                .withPermission(Manifest.permission.CAMERA)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                        scannerView.startCamera();
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {

                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
                        permissionToken.continuePermissionRequest();
                    }
                }).check();
    }

    @Override
    public void handleResult(Result rawResult) {
        String QRScanText = rawResult.getText();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HHmm");
        SimpleDateFormat dateOfScanStatus = new SimpleDateFormat("MM/dd/yyyy");
        SimpleDateFormat timeColon = new SimpleDateFormat("HH:mm");

        dateOfQRScan = dateOfScanStatus.format(new Date());
        timeForStatus = simpleDateFormat.format(new Date());
        timeOfScan = timeColon.format(new Date());

        Map<String, Object> Block = new HashMap<>();
        Block.put("currentBlock", QRScanText);
        Block.put("dateOfScanForStatus", dateOfQRScan);
        Block.put("timeForStatus", timeForStatus);
        Block.put("timeOfScan", timeOfScan);

        Map<String, Object> Date = new HashMap<>();
        Date.put("dateOfScanForStatus", dateOfQRScan);

        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();

        db.collection("dateOfScanForStatus").document("dateOfScanForStatus").update(Date);

        db.collection("users").document(uid).update(Block)
                .addOnSuccessListener(unused -> {
                    Runnable runnable = () -> {
                        Intent intent = new Intent(QRScannerActivity.this, AttendanceSubmittedActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        finish();
                    };
                    Handler handler = new Handler();
                    handler.postDelayed(runnable, 1);
                })
                .addOnFailureListener(e -> Toast.makeText(getApplicationContext(), "Error Occurred, Data Not Saved", Toast.LENGTH_SHORT).show());
        //test date
        String September11 = "09/11/2021";

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
        if (dateOfScan.equals(September11)) {
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
                .addOnFailureListener(e -> Toast.makeText(getApplicationContext(), "Error, Day Not Set", Toast.LENGTH_SHORT).show());

        int timeForStatusNum = Integer.parseInt(timeForStatus);

        int firstBlockStart = 835;
        int secondBlockStart = 940;
        int thirdBlockStart = 1035;
        int fourthBlockStart = 1130;
        int fifthBlockStart = 1310;
        int sixthBlockStart = 1405;

        int diffInTimeFirstBlock;
        int diffInTimeSecondBlock;
        int diffInTimeThirdBlock;
        int diffInTimeFourthBlock;
        int diffInTimeFifthBlock;
        int diffInTimeSixthBlock;

        String tardy = "tardy";
        String present = "present";
        String absent = "absent";

        diffInTimeFirstBlock = firstBlockStart - timeForStatusNum;
        diffInTimeSecondBlock = secondBlockStart - timeForStatusNum;
        diffInTimeThirdBlock = thirdBlockStart - timeForStatusNum;
        diffInTimeFourthBlock = fourthBlockStart - timeForStatusNum;
        diffInTimeFifthBlock = fifthBlockStart - timeForStatusNum;
        diffInTimeSixthBlock = sixthBlockStart - timeForStatusNum;

        String uid2 = FirebaseAuth.getInstance().getCurrentUser().getUid();


        if (day.equals(Day1)) {
            //ROOM 111 BLOCK 1 STATUS
            if (QRScanText.equals(r111b1)) {
                if (diffInTimeFirstBlock < 0 && diffInTimeFirstBlock > -60) {
                    String status;
                    status = tardy;
                    Map<String, Object> tardy1 = new HashMap<>();
                    tardy1.put("status", status);
                    db.collection("111-1").document(uid2).update(tardy1)
                            .addOnSuccessListener(unused -> Toast.makeText(getApplicationContext(), "Status Updated: Tardy", Toast.LENGTH_SHORT).show());
                } else if (diffInTimeFirstBlock >= 0 && diffInTimeFirstBlock <= 35) {
                    String status;
                    status = present;
                    Map<String, Object> present1 = new HashMap<>();
                    present1.put("status", status);
                    db.collection("111-1").document(uid2).update(present1)
                            .addOnSuccessListener(unused -> Toast.makeText(getApplicationContext(), "Status Updated: Present", Toast.LENGTH_SHORT).show());
                } else if (diffInTimeFirstBlock < -60) {
                    String status;
                    status = absent;
                    Map<String, Object> absent1 = new HashMap<>();
                    absent1.put("status", status);
                    db.collection("111-1").document(uid2).update(absent1);
                } else {
                    Toast.makeText(getApplicationContext(), "Attendance NOT Updated. This class hasn't started yet, please wait for the class to begin to scan the QR Code", Toast.LENGTH_SHORT).show();
                }
            }

            //ROOM 112 BLOCK 1 STATUS
            if (QRScanText.equals(r112b1)) {
                if (diffInTimeFirstBlock < 0 && diffInTimeFirstBlock > -60) {
                    String status;
                    status = tardy;
                    Map<String, Object> tardy1 = new HashMap<>();
                    tardy1.put("status", status);
                    db.collection("112-1").document(uid2).update(tardy1)
                            .addOnSuccessListener(unused -> Toast.makeText(getApplicationContext(), "Status Updated: Tardy", Toast.LENGTH_SHORT).show());
                } else if (diffInTimeFirstBlock >= 0 && diffInTimeFirstBlock <= 35) {
                    String status;
                    status = present;
                    Map<String, Object> present1 = new HashMap<>();
                    present1.put("status", status);
                    db.collection("112-1").document(uid2).update(present1)
                            .addOnSuccessListener(unused -> Toast.makeText(getApplicationContext(), "Status Updated: Present", Toast.LENGTH_SHORT).show());
                } else if (diffInTimeFirstBlock < -60) {
                    String status;
                    status = absent;
                    Map<String, Object> absent1 = new HashMap<>();
                    absent1.put("status", status);
                    db.collection("112-1").document(uid2).update(absent1);
                } else {
                    Toast.makeText(getApplicationContext(), "Attendance NOT Updated. This class hasn't started yet, please wait for the class to begin to scan the QR Code", Toast.LENGTH_SHORT).show();
                }
            }

            //ROOM 111 BLOCK 2 STATUS
            if (QRScanText.equals(r111b2)) {
                if (diffInTimeSecondBlock < 0 && diffInTimeSecondBlock > -50) {
                    String status;
                    status = tardy;
                    Map<String, Object> tardy1 = new HashMap<>();
                    tardy1.put("status", status);
                    db.collection("111-2").document(uid2).update(tardy1)
                            .addOnSuccessListener(unused -> Toast.makeText(getApplicationContext(), "Status Updated: Tardy", Toast.LENGTH_SHORT).show());
                } else if (diffInTimeSecondBlock >= 0 && diffInTimeFirstBlock <= 10) {
                    String status;
                    status = present;
                    Map<String, Object> present1 = new HashMap<>();
                    present1.put("status", status);
                    db.collection("111-2").document(uid2).update(present1)
                            .addOnSuccessListener(unused -> Toast.makeText(getApplicationContext(), "Status Updated: Present", Toast.LENGTH_SHORT).show());
                } else if (diffInTimeSecondBlock < -50) {
                    String status;
                    status = absent;
                    Map<String, Object> absent1 = new HashMap<>();
                    absent1.put("status", status);
                    db.collection("111-2").document(uid2).update(absent1);
                } else {
                    Toast.makeText(getApplicationContext(), "Attendance NOT Updated. This class hasn't started yet, please wait for the class to begin to scan the QR Code", Toast.LENGTH_SHORT).show();
                }
            }

            //ROOM 112 BLOCK 2 STATUS
            if (QRScanText.equals(r112b2)) {
                if (diffInTimeSecondBlock < 0 && diffInTimeSecondBlock > -50) {
                    String status;
                    status = tardy;
                    Map<String, Object> tardy1 = new HashMap<>();
                    tardy1.put("status", status);
                    db.collection("112-2").document(uid2).update(tardy1)
                            .addOnSuccessListener(unused -> Toast.makeText(getApplicationContext(), "Status Updated: Tardy", Toast.LENGTH_SHORT).show());
                } else if (diffInTimeSecondBlock >= 0 && diffInTimeSecondBlock <= 10) {
                    String status;
                    status = present;
                    Map<String, Object> present1 = new HashMap<>();
                    present1.put("status", status);
                    db.collection("112-2").document(uid2).update(present1)
                            .addOnSuccessListener(unused -> Toast.makeText(getApplicationContext(), "Status Updated: Present", Toast.LENGTH_SHORT).show());
                } else if (diffInTimeSecondBlock < -50) {
                    String status;
                    status = absent;
                    Map<String, Object> absent1 = new HashMap<>();
                    absent1.put("status", status);
                    db.collection("112-2").document(uid2).update(absent1);
                } else {
                    Toast.makeText(getApplicationContext(), "Attendance NOT Updated. This class hasn't started yet, please wait for the class to begin to scan the QR Code", Toast.LENGTH_SHORT).show();
                }
            }

            //ROOM 111 BLOCK 3 STATUS
            if (QRScanText.equals(r111b3)) {
                if (diffInTimeThirdBlock < 0 && diffInTimeThirdBlock > -50) {
                    String status;
                    status = tardy;
                    Map<String, Object> tardy1 = new HashMap<>();
                    tardy1.put("status", status);
                    db.collection("111-3").document(uid2).update(tardy1)
                            .addOnSuccessListener(unused -> Toast.makeText(getApplicationContext(), "Status Updated: Tardy", Toast.LENGTH_SHORT).show());
                } else if (diffInTimeThirdBlock >= 0 && diffInTimeThirdBlock <= 10) {
                    String status;
                    status = present;
                    Map<String, Object> present1 = new HashMap<>();
                    present1.put("status", status);
                    db.collection("111-3").document(uid2).update(present1)
                            .addOnSuccessListener(unused -> Toast.makeText(getApplicationContext(), "Status Updated: Present", Toast.LENGTH_SHORT).show());
                } else if (diffInTimeThirdBlock < -50) {
                    String status;
                    status = absent;
                    Map<String, Object> absent1 = new HashMap<>();
                    absent1.put("status", status);
                    db.collection("111-3").document(uid2).update(absent1);
                } else {
                    Toast.makeText(getApplicationContext(), "Attendance NOT Updated. This class hasn't started yet, please wait for the class to begin to scan the QR Code", Toast.LENGTH_SHORT).show();
                }
            }

            //ROOM 112 BLOCK 3 STATUS
            if (QRScanText.equals(r112b3)) {
                if (diffInTimeThirdBlock < 0 && diffInTimeThirdBlock > -50) {
                    String status;
                    status = tardy;
                    Map<String, Object> tardy1 = new HashMap<>();
                    tardy1.put("status", status);
                    db.collection("112-3").document(uid2).update(tardy1)
                            .addOnSuccessListener(unused -> Toast.makeText(getApplicationContext(), "Status Updated: Tardy", Toast.LENGTH_SHORT).show());
                } else if (diffInTimeThirdBlock >= 0 && diffInTimeThirdBlock <= 10) {
                    String status;
                    status = present;
                    Map<String, Object> present1 = new HashMap<>();
                    present1.put("status", status);
                    db.collection("112-3").document(uid2).update(present1)
                            .addOnSuccessListener(unused -> Toast.makeText(getApplicationContext(), "Status Updated: Present", Toast.LENGTH_SHORT).show());
                } else if (diffInTimeThirdBlock < -50) {
                    String status;
                    status = absent;
                    Map<String, Object> absent1 = new HashMap<>();
                    absent1.put("status", status);
                    db.collection("112-3").document(uid2).update(absent1);
                } else {
                    Toast.makeText(getApplicationContext(), "Attendance NOT Updated. This class hasn't started yet, please wait for the class to begin to scan the QR Code", Toast.LENGTH_SHORT).show();
                }
            }

            //ROOM 111 BLOCK 5 STATUS
            if (QRScanText.equals(r111b5)) {
                if (diffInTimeFourthBlock < 0 && diffInTimeFourthBlock > -50) {
                    String status;
                    status = tardy;
                    Map<String, Object> tardy1 = new HashMap<>();
                    tardy1.put("status", status);
                    db.collection("111-5").document(uid2).update(tardy1)
                            .addOnSuccessListener(unused -> Toast.makeText(getApplicationContext(), "Status Updated: Tardy", Toast.LENGTH_SHORT).show());
                } else if (diffInTimeFourthBlock >= 0 && diffInTimeFourthBlock <= 10) {
                    String status;
                    status = present;
                    Map<String, Object> present1 = new HashMap<>();
                    present1.put("status", status);
                    db.collection("111-5").document(uid2).update(present1)
                            .addOnSuccessListener(unused -> Toast.makeText(getApplicationContext(), "Status Updated: Present", Toast.LENGTH_SHORT).show());
                } else if (diffInTimeFourthBlock < -50) {
                    String status;
                    status = absent;
                    Map<String, Object> absent1 = new HashMap<>();
                    absent1.put("status", status);
                    db.collection("111-5").document(uid2).update(absent1);
                } else {
                    Toast.makeText(getApplicationContext(), "Attendance NOT Updated. This class hasn't started yet, please wait for the class to begin to scan the QR Code", Toast.LENGTH_SHORT).show();
                }
            }

            //ROOM 112 BLOCK 5 STATUS
            if (QRScanText.equals(r112b5)) {
                if (diffInTimeFourthBlock < 0 && diffInTimeFourthBlock > -50) {
                    String status;
                    status = tardy;
                    Map<String, Object> tardy1 = new HashMap<>();
                    tardy1.put("status", status);
                    db.collection("112-5").document(uid2).update(tardy1)
                            .addOnSuccessListener(unused -> Toast.makeText(getApplicationContext(), "Status Updated: Tardy", Toast.LENGTH_SHORT).show());
                } else if (diffInTimeFourthBlock >= 0 && diffInTimeFourthBlock <= 10) {
                    String status;
                    status = present;
                    Map<String, Object> present1 = new HashMap<>();
                    present1.put("status", status);
                    db.collection("112-5").document(uid2).update(present1)
                            .addOnSuccessListener(unused -> Toast.makeText(getApplicationContext(), "Status Updated: Present", Toast.LENGTH_SHORT).show());
                } else if (diffInTimeFourthBlock < -50) {
                    String status;
                    status = absent;
                    Map<String, Object> absent1 = new HashMap<>();
                    absent1.put("status", status);
                    db.collection("112-5").document(uid2).update(absent1);
                } else {
                    Toast.makeText(getApplicationContext(), "Attendance NOT Updated. This class hasn't started yet, please wait for the class to begin to scan the QR Code", Toast.LENGTH_SHORT).show();
                }
            }

            //ROOM 111 BLOCK 6 STATUS
            if (QRScanText.equals(r111b6)) {
                if (diffInTimeFifthBlock < 0 && diffInTimeFifthBlock > -50) {
                    String status;
                    status = tardy;
                    Map<String, Object> tardy1 = new HashMap<>();
                    tardy1.put("status", status);
                    db.collection("111-6").document(uid2).update(tardy1)
                            .addOnSuccessListener(unused -> Toast.makeText(getApplicationContext(), "Status Updated: Tardy", Toast.LENGTH_SHORT).show());
                } else if (diffInTimeFifthBlock >= 0 && diffInTimeFifthBlock <= 10) {
                    String status;
                    status = present;
                    Map<String, Object> present1 = new HashMap<>();
                    present1.put("status", status);
                    db.collection("111-6").document(uid2).update(present1)
                            .addOnSuccessListener(unused -> Toast.makeText(getApplicationContext(), "Status Updated: Present", Toast.LENGTH_SHORT).show());
                } else if (diffInTimeFifthBlock < -50) {
                    String status;
                    status = absent;
                    Map<String, Object> absent1 = new HashMap<>();
                    absent1.put("status", status);
                    db.collection("111-6").document(uid2).update(absent1);
                } else {
                    Toast.makeText(getApplicationContext(), "Attendance NOT Updated. This class hasn't started yet, please wait for the class to begin to scan the QR Code", Toast.LENGTH_SHORT).show();
                }
            }

            //ROOM 112 BLOCK 6 STATUS
            if (QRScanText.equals(r112b6)) {
                if (diffInTimeFifthBlock < 0 && diffInTimeFifthBlock > -50) {
                    String status;
                    status = tardy;
                    Map<String, Object> tardy1 = new HashMap<>();
                    tardy1.put("status", status);
                    db.collection("112-6").document(uid2).update(tardy1)
                            .addOnSuccessListener(unused -> Toast.makeText(getApplicationContext(), "Status Updated: Tardy", Toast.LENGTH_SHORT).show());
                } else if (diffInTimeFifthBlock >= 0 && diffInTimeFifthBlock <= 10) {
                    String status;
                    status = present;
                    Map<String, Object> present1 = new HashMap<>();
                    present1.put("status", status);
                    db.collection("112-6").document(uid2).update(present1)
                            .addOnSuccessListener(unused -> Toast.makeText(getApplicationContext(), "Status Updated: Present", Toast.LENGTH_SHORT).show());
                } else if (diffInTimeFifthBlock < -50) {
                    String status;
                    status = absent;
                    Map<String, Object> absent1 = new HashMap<>();
                    absent1.put("status", status);
                    db.collection("112-6").document(uid2).update(absent1);
                } else {
                    Toast.makeText(getApplicationContext(), "Attendance NOT Updated. This class hasn't started yet, please wait for the class to begin to scan the QR Code", Toast.LENGTH_SHORT).show();
                }
            }

            //ROOM 111 BLOCK 7 STATUS
            if (QRScanText.equals(r111b7)) {
                if (diffInTimeSixthBlock < 0 && diffInTimeSixthBlock > -55) {
                    String status;
                    status = tardy;
                    Map<String, Object> tardy1 = new HashMap<>();
                    tardy1.put("status", status);
                    db.collection("111-7").document(uid2).update(tardy1)
                            .addOnSuccessListener(unused -> Toast.makeText(getApplicationContext(), "Status Updated: Tardy", Toast.LENGTH_SHORT).show());
                } else if (diffInTimeSixthBlock >= 0 && diffInTimeSixthBlock <= 10) {
                    String status;
                    status = present;
                    Map<String, Object> present1 = new HashMap<>();
                    present1.put("status", status);
                    db.collection("111-7").document(uid2).update(present1)
                            .addOnSuccessListener(unused -> Toast.makeText(getApplicationContext(), "Status Updated: Present", Toast.LENGTH_SHORT).show());
                } else if (diffInTimeSixthBlock < -55) {
                    String status;
                    status = absent;
                    Map<String, Object> absent1 = new HashMap<>();
                    absent1.put("status", status);
                    db.collection("111-7").document(uid2).update(absent1);
                } else {
                    Toast.makeText(getApplicationContext(), "Attendance NOT Updated. This class hasn't started yet, please wait for the class to begin to scan the QR Code", Toast.LENGTH_SHORT).show();
                }
            }

            //ROOM 112 BLOCK 7 STATUS
            if (QRScanText.equals(r112b7)) {
                if (diffInTimeSixthBlock < 0 && diffInTimeSixthBlock > -55) {
                    String status;
                    status = tardy;
                    Map<String, Object> tardy1 = new HashMap<>();
                    tardy1.put("status", status);
                    db.collection("112-7").document(uid2).update(tardy1)
                            .addOnSuccessListener(unused -> Toast.makeText(getApplicationContext(), "Status Updated: Tardy", Toast.LENGTH_SHORT).show());
                } else if (diffInTimeSixthBlock >= 0 && diffInTimeSixthBlock <= 10) {
                    String status;
                    status = present;
                    Map<String, Object> present1 = new HashMap<>();
                    present1.put("status", status);
                    db.collection("112-7").document(uid2).update(present1)
                            .addOnSuccessListener(unused -> Toast.makeText(getApplicationContext(), "Status Updated: Present", Toast.LENGTH_SHORT).show());
                } else if (diffInTimeSixthBlock < -55) {
                    String status;
                    status = absent;
                    Map<String, Object> absent1 = new HashMap<>();
                    absent1.put("status", status);
                    db.collection("112-7").document(uid2).update(absent1);
                } else {
                    Toast.makeText(getApplicationContext(), "Attendance NOT Updated. This class hasn't started yet, please wait for the class to begin to scan the QR Code", Toast.LENGTH_SHORT).show();
                }
            }

            //IF USER SCANS BLOCK 4 QR CODE, IT RETURNS TOAST
            if (QRScanText.equals(r111b4)) {
                Toast.makeText(getApplicationContext(), "You Do Not Have Block 4 On A Day 1, Please Make Your Way To The Right Class", Toast.LENGTH_LONG).show();
            }
            if (QRScanText.equals(r112b4)) {
                Toast.makeText(getApplicationContext(), "You Do Not Have Block 4 On A Day 1, Please Make Your Way To The Right Class", Toast.LENGTH_LONG).show();
            }

            //IF USER SCANS BLOCK 8 QR CODE, IT RETURNS TOAST
            if (QRScanText.equals(r111b8)) {
                Toast.makeText(getApplicationContext(), "You Do Not Have Block 8 On A Day 1, Please Make Your Way To The Right Class", Toast.LENGTH_LONG).show();
            }
            if (QRScanText.equals(r112b8)) {
                Toast.makeText(getApplicationContext(), "You Do Not Have Block 8 On A Day 1, Please Make Your Way To The Right Class", Toast.LENGTH_LONG).show();
            }
        }

        if (day.equals(Day2)) {
            //ROOM 111 BLOCK 2 STATUS
            if (QRScanText.equals(r111b2)) {
                if (diffInTimeFirstBlock < 0 && diffInTimeFirstBlock > -60) {
                    String status;
                    status = tardy;
                    Map<String, Object> tardy1 = new HashMap<>();
                    tardy1.put("status", status);
                    db.collection("111-2").document(uid2).update(tardy1)
                            .addOnSuccessListener(unused -> Toast.makeText(getApplicationContext(), "Status Updated: Tardy", Toast.LENGTH_SHORT).show());
                } else if (diffInTimeFirstBlock >= 0 && diffInTimeFirstBlock <= 35) {
                    String status;
                    status = present;
                    Map<String, Object> present1 = new HashMap<>();
                    present1.put("status", status);
                    db.collection("111-2").document(uid2).update(present1)
                            .addOnSuccessListener(unused -> Toast.makeText(getApplicationContext(), "Status Updated: Present", Toast.LENGTH_SHORT).show());
                } else if (diffInTimeFirstBlock < -60) {
                    String status;
                    status = absent;
                    Map<String, Object> absent1 = new HashMap<>();
                    absent1.put("status", status);
                    db.collection("111-2").document(uid2).update(absent1);
                } else {
                    Toast.makeText(getApplicationContext(), "Attendance NOT Updated. This class hasn't started yet, please wait for the class to begin to scan the QR Code", Toast.LENGTH_SHORT).show();
                }
            }

            //ROOM 112 BLOCK 2 STATUS
            if (QRScanText.equals(r112b2)) {
                if (diffInTimeFirstBlock < 0 && diffInTimeFirstBlock > -60) {
                    String status;
                    status = tardy;
                    Map<String, Object> tardy1 = new HashMap<>();
                    tardy1.put("status", status);
                    db.collection("112-2").document(uid2).update(tardy1)
                            .addOnSuccessListener(unused -> Toast.makeText(getApplicationContext(), "Status Updated: Tardy", Toast.LENGTH_SHORT).show());
                } else if (diffInTimeFirstBlock >= 0 && diffInTimeFirstBlock <= 35) {
                    String status;
                    status = present;
                    Map<String, Object> present1 = new HashMap<>();
                    present1.put("status", status);
                    db.collection("112-2").document(uid2).update(present1)
                            .addOnSuccessListener(unused -> Toast.makeText(getApplicationContext(), "Status Updated: Present", Toast.LENGTH_SHORT).show());
                } else if (diffInTimeFirstBlock < -60) {
                    String status;
                    status = absent;
                    Map<String, Object> absent1 = new HashMap<>();
                    absent1.put("status", status);
                    db.collection("112-2").document(uid2).update(absent1);
                } else {
                    Toast.makeText(getApplicationContext(), "Attendance NOT Updated. This class hasn't started yet, please wait for the class to begin to scan the QR Code", Toast.LENGTH_SHORT).show();
                }
            }

            //ROOM 111 BLOCK 3 STATUS
            if (QRScanText.equals(r111b3)) {
                if (diffInTimeSecondBlock < 0 && diffInTimeSecondBlock > -50) {
                    String status;
                    status = tardy;
                    Map<String, Object> tardy1 = new HashMap<>();
                    tardy1.put("status", status);
                    db.collection("111-3").document(uid2).update(tardy1)
                            .addOnSuccessListener(unused -> Toast.makeText(getApplicationContext(), "Status Updated: Tardy", Toast.LENGTH_SHORT).show());
                } else if (diffInTimeSecondBlock >= 0 && diffInTimeSecondBlock <= 10) {
                    String status;
                    status = present;
                    Map<String, Object> present1 = new HashMap<>();
                    present1.put("status", status);
                    db.collection("111-3").document(uid2).update(present1)
                            .addOnSuccessListener(unused -> Toast.makeText(getApplicationContext(), "Status Updated: Present", Toast.LENGTH_SHORT).show());
                } else if (diffInTimeSecondBlock < -50) {
                    String status;
                    status = absent;
                    Map<String, Object> absent1 = new HashMap<>();
                    absent1.put("status", status);
                    db.collection("111-3").document(uid2).update(absent1);
                } else {
                    Toast.makeText(getApplicationContext(), "Attendance NOT Updated. This class hasn't started yet, please wait for the class to begin to scan the QR Code", Toast.LENGTH_SHORT).show();
                }
            }

            //ROOM 112 BLOCK 3 STATUS
            if (QRScanText.equals(r112b3)) {
                if (diffInTimeSecondBlock < 0 && diffInTimeSecondBlock > -50) {
                    String status;
                    status = tardy;
                    Map<String, Object> tardy1 = new HashMap<>();
                    tardy1.put("status", status);
                    db.collection("112-3").document(uid2).update(tardy1)
                            .addOnSuccessListener(unused -> Toast.makeText(getApplicationContext(), "Status Updated: Tardy", Toast.LENGTH_SHORT).show());
                } else if (diffInTimeSecondBlock >= 0 && diffInTimeSecondBlock <= 10) {
                    String status;
                    status = present;
                    Map<String, Object> present1 = new HashMap<>();
                    present1.put("status", status);
                    db.collection("112-3").document(uid2).update(present1)
                            .addOnSuccessListener(unused -> Toast.makeText(getApplicationContext(), "Status Updated: Present", Toast.LENGTH_SHORT).show());
                } else if (diffInTimeSecondBlock < -50) {
                    String status;
                    status = absent;
                    Map<String, Object> absent1 = new HashMap<>();
                    absent1.put("status", status);
                    db.collection("112-3").document(uid2).update(absent1);
                } else {
                    Toast.makeText(getApplicationContext(), "Attendance NOT Updated. This class hasn't started yet, please wait for the class to begin to scan the QR Code", Toast.LENGTH_SHORT).show();
                }
            }

            //ROOM 111 BLOCK 4 STATUS
            if (QRScanText.equals(r111b4)) {
                if (diffInTimeThirdBlock < 0 && diffInTimeThirdBlock > -50) {
                    String status;
                    status = tardy;
                    Map<String, Object> tardy1 = new HashMap<>();
                    tardy1.put("status", status);
                    db.collection("111-4").document(uid2).update(tardy1)
                            .addOnSuccessListener(unused -> Toast.makeText(getApplicationContext(), "Status Updated: Tardy", Toast.LENGTH_SHORT).show());
                } else if (diffInTimeThirdBlock >= 0 && diffInTimeThirdBlock <= 10) {
                    String status;
                    status = present;
                    Map<String, Object> present1 = new HashMap<>();
                    present1.put("status", status);
                    db.collection("111-4").document(uid2).update(present1)
                            .addOnSuccessListener(unused -> Toast.makeText(getApplicationContext(), "Status Updated: Present", Toast.LENGTH_SHORT).show());
                } else if (diffInTimeThirdBlock < -50) {
                    String status;
                    status = absent;
                    Map<String, Object> absent1 = new HashMap<>();
                    absent1.put("status", status);
                    db.collection("111-4").document(uid2).update(absent1);
                } else {
                    Toast.makeText(getApplicationContext(), "Attendance NOT Updated. This class hasn't started yet, please wait for the class to begin to scan the QR Code", Toast.LENGTH_SHORT).show();
                }
            }

            //ROOM 112 BLOCK 4 STATUS
            if (QRScanText.equals(r112b4)) {
                if (diffInTimeThirdBlock < 0 && diffInTimeThirdBlock > -50) {
                    String status;
                    status = tardy;
                    Map<String, Object> tardy1 = new HashMap<>();
                    tardy1.put("status", status);
                    db.collection("112-4").document(uid2).update(tardy1)
                            .addOnSuccessListener(unused -> Toast.makeText(getApplicationContext(), "Status Updated: Tardy", Toast.LENGTH_SHORT).show());
                } else if (diffInTimeThirdBlock >= 0 && diffInTimeThirdBlock <= 10) {
                    String status;
                    status = present;
                    Map<String, Object> present1 = new HashMap<>();
                    present1.put("status", status);
                    db.collection("112-4").document(uid2).update(present1)
                            .addOnSuccessListener(unused -> Toast.makeText(getApplicationContext(), "Status Updated: Present", Toast.LENGTH_SHORT).show());
                } else if (diffInTimeThirdBlock < -50) {
                    String status;
                    status = absent;
                    Map<String, Object> absent1 = new HashMap<>();
                    absent1.put("status", status);
                    db.collection("112-4").document(uid2).update(absent1);
                } else {
                    Toast.makeText(getApplicationContext(), "Attendance NOT Updated. This class hasn't started yet, please wait for the class to begin to scan the QR Code", Toast.LENGTH_SHORT).show();
                }
            }

            //ROOM 111 BLOCK 6 STATUS
            if (QRScanText.equals(r111b6)) {
                if (diffInTimeFourthBlock < 0 && diffInTimeFourthBlock > -50) {
                    String status;
                    status = tardy;
                    Map<String, Object> tardy1 = new HashMap<>();
                    tardy1.put("status", status);
                    db.collection("111-6").document(uid2).update(tardy1)
                            .addOnSuccessListener(unused -> Toast.makeText(getApplicationContext(), "Status Updated: Tardy", Toast.LENGTH_SHORT).show());
                } else if (diffInTimeFourthBlock >= 0 && diffInTimeFourthBlock <= 10) {
                    String status;
                    status = present;
                    Map<String, Object> present1 = new HashMap<>();
                    present1.put("status", status);
                    db.collection("111-6").document(uid2).update(present1)
                            .addOnSuccessListener(unused -> Toast.makeText(getApplicationContext(), "Status Updated: Present", Toast.LENGTH_SHORT).show());
                } else if (diffInTimeFourthBlock < -50) {
                    String status;
                    status = absent;
                    Map<String, Object> absent1 = new HashMap<>();
                    absent1.put("status", status);
                    db.collection("111-6").document(uid2).update(absent1);
                } else {
                    Toast.makeText(getApplicationContext(), "Attendance NOT Updated. This class hasn't started yet, please wait for the class to begin to scan the QR Code", Toast.LENGTH_SHORT).show();
                }
            }

            //ROOM 112 BLOCK 6 STATUS
            if (QRScanText.equals(r112b6)) {
                if (diffInTimeFourthBlock < 0 && diffInTimeFourthBlock > -50) {
                    String status;
                    status = tardy;
                    Map<String, Object> tardy1 = new HashMap<>();
                    tardy1.put("status", status);
                    db.collection("112-6").document(uid2).update(tardy1)
                            .addOnSuccessListener(unused -> Toast.makeText(getApplicationContext(), "Status Updated: Tardy", Toast.LENGTH_SHORT).show());
                } else if (diffInTimeFourthBlock >= 0 && diffInTimeFourthBlock <= 10) {
                    String status;
                    status = present;
                    Map<String, Object> present1 = new HashMap<>();
                    present1.put("status", status);
                    db.collection("112-6").document(uid2).update(present1)
                            .addOnSuccessListener(unused -> Toast.makeText(getApplicationContext(), "Status Updated: Present", Toast.LENGTH_SHORT).show());
                } else if (diffInTimeFourthBlock < -50) {
                    String status;
                    status = absent;
                    Map<String, Object> absent1 = new HashMap<>();
                    absent1.put("status", status);
                    db.collection("112-6").document(uid2).update(absent1);
                } else {
                    Toast.makeText(getApplicationContext(), "Attendance NOT Updated. This class hasn't started yet, please wait for the class to begin to scan the QR Code", Toast.LENGTH_SHORT).show();
                }
            }

            //ROOM 111 BLOCK 7 STATUS
            if (QRScanText.equals(r111b7)) {
                if (diffInTimeFifthBlock < 0 && diffInTimeFifthBlock > -50) {
                    String status;
                    status = tardy;
                    Map<String, Object> tardy1 = new HashMap<>();
                    tardy1.put("status", status);
                    db.collection("111-7").document(uid2).update(tardy1)
                            .addOnSuccessListener(unused -> Toast.makeText(getApplicationContext(), "Status Updated: Tardy", Toast.LENGTH_SHORT).show());
                } else if (diffInTimeFifthBlock >= 0 && diffInTimeFifthBlock <= 10) {
                    String status;
                    status = present;
                    Map<String, Object> present1 = new HashMap<>();
                    present1.put("status", status);
                    db.collection("111-7").document(uid2).update(present1)
                            .addOnSuccessListener(unused -> Toast.makeText(getApplicationContext(), "Status Updated: Present", Toast.LENGTH_SHORT).show());
                } else if (diffInTimeFifthBlock < -50) {
                    String status;
                    status = absent;
                    Map<String, Object> absent1 = new HashMap<>();
                    absent1.put("status", status);
                    db.collection("111-7").document(uid2).update(absent1);
                } else {
                    Toast.makeText(getApplicationContext(), "Attendance NOT Updated. This class hasn't started yet, please wait for the class to begin to scan the QR Code", Toast.LENGTH_SHORT).show();
                }
            }

            //ROOM 112 BLOCK 7
            if (QRScanText.equals(r112b7)) {
                if (diffInTimeFifthBlock < 0 && diffInTimeFifthBlock > -50) {
                    String status;
                    status = tardy;
                    Map<String, Object> tardy1 = new HashMap<>();
                    tardy1.put("status", status);
                    db.collection("112-7").document(uid2).update(tardy1)
                            .addOnSuccessListener(unused -> Toast.makeText(getApplicationContext(), "Status Updated: Tardy", Toast.LENGTH_SHORT).show());
                } else if (diffInTimeFifthBlock >= 0 && diffInTimeFifthBlock <= 10) {
                    String status;
                    status = present;
                    Map<String, Object> present1 = new HashMap<>();
                    present1.put("status", status);
                    db.collection("112-7").document(uid2).update(present1)
                            .addOnSuccessListener(unused -> Toast.makeText(getApplicationContext(), "Status Updated: Present", Toast.LENGTH_SHORT).show());
                } else if (diffInTimeFifthBlock < -50) {
                    String status;
                    status = absent;
                    Map<String, Object> absent1 = new HashMap<>();
                    absent1.put("status", status);
                    db.collection("112-7").document(uid2).update(absent1);
                } else {
                    Toast.makeText(getApplicationContext(), "Attendance NOT Updated. This class hasn't started yet, please wait for the class to begin to scan the QR Code", Toast.LENGTH_SHORT).show();
                }
            }

            //ROOM 111 BLOCK 8 STATUS
            if (QRScanText.equals(r111b8)) {
                if (diffInTimeSixthBlock < 0 && diffInTimeSixthBlock > -55) {
                    String status;
                    status = tardy;
                    Map<String, Object> tardy1 = new HashMap<>();
                    tardy1.put("status", status);
                    db.collection("111-8").document(uid2).update(tardy1)
                            .addOnSuccessListener(unused -> Toast.makeText(getApplicationContext(), "Status Updated: Tardy", Toast.LENGTH_SHORT).show());
                } else if (diffInTimeSixthBlock >= 0 && diffInTimeSixthBlock <= 10) {
                    String status;
                    status = present;
                    Map<String, Object> present1 = new HashMap<>();
                    present1.put("status", status);
                    db.collection("111-8").document(uid2).update(present1)
                            .addOnSuccessListener(unused -> Toast.makeText(getApplicationContext(), "Status Updated: Present", Toast.LENGTH_SHORT).show());
                } else if (diffInTimeSixthBlock < -55) {
                    String status;
                    status = absent;
                    Map<String, Object> absent1 = new HashMap<>();
                    absent1.put("status", status);
                    db.collection("111-8").document(uid2).update(absent1);
                } else {
                    Toast.makeText(getApplicationContext(), "Attendance NOT Updated. This class hasn't started yet, please wait for the class to begin to scan the QR Code", Toast.LENGTH_SHORT).show();
                }
            }

            //ROOM 112 BLOCK 8 STATUS
            if (QRScanText.equals(r112b8)) {
                if (diffInTimeSixthBlock < 0 && diffInTimeSixthBlock > -55) {
                    String status;
                    status = tardy;
                    Map<String, Object> tardy1 = new HashMap<>();
                    tardy1.put("status", status);
                    db.collection("112-8").document(uid2).update(tardy1)
                            .addOnSuccessListener(unused -> Toast.makeText(getApplicationContext(), "Status Updated: Tardy", Toast.LENGTH_SHORT).show());
                } else if (diffInTimeSixthBlock >= 0 && diffInTimeSixthBlock <= 10) {
                    String status;
                    status = present;
                    Map<String, Object> present1 = new HashMap<>();
                    present1.put("status", status);
                    db.collection("112-8").document(uid2).update(present1)
                            .addOnSuccessListener(unused -> Toast.makeText(getApplicationContext(), "Status Updated: Present", Toast.LENGTH_SHORT).show());
                } else if (diffInTimeSixthBlock < -55) {
                    String status;
                    status = absent;
                    Map<String, Object> absent1 = new HashMap<>();
                    absent1.put("status", status);
                    db.collection("112-8").document(uid2).update(absent1);
                } else {
                    Toast.makeText(getApplicationContext(), "Attendance NOT Updated. This class hasn't started yet, please wait for the class to begin to scan the QR Code", Toast.LENGTH_SHORT).show();
                }
            }

            //IF USER SCANS BLOCK 4 QR CODE, IT RETURNS TOAST
            if (QRScanText.equals(r111b1)) {
                Toast.makeText(getApplicationContext(), "You Do Not Have Block 1 On A Day 2, Please Make Your Way To The Right Class", Toast.LENGTH_LONG).show();
            }
            if (QRScanText.equals(r112b1)) {
                Toast.makeText(getApplicationContext(), "You Do Not Have Block 1 On A Day 2, Please Make Your Way To The Right Class", Toast.LENGTH_LONG).show();
            }

            //IF USER SCANS BLOCK 8 QR CODE, IT RETURNS TOAST
            if (QRScanText.equals(r111b5)) {
                Toast.makeText(getApplicationContext(), "You Do Not Have Block 5 On A Day 2, Please Make Your Way To The Right Class", Toast.LENGTH_LONG).show();
            }
            if (QRScanText.equals(r112b5)) {
                Toast.makeText(getApplicationContext(), "You Do Not Have Block 5 On A Day 2, Please Make Your Way To The Right Class", Toast.LENGTH_LONG).show();
            }
        }

        if (day.equals(Day3)) {
            //ROOM 111 BLOCK 3 STATUS
            if (QRScanText.equals(r111b3)) {
                if (diffInTimeFirstBlock < 0 && diffInTimeFirstBlock > -60) {
                    String status;
                    status = tardy;
                    Map<String, Object> tardy1 = new HashMap<>();
                    tardy1.put("status", status);
                    db.collection("111-3").document(uid2).update(tardy1)
                            .addOnSuccessListener(unused -> Toast.makeText(getApplicationContext(), "Status Updated: Tardy", Toast.LENGTH_SHORT).show());
                } else if (diffInTimeFirstBlock >= 0 && diffInTimeFirstBlock <= 35) {
                    String status;
                    status = present;
                    Map<String, Object> present1 = new HashMap<>();
                    present1.put("status", status);
                    db.collection("111-3").document(uid2).update(present1)
                            .addOnSuccessListener(unused -> Toast.makeText(getApplicationContext(), "Status Updated: Present", Toast.LENGTH_SHORT).show());
                } else if (diffInTimeFirstBlock < -60) {
                    String status;
                    status = absent;
                    Map<String, Object> absent1 = new HashMap<>();
                    absent1.put("status", status);
                    db.collection("111-3").document(uid2).update(absent1);
                } else {
                    Toast.makeText(getApplicationContext(), "Attendance NOT Updated. This class hasn't started yet, please wait for the class to begin to scan the QR Code", Toast.LENGTH_SHORT).show();
                }
            }

            //ROOM 112 BLOCK 3 STATUS
            if (QRScanText.equals(r112b3)) {
                if (diffInTimeFirstBlock < 0 && diffInTimeFirstBlock > -60) {
                    String status;
                    status = tardy;
                    Map<String, Object> tardy1 = new HashMap<>();
                    tardy1.put("status", status);
                    db.collection("112-3").document(uid2).update(tardy1)
                            .addOnSuccessListener(unused -> Toast.makeText(getApplicationContext(), "Status Updated: Tardy", Toast.LENGTH_SHORT).show());
                } else if (diffInTimeFirstBlock >= 0 && diffInTimeFirstBlock <= 35) {
                    String status;
                    status = present;
                    Map<String, Object> present1 = new HashMap<>();
                    present1.put("status", status);
                    db.collection("112-3").document(uid2).update(present1)
                            .addOnSuccessListener(unused -> Toast.makeText(getApplicationContext(), "Status Updated: Present", Toast.LENGTH_SHORT).show());
                } else if (diffInTimeFirstBlock < -60) {
                    String status;
                    status = absent;
                    Map<String, Object> absent1 = new HashMap<>();
                    absent1.put("status", status);
                    db.collection("112-3").document(uid2).update(absent1);
                } else {
                    Toast.makeText(getApplicationContext(), "Attendance NOT Updated. This class hasn't started yet, please wait for the class to begin to scan the QR Code", Toast.LENGTH_SHORT).show();
                }
            }

            //ROOM 111 BLOCK 4 STATUS
            if (QRScanText.equals(r111b4)) {
                if (diffInTimeSecondBlock < 0 && diffInTimeSecondBlock > -50) {
                    String status;
                    status = tardy;
                    Map<String, Object> tardy1 = new HashMap<>();
                    tardy1.put("status", status);
                    db.collection("111-4").document(uid2).update(tardy1)
                            .addOnSuccessListener(unused -> Toast.makeText(getApplicationContext(), "Status Updated: Tardy", Toast.LENGTH_SHORT).show());
                } else if (diffInTimeSecondBlock >= 0 && diffInTimeFirstBlock <= 10) {
                    String status;
                    status = present;
                    Map<String, Object> present1 = new HashMap<>();
                    present1.put("status", status);
                    db.collection("111-4").document(uid2).update(present1)
                            .addOnSuccessListener(unused -> Toast.makeText(getApplicationContext(), "Status Updated: Present", Toast.LENGTH_SHORT).show());
                } else if (diffInTimeSecondBlock < -50) {
                    String status;
                    status = absent;
                    Map<String, Object> absent1 = new HashMap<>();
                    absent1.put("status", status);
                    db.collection("111-4").document(uid2).update(absent1);
                } else {
                    Toast.makeText(getApplicationContext(), "Attendance NOT Updated. This class hasn't started yet, please wait for the class to begin to scan the QR Code", Toast.LENGTH_SHORT).show();
                }
            }

            //ROOM 112 BLOCK 4 STATUS
            if (QRScanText.equals(r112b4)) {
                if (diffInTimeSecondBlock < 0 && diffInTimeSecondBlock > -50) {
                    String status;
                    status = tardy;
                    Map<String, Object> tardy1 = new HashMap<>();
                    tardy1.put("status", status);
                    db.collection("112-4").document(uid2).update(tardy1)
                            .addOnSuccessListener(unused -> Toast.makeText(getApplicationContext(), "Status Updated: Tardy", Toast.LENGTH_SHORT).show());
                } else if (diffInTimeSecondBlock >= 0 && diffInTimeFirstBlock <= 10) {
                    String status;
                    status = present;
                    Map<String, Object> present1 = new HashMap<>();
                    present1.put("status", status);
                    db.collection("112-4").document(uid2).update(present1)
                            .addOnSuccessListener(unused -> Toast.makeText(getApplicationContext(), "Status Updated: Present", Toast.LENGTH_SHORT).show());
                } else if (diffInTimeSecondBlock < -50) {
                    String status;
                    status = absent;
                    Map<String, Object> absent1 = new HashMap<>();
                    absent1.put("status", status);
                    db.collection("112-4").document(uid2).update(absent1);
                } else {
                    Toast.makeText(getApplicationContext(), "Attendance NOT Updated. This class hasn't started yet, please wait for the class to begin to scan the QR Code", Toast.LENGTH_SHORT).show();
                }
            }

            //ROOM 111 BLOCK 1 STATUS
            if (QRScanText.equals(r111b1)) {
                if (diffInTimeThirdBlock < 0 && diffInTimeThirdBlock > -50) {
                    String status;
                    status = tardy;
                    Map<String, Object> tardy1 = new HashMap<>();
                    tardy1.put("status", status);
                    db.collection("111-1").document(uid2).update(tardy1)
                            .addOnSuccessListener(unused -> Toast.makeText(getApplicationContext(), "Status Updated: Tardy", Toast.LENGTH_SHORT).show());
                } else if (diffInTimeThirdBlock >= 0 && diffInTimeThirdBlock <= 10) {
                    String status;
                    status = present;
                    Map<String, Object> present1 = new HashMap<>();
                    present1.put("status", status);
                    db.collection("111-1").document(uid2).update(present1)
                            .addOnSuccessListener(unused -> Toast.makeText(getApplicationContext(), "Status Updated: Present", Toast.LENGTH_SHORT).show());
                } else if (diffInTimeThirdBlock < -50) {
                    String status;
                    status = absent;
                    Map<String, Object> absent1 = new HashMap<>();
                    absent1.put("status", status);
                    db.collection("111-1").document(uid2).update(absent1);
                } else {
                    Toast.makeText(getApplicationContext(), "Attendance NOT Updated. This class hasn't started yet, please wait for the class to begin to scan the QR Code", Toast.LENGTH_SHORT).show();
                }
            }

            //ROOM 112 BLOCK 1 STATUS
            if (QRScanText.equals(r112b1)) {
                if (diffInTimeThirdBlock < 0 && diffInTimeThirdBlock > -50) {
                    String status;
                    status = tardy;
                    Map<String, Object> tardy1 = new HashMap<>();
                    tardy1.put("status", status);
                    db.collection("112-1").document(uid2).update(tardy1)
                            .addOnSuccessListener(unused -> Toast.makeText(getApplicationContext(), "Status Updated: Tardy", Toast.LENGTH_SHORT).show());
                } else if (diffInTimeThirdBlock >= 0 && diffInTimeThirdBlock <= 10) {
                    String status;
                    status = present;
                    Map<String, Object> present1 = new HashMap<>();
                    present1.put("status", status);
                    db.collection("112-1").document(uid2).update(present1)
                            .addOnSuccessListener(unused -> Toast.makeText(getApplicationContext(), "Status Updated: Present", Toast.LENGTH_SHORT).show());
                } else if (diffInTimeThirdBlock < -50) {
                    String status;
                    status = absent;
                    Map<String, Object> absent1 = new HashMap<>();
                    absent1.put("status", status);
                    db.collection("112-1").document(uid2).update(absent1);
                } else {
                    Toast.makeText(getApplicationContext(), "Attendance NOT Updated. This class hasn't started yet, please wait for the class to begin to scan the QR Code", Toast.LENGTH_SHORT).show();
                }
            }

            //ROOM 111 BLOCK 7 STATUS
            if (QRScanText.equals(r111b7)) {
                if (diffInTimeFourthBlock < 0 && diffInTimeFourthBlock > -50) {
                    String status;
                    status = tardy;
                    Map<String, Object> tardy1 = new HashMap<>();
                    tardy1.put("status", status);
                    db.collection("111-7").document(uid2).update(tardy1)
                            .addOnSuccessListener(unused -> Toast.makeText(getApplicationContext(), "Status Updated: Tardy", Toast.LENGTH_SHORT).show());
                } else if (diffInTimeFourthBlock >= 0 && diffInTimeFourthBlock <= 10) {
                    String status;
                    status = present;
                    Map<String, Object> present1 = new HashMap<>();
                    present1.put("status", status);
                    db.collection("111-7").document(uid2).update(present1)
                            .addOnSuccessListener(unused -> Toast.makeText(getApplicationContext(), "Status Updated: Present", Toast.LENGTH_SHORT).show());
                } else if (diffInTimeFourthBlock < -50) {
                    String status;
                    status = absent;
                    Map<String, Object> absent1 = new HashMap<>();
                    absent1.put("status", status);
                    db.collection("111-7").document(uid2).update(absent1);
                } else {
                    Toast.makeText(getApplicationContext(), "Attendance NOT Updated. This class hasn't started yet, please wait for the class to begin to scan the QR Code", Toast.LENGTH_SHORT).show();
                }
            }

            //ROOM 112 BLOCK 7 STATUS
            if (QRScanText.equals(r112b7)) {
                if (diffInTimeFourthBlock < 0 && diffInTimeFourthBlock > -50) {
                    String status;
                    status = tardy;
                    Map<String, Object> tardy1 = new HashMap<>();
                    tardy1.put("status", status);
                    db.collection("112-7").document(uid2).update(tardy1)
                            .addOnSuccessListener(unused -> Toast.makeText(getApplicationContext(), "Status Updated: Tardy", Toast.LENGTH_SHORT).show());
                } else if (diffInTimeFourthBlock >= 0 && diffInTimeFourthBlock <= 10) {
                    String status;
                    status = present;
                    Map<String, Object> present1 = new HashMap<>();
                    present1.put("status", status);
                    db.collection("112-7").document(uid2).update(present1)
                            .addOnSuccessListener(unused -> Toast.makeText(getApplicationContext(), "Status Updated: Present", Toast.LENGTH_SHORT).show());
                } else if (diffInTimeFourthBlock < -50) {
                    String status;
                    status = absent;
                    Map<String, Object> absent1 = new HashMap<>();
                    absent1.put("status", status);
                    db.collection("112-7").document(uid2).update(absent1);
                } else {
                    Toast.makeText(getApplicationContext(), "Attendance NOT Updated. This class hasn't started yet, please wait for the class to begin to scan the QR Code", Toast.LENGTH_SHORT).show();
                }
            }

            //ROOM 111 BLOCK 8 STATUS
            if (QRScanText.equals(r111b8)) {
                if (diffInTimeFifthBlock < 0 && diffInTimeFifthBlock > -50) {
                    String status;
                    status = tardy;
                    Map<String, Object> tardy1 = new HashMap<>();
                    tardy1.put("status", status);
                    db.collection("111-8").document(uid2).update(tardy1)
                            .addOnSuccessListener(unused -> Toast.makeText(getApplicationContext(), "Status Updated: Tardy", Toast.LENGTH_SHORT).show());
                } else if (diffInTimeFifthBlock >= 0 && diffInTimeFifthBlock <= 10) {
                    String status;
                    status = present;
                    Map<String, Object> present1 = new HashMap<>();
                    present1.put("status", status);
                    db.collection("111-8").document(uid2).update(present1)
                            .addOnSuccessListener(unused -> Toast.makeText(getApplicationContext(), "Status Updated: Present", Toast.LENGTH_SHORT).show());
                } else if (diffInTimeFifthBlock < -50) {
                    String status;
                    status = absent;
                    Map<String, Object> absent1 = new HashMap<>();
                    absent1.put("status", status);
                    db.collection("111-8").document(uid2).update(absent1);
                } else {
                    Toast.makeText(getApplicationContext(), "Attendance NOT Updated. This class hasn't started yet, please wait for the class to begin to scan the QR Code", Toast.LENGTH_SHORT).show();
                }
            }

            //ROOM 112 BLOCK 8 STATUS
            if (QRScanText.equals(r112b8)) {
                if (diffInTimeFifthBlock < 0 && diffInTimeFifthBlock > -50) {
                    String status;
                    status = tardy;
                    Map<String, Object> tardy1 = new HashMap<>();
                    tardy1.put("status", status);
                    db.collection("112-8").document(uid2).update(tardy1)
                            .addOnSuccessListener(unused -> Toast.makeText(getApplicationContext(), "Status Updated: Tardy", Toast.LENGTH_SHORT).show());
                } else if (diffInTimeFifthBlock >= 0 && diffInTimeFifthBlock <= 10) {
                    String status;
                    status = present;
                    Map<String, Object> present1 = new HashMap<>();
                    present1.put("status", status);
                    db.collection("112-8").document(uid2).update(present1)
                            .addOnSuccessListener(unused -> Toast.makeText(getApplicationContext(), "Status Updated: Present", Toast.LENGTH_SHORT).show());
                } else if (diffInTimeFifthBlock < -50) {
                    String status;
                    status = absent;
                    Map<String, Object> absent1 = new HashMap<>();
                    absent1.put("status", status);
                    db.collection("112-8").document(uid2).update(absent1);
                } else {
                    Toast.makeText(getApplicationContext(), "Attendance NOT Updated. This class hasn't started yet, please wait for the class to begin to scan the QR Code", Toast.LENGTH_SHORT).show();
                }
            }

            //ROOM 111 BLOCK 5 STATUS
            if (QRScanText.equals(r111b5)) {
                if (diffInTimeSixthBlock < 0 && diffInTimeSixthBlock > -55) {
                    String status;
                    status = tardy;
                    Map<String, Object> tardy1 = new HashMap<>();
                    tardy1.put("status", status);
                    db.collection("111-5").document(uid2).update(tardy1)
                            .addOnSuccessListener(unused -> Toast.makeText(getApplicationContext(), "Status Updated: Tardy", Toast.LENGTH_SHORT).show());
                } else if (diffInTimeSixthBlock >= 0 && diffInTimeSixthBlock <= 10) {
                    String status;
                    status = present;
                    Map<String, Object> present1 = new HashMap<>();
                    present1.put("status", status);
                    db.collection("111-5").document(uid2).update(present1)
                            .addOnSuccessListener(unused -> Toast.makeText(getApplicationContext(), "Status Updated: Present", Toast.LENGTH_SHORT).show());
                } else if (diffInTimeSixthBlock < -55) {
                    String status;
                    status = absent;
                    Map<String, Object> absent1 = new HashMap<>();
                    absent1.put("status", status);
                    db.collection("111-5").document(uid2).update(absent1);
                } else {
                    Toast.makeText(getApplicationContext(), "Attendance NOT Updated. This class hasn't started yet, please wait for the class to begin to scan the QR Code", Toast.LENGTH_SHORT).show();
                }
            }

            //ROOM 112 BLOCK 5 STATUS
            if (QRScanText.equals(r112b5)) {
                if (diffInTimeSixthBlock < 0 && diffInTimeSixthBlock > -55) {
                    String status;
                    status = tardy;
                    Map<String, Object> tardy1 = new HashMap<>();
                    tardy1.put("status", status);
                    db.collection("112-5").document(uid2).update(tardy1)
                            .addOnSuccessListener(unused -> Toast.makeText(getApplicationContext(), "Status Updated: Tardy", Toast.LENGTH_SHORT).show());
                } else if (diffInTimeSixthBlock >= 0 && diffInTimeSixthBlock <= 10) {
                    String status;
                    status = present;
                    Map<String, Object> present1 = new HashMap<>();
                    present1.put("status", status);
                    db.collection("112-5").document(uid2).update(present1)
                            .addOnSuccessListener(unused -> Toast.makeText(getApplicationContext(), "Status Updated: Present", Toast.LENGTH_SHORT).show());
                } else if (diffInTimeSixthBlock < -55) {
                    String status;
                    status = absent;
                    Map<String, Object> absent1 = new HashMap<>();
                    absent1.put("status", status);
                    db.collection("112-5").document(uid2).update(absent1);
                } else {
                    Toast.makeText(getApplicationContext(), "Attendance NOT Updated. This class hasn't started yet, please wait for the class to begin to scan the QR Code", Toast.LENGTH_SHORT).show();
                }
            }

            //IF USER SCANS BLOCK 2 QR CODE, IT RETURNS TOAST
            if (QRScanText.equals(r111b2)) {
                Toast.makeText(getApplicationContext(), "You Do Not Have Block 2 On A Day 3, Please Make Your Way To The Right Class", Toast.LENGTH_LONG).show();
            }
            if (QRScanText.equals(r112b2)) {
                Toast.makeText(getApplicationContext(), "You Do Not Have Block 2 On A Day 3, Please Make Your Way To The Right Class", Toast.LENGTH_LONG).show();
            }

            //IF USER SCANS BLOCK 6 QR CODE, IT RETURNS TOAST
            if (QRScanText.equals(r111b6)) {
                Toast.makeText(getApplicationContext(), "You Do Not Have Block 6 On A Day 3, Please Make Your Way To The Right Class", Toast.LENGTH_LONG).show();
            }
            if (QRScanText.equals(r112b6)) {
                Toast.makeText(getApplicationContext(), "You Do Not Have Block 6 On A Day 3, Please Make Your Way To The Right Class", Toast.LENGTH_LONG).show();
            }
        }

        if (day.equals(Day4)) {
            //ROOM 111 BLOCK 4 STATUS
            if (QRScanText.equals(r111b4)) {
                if (diffInTimeFirstBlock < 0 && diffInTimeFirstBlock > -60) {
                    String status;
                    status = tardy;
                    Map<String, Object> tardy1 = new HashMap<>();
                    tardy1.put("status", status);
                    db.collection("111-4").document(uid2).update(tardy1)
                            .addOnSuccessListener(unused -> Toast.makeText(getApplicationContext(), "Status Updated: Tardy", Toast.LENGTH_SHORT).show());
                } else if (diffInTimeFirstBlock >= 0 && diffInTimeFirstBlock <= 35) {
                    String status;
                    status = present;
                    Map<String, Object> present1 = new HashMap<>();
                    present1.put("status", status);
                    db.collection("111-4").document(uid2).update(present1)
                            .addOnSuccessListener(unused -> Toast.makeText(getApplicationContext(), "Status Updated: Present", Toast.LENGTH_SHORT).show());
                } else if (diffInTimeFirstBlock < -60) {
                    String status;
                    status = absent;
                    Map<String, Object> absent1 = new HashMap<>();
                    absent1.put("status", status);
                    db.collection("111-4").document(uid2).update(absent1);
                } else {
                    Toast.makeText(getApplicationContext(), "Attendance NOT Updated. This class hasn't started yet, please wait for the class to begin to scan the QR Code", Toast.LENGTH_SHORT).show();
                }
            }

            //ROOM 112 BLOCK 4 STATUS
            if (QRScanText.equals(r112b4)) {
                if (diffInTimeFirstBlock < 0 && diffInTimeFirstBlock > -60) {
                    String status;
                    status = tardy;
                    Map<String, Object> tardy1 = new HashMap<>();
                    tardy1.put("status", status);
                    db.collection("112-4").document(uid2).update(tardy1)
                            .addOnSuccessListener(unused -> Toast.makeText(getApplicationContext(), "Status Updated: Tardy", Toast.LENGTH_SHORT).show());
                } else if (diffInTimeFirstBlock >= 0 && diffInTimeFirstBlock <= 35) {
                    String status;
                    status = present;
                    Map<String, Object> present1 = new HashMap<>();
                    present1.put("status", status);
                    db.collection("112-4").document(uid2).update(present1)
                            .addOnSuccessListener(unused -> Toast.makeText(getApplicationContext(), "Status Updated: Present", Toast.LENGTH_SHORT).show());
                } else if (diffInTimeFirstBlock < -60) {
                    String status;
                    status = absent;
                    Map<String, Object> absent1 = new HashMap<>();
                    absent1.put("status", status);
                    db.collection("112-4").document(uid2).update(absent1);
                } else {
                    Toast.makeText(getApplicationContext(), "Attendance NOT Updated. This class hasn't started yet, please wait for the class to begin to scan the QR Code", Toast.LENGTH_SHORT).show();
                }
            }

            //ROOM 111 BLOCK 1 STATUS
            if (QRScanText.equals(r111b1)) {
                if (diffInTimeSecondBlock < 0 && diffInTimeSecondBlock > -50) {
                    String status;
                    status = tardy;
                    Map<String, Object> tardy1 = new HashMap<>();
                    tardy1.put("status", status);
                    db.collection("111-1").document(uid2).update(tardy1)
                            .addOnSuccessListener(unused -> Toast.makeText(getApplicationContext(), "Status Updated: Tardy", Toast.LENGTH_SHORT).show());
                } else if (diffInTimeSecondBlock >= 0 && diffInTimeFirstBlock <= 10) {
                    String status;
                    status = present;
                    Map<String, Object> present1 = new HashMap<>();
                    present1.put("status", status);
                    db.collection("111-1").document(uid2).update(present1)
                            .addOnSuccessListener(unused -> Toast.makeText(getApplicationContext(), "Status Updated: Present", Toast.LENGTH_SHORT).show());
                } else if (diffInTimeSecondBlock < -50) {
                    String status;
                    status = absent;
                    Map<String, Object> absent1 = new HashMap<>();
                    absent1.put("status", status);
                    db.collection("111-1").document(uid2).update(absent1);
                } else {
                    Toast.makeText(getApplicationContext(), "Attendance NOT Updated. This class hasn't started yet, please wait for the class to begin to scan the QR Code", Toast.LENGTH_SHORT).show();
                }
            }

            //ROOM 112 BLOCK 1 STATUS
            if (QRScanText.equals(r112b1)) {
                if (diffInTimeSecondBlock < 0 && diffInTimeSecondBlock > -50) {
                    String status;
                    status = tardy;
                    Map<String, Object> tardy1 = new HashMap<>();
                    tardy1.put("status", status);
                    db.collection("112-1").document(uid2).update(tardy1)
                            .addOnSuccessListener(unused -> Toast.makeText(getApplicationContext(), "Status Updated: Tardy", Toast.LENGTH_SHORT).show());
                } else if (diffInTimeSecondBlock >= 0 && diffInTimeFirstBlock <= 10) {
                    String status;
                    status = present;
                    Map<String, Object> present1 = new HashMap<>();
                    present1.put("status", status);
                    db.collection("112-1").document(uid2).update(present1)
                            .addOnSuccessListener(unused -> Toast.makeText(getApplicationContext(), "Status Updated: Present", Toast.LENGTH_SHORT).show());
                } else if (diffInTimeSecondBlock < -50) {
                    String status;
                    status = absent;
                    Map<String, Object> absent1 = new HashMap<>();
                    absent1.put("status", status);
                    db.collection("112-1").document(uid2).update(absent1);
                } else {
                    Toast.makeText(getApplicationContext(), "Attendance NOT Updated. This class hasn't started yet, please wait for the class to begin to scan the QR Code", Toast.LENGTH_SHORT).show();
                }
            }

            //ROOM 111 BLOCK 2 STATUS
            if (QRScanText.equals(r111b2)) {
                if (diffInTimeThirdBlock < 0 && diffInTimeThirdBlock > -50) {
                    String status;
                    status = tardy;
                    Map<String, Object> tardy1 = new HashMap<>();
                    tardy1.put("status", status);
                    db.collection("111-2").document(uid2).update(tardy1)
                            .addOnSuccessListener(unused -> Toast.makeText(getApplicationContext(), "Status Updated: Tardy", Toast.LENGTH_SHORT).show());
                } else if (diffInTimeThirdBlock >= 0 && diffInTimeThirdBlock <= 10) {
                    String status;
                    status = present;
                    Map<String, Object> present1 = new HashMap<>();
                    present1.put("status", status);
                    db.collection("111-2").document(uid2).update(present1)
                            .addOnSuccessListener(unused -> Toast.makeText(getApplicationContext(), "Status Updated: Present", Toast.LENGTH_SHORT).show());
                } else if (diffInTimeThirdBlock < -50) {
                    String status;
                    status = absent;
                    Map<String, Object> absent1 = new HashMap<>();
                    absent1.put("status", status);
                    db.collection("111-2").document(uid2).update(absent1);
                } else {
                    Toast.makeText(getApplicationContext(), "Attendance NOT Updated. This class hasn't started yet, please wait for the class to begin to scan the QR Code", Toast.LENGTH_SHORT).show();
                }
            }

            //ROOM 112 BLOCK 2 STATUS
            if (QRScanText.equals(r112b2)) {
                if (diffInTimeThirdBlock < 0 && diffInTimeThirdBlock > -50) {
                    String status;
                    status = tardy;
                    Map<String, Object> tardy1 = new HashMap<>();
                    tardy1.put("status", status);
                    db.collection("112-2").document(uid2).update(tardy1)
                            .addOnSuccessListener(unused -> Toast.makeText(getApplicationContext(), "Status Updated: Tardy", Toast.LENGTH_SHORT).show());
                } else if (diffInTimeThirdBlock >= 0 && diffInTimeThirdBlock <= 10) {
                    String status;
                    status = present;
                    Map<String, Object> present1 = new HashMap<>();
                    present1.put("status", status);
                    db.collection("112-2").document(uid2).update(present1)
                            .addOnSuccessListener(unused -> Toast.makeText(getApplicationContext(), "Status Updated: Present", Toast.LENGTH_SHORT).show());
                } else if (diffInTimeThirdBlock < -50) {
                    String status;
                    status = absent;
                    Map<String, Object> absent1 = new HashMap<>();
                    absent1.put("status", status);
                    db.collection("112-2").document(uid2).update(absent1);
                } else {
                    Toast.makeText(getApplicationContext(), "Attendance NOT Updated. This class hasn't started yet, please wait for the class to begin to scan the QR Code", Toast.LENGTH_SHORT).show();
                }
            }

            //ROOM 111 BLOCk 8 STATUS
            if (QRScanText.equals(r111b8)) {
                if (diffInTimeFourthBlock < 0 && diffInTimeFourthBlock > -50) {
                    String status;
                    status = tardy;
                    Map<String, Object> tardy1 = new HashMap<>();
                    tardy1.put("status", status);
                    db.collection("111-8").document(uid2).update(tardy1)
                            .addOnSuccessListener(unused -> Toast.makeText(getApplicationContext(), "Status Updated: Tardy", Toast.LENGTH_SHORT).show());
                } else if (diffInTimeFourthBlock >= 0 && diffInTimeFourthBlock <= 10) {
                    String status;
                    status = present;
                    Map<String, Object> present1 = new HashMap<>();
                    present1.put("status", status);
                    db.collection("111-8").document(uid2).update(present1)
                            .addOnSuccessListener(unused -> Toast.makeText(getApplicationContext(), "Status Updated: Present", Toast.LENGTH_SHORT).show());
                } else if (diffInTimeFourthBlock < -50) {
                    String status;
                    status = absent;
                    Map<String, Object> absent1 = new HashMap<>();
                    absent1.put("status", status);
                    db.collection("111-8").document(uid2).update(absent1);
                } else {
                    Toast.makeText(getApplicationContext(), "Attendance NOT Updated. This class hasn't started yet, please wait for the class to begin to scan the QR Code", Toast.LENGTH_SHORT).show();
                }
            }

            //ROOM 112 BLOCK 8 STATUS
            if (QRScanText.equals(r112b8)) {
                if (diffInTimeFourthBlock < 0 && diffInTimeFourthBlock > -50) {
                    String status;
                    status = tardy;
                    Map<String, Object> tardy1 = new HashMap<>();
                    tardy1.put("status", status);
                    db.collection("112-8").document(uid2).update(tardy1)
                            .addOnSuccessListener(unused -> Toast.makeText(getApplicationContext(), "Status Updated: Tardy", Toast.LENGTH_SHORT).show());
                } else if (diffInTimeFourthBlock >= 0 && diffInTimeFourthBlock <= 10) {
                    String status;
                    status = present;
                    Map<String, Object> present1 = new HashMap<>();
                    present1.put("status", status);
                    db.collection("112-8").document(uid2).update(present1)
                            .addOnSuccessListener(unused -> Toast.makeText(getApplicationContext(), "Status Updated: Present", Toast.LENGTH_SHORT).show());
                } else if (diffInTimeFourthBlock < -50) {
                    String status;
                    status = absent;
                    Map<String, Object> absent1 = new HashMap<>();
                    absent1.put("status", status);
                    db.collection("112-8").document(uid2).update(absent1);
                } else {
                    Toast.makeText(getApplicationContext(), "Attendance NOT Updated. This class hasn't started yet, please wait for the class to begin to scan the QR Code", Toast.LENGTH_SHORT).show();
                }
            }

            //ROOM 111 BLOCk 5 STATUS
            if (QRScanText.equals(r111b5)) {
                if (diffInTimeFifthBlock < 0 && diffInTimeFifthBlock > -50) {
                    String status;
                    status = tardy;
                    Map<String, Object> tardy1 = new HashMap<>();
                    tardy1.put("status", status);
                    db.collection("111-5").document(uid2).update(tardy1)
                            .addOnSuccessListener(unused -> Toast.makeText(getApplicationContext(), "Status Updated: Tardy", Toast.LENGTH_SHORT).show());
                } else if (diffInTimeFifthBlock >= 0 && diffInTimeFifthBlock <= 10) {
                    String status;
                    status = present;
                    Map<String, Object> present1 = new HashMap<>();
                    present1.put("status", status);
                    db.collection("111-5").document(uid2).update(present1)
                            .addOnSuccessListener(unused -> Toast.makeText(getApplicationContext(), "Status Updated: Present", Toast.LENGTH_SHORT).show());
                } else if (diffInTimeFifthBlock < -50) {
                    String status;
                    status = absent;
                    Map<String, Object> absent1 = new HashMap<>();
                    absent1.put("status", status);
                    db.collection("111-5").document(uid2).update(absent1);
                } else {
                    Toast.makeText(getApplicationContext(), "Attendance NOT Updated. This class hasn't started yet, please wait for the class to begin to scan the QR Code", Toast.LENGTH_SHORT).show();
                }
            }

            //ROOM 112 BLOCK 5 STATUS
            if (QRScanText.equals(r112b5)) {
                if (diffInTimeFifthBlock < 0 && diffInTimeFifthBlock > -50) {
                    String status;
                    status = tardy;
                    Map<String, Object> tardy1 = new HashMap<>();
                    tardy1.put("status", status);
                    db.collection("112-5").document(uid2).update(tardy1)
                            .addOnSuccessListener(unused -> Toast.makeText(getApplicationContext(), "Status Updated: Tardy", Toast.LENGTH_SHORT).show());
                } else if (diffInTimeFifthBlock >= 0 && diffInTimeFifthBlock <= 10) {
                    String status;
                    status = present;
                    Map<String, Object> present1 = new HashMap<>();
                    present1.put("status", status);
                    db.collection("112-5").document(uid2).update(present1)
                            .addOnSuccessListener(unused -> Toast.makeText(getApplicationContext(), "Status Updated: Present", Toast.LENGTH_SHORT).show());
                } else if (diffInTimeFifthBlock < -50) {
                    String status;
                    status = absent;
                    Map<String, Object> absent1 = new HashMap<>();
                    absent1.put("status", status);
                    db.collection("112-5").document(uid2).update(absent1);
                } else {
                    Toast.makeText(getApplicationContext(), "Attendance NOT Updated. This class hasn't started yet, please wait for the class to begin to scan the QR Code", Toast.LENGTH_SHORT).show();
                }
            }

            //ROOM 111 BLOCK 6 STATUS
            if (QRScanText.equals(r111b6)) {
                if (diffInTimeSixthBlock < 0 && diffInTimeSixthBlock > -55) {
                    String status;
                    status = tardy;
                    Map<String, Object> tardy1 = new HashMap<>();
                    tardy1.put("status", status);
                    db.collection("111-6").document(uid2).update(tardy1)
                            .addOnSuccessListener(unused -> Toast.makeText(getApplicationContext(), "Status Updated: Tardy", Toast.LENGTH_SHORT).show());
                } else if (diffInTimeSixthBlock >= 0 && diffInTimeSixthBlock <= 10) {
                    String status;
                    status = present;
                    Map<String, Object> present1 = new HashMap<>();
                    present1.put("status", status);
                    db.collection("111-6").document(uid2).update(present1)
                            .addOnSuccessListener(unused -> Toast.makeText(getApplicationContext(), "Status Updated: Present", Toast.LENGTH_SHORT).show());
                } else if (diffInTimeSixthBlock < -55) {
                    String status;
                    status = absent;
                    Map<String, Object> absent1 = new HashMap<>();
                    absent1.put("status", status);
                    db.collection("111-6").document(uid2).update(absent1);
                } else {
                    Toast.makeText(getApplicationContext(), "Attendance NOT Updated. This class hasn't started yet, please wait for the class to begin to scan the QR Code", Toast.LENGTH_SHORT).show();
                }
            }

            //ROOM 112 BLOCK 6 STATUS
            if (QRScanText.equals(r112b6)) {
                if (diffInTimeSixthBlock < 0 && diffInTimeSixthBlock > -55) {
                    String status;
                    status = tardy;
                    Map<String, Object> tardy1 = new HashMap<>();
                    tardy1.put("status", status);
                    db.collection("112-6").document(uid2).update(tardy1)
                            .addOnSuccessListener(unused -> Toast.makeText(getApplicationContext(), "Status Updated: Tardy", Toast.LENGTH_SHORT).show());
                } else if (diffInTimeSixthBlock >= 0 && diffInTimeSixthBlock <= 10) {
                    String status;
                    status = present;
                    Map<String, Object> present1 = new HashMap<>();
                    present1.put("status", status);
                    db.collection("112-6").document(uid2).update(present1)
                            .addOnSuccessListener(unused -> Toast.makeText(getApplicationContext(), "Status Updated: Present", Toast.LENGTH_SHORT).show());
                } else if (diffInTimeSixthBlock < -55) {
                    String status;
                    status = absent;
                    Map<String, Object> absent1 = new HashMap<>();
                    absent1.put("status", status);
                    db.collection("112-6").document(uid2).update(absent1);
                } else {
                    Toast.makeText(getApplicationContext(), "Attendance NOT Updated. This class hasn't started yet, please wait for the class to begin to scan the QR Code", Toast.LENGTH_SHORT).show();
                }
            }

            //IF USER SCANS BLOCK 3 QR CODE, IT RETURNS TOAST
            if (QRScanText.equals(r111b3)) {
                Toast.makeText(getApplicationContext(), "You Do Not Have Block 3 On A Day 4, Please Make Your Way To The Right Class", Toast.LENGTH_LONG).show();
            }
            if (QRScanText.equals(r112b3)) {
                Toast.makeText(getApplicationContext(), "You Do Not Have Block 3 On A Day 4, Please Make Your Way To The Right Class", Toast.LENGTH_LONG).show();
            }

            //IF USER SCANS BLOCK 7 QR CODE, IT RETURNS TOAST
            if (QRScanText.equals(r111b7)) {
                Toast.makeText(getApplicationContext(), "You Do Not Have Block 7 On A Day 4, Please Make Your Way To The Right Class", Toast.LENGTH_LONG).show();
            }
            if (QRScanText.equals(r112b7)) {
                Toast.makeText(getApplicationContext(), "You Do Not Have Block 7 On A Day 4, Please Make Your Way To The Right Class", Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        scannerView.stopCamera();
    }

    @Override
    protected void onResume() {
        super.onResume();
        scannerView.setResultHandler(this);
        scannerView.startCamera();
    }
}