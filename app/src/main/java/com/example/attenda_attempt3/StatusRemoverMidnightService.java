package com.example.attenda_attempt3;

import android.app.job.JobParameters;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class StatusRemoverMidnightService extends android.app.job.JobService {
    private static final String TAG = "Remove Status From Firestore";
    //figure this out, dont give up like you did yesterday  -past abrahim
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
    DocumentReference r111_b1 = db.collection("111-1").document(uid);
    DocumentReference r111_b2 = db.collection("111-2").document(uid);
    DocumentReference r111_b3 = db.collection("111-3").document(uid);
    DocumentReference r111_b4 = db.collection("111-4").document(uid);
    DocumentReference r111_b5 = db.collection("111-5").document(uid);
    DocumentReference r111_b6 = db.collection("111-6").document(uid);
    DocumentReference r111_b7 = db.collection("111-7").document(uid);
    DocumentReference r111_b8 = db.collection("111-8").document(uid);
    DocumentReference r112_b1 = db.collection("112-1").document(uid);
    DocumentReference r112_b2 = db.collection("112-2").document(uid);
    DocumentReference r112_b3 = db.collection("112-3").document(uid);
    DocumentReference r112_b4 = db.collection("112-4").document(uid);
    DocumentReference r112_b5 = db.collection("112-5").document(uid);
    DocumentReference r112_b6 = db.collection("112-6").document(uid);
    DocumentReference r112_b7 = db.collection("112-7").document(uid);
    DocumentReference r112_b8 = db.collection("112-8").document(uid);
    private boolean jobCancelled = false;

    @Override
    public boolean onStartJob(JobParameters params) {
        Log.d(TAG, "Job Started");
        doBackgroundWork(params);

        return true;
    }

    private void doBackgroundWork(JobParameters params) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.HOUR_OF_DAY, 23);
                calendar.set(Calendar.MINUTE, 59);
                calendar.set(Calendar.SECOND, 0);
                Date time = calendar.getTime();

                Timer timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        Map<String, Object> updates = new HashMap<>();
                        updates.put("status", FieldValue.delete());
                        r111_b1.update(updates);
                        r111_b2.update(updates);
                        r111_b3.update(updates);
                        r111_b4.update(updates);
                        r111_b5.update(updates);
                        r111_b6.update(updates);
                        r111_b7.update(updates);
                        r111_b8.update(updates);
                        r112_b1.update(updates);
                        r112_b2.update(updates);
                        r112_b3.update(updates);
                        r112_b4.update(updates);
                        r112_b5.update(updates);
                        r112_b6.update(updates);
                        r112_b7.update(updates);
                        r112_b8.update(updates);

                        Log.d(TAG, "Job Finished");
                        jobFinished(params, false);
                    }
                }, time);
            }
        }).start();
    }

    @Override
    public boolean onStopJob(JobParameters jobParameters) {
        Log.d(TAG, "Job Cancelled Before Completion");
        jobCancelled = true;
        return true;
    }
}
