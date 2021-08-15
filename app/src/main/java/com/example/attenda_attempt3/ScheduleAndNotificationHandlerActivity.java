package com.example.attenda_attempt3;

import static com.example.attenda_attempt3.DaysOneToFourHandlerActivity.day;
import static com.example.attenda_attempt3.StudentScheduleSelectActivity.block1Text;
import static com.example.attenda_attempt3.StudentScheduleSelectActivity.block2Text;
import static com.example.attenda_attempt3.StudentScheduleSelectActivity.block3Text;
import static com.example.attenda_attempt3.StudentScheduleSelectActivity.block4Text;
import static com.example.attenda_attempt3.StudentScheduleSelectActivity2.block5Text;
import static com.example.attenda_attempt3.StudentScheduleSelectActivity2.block6Text;
import static com.example.attenda_attempt3.StudentScheduleSelectActivity2.block7Text;
import static com.example.attenda_attempt3.StudentScheduleSelectActivity2.block8Text;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class ScheduleAndNotificationHandlerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_and_notification_handler);

        String dayNumber = day;

        String blockOne = block1Text;
        String blockTwo = block2Text;
        String blockThree = block3Text;
        String blockFour = block4Text;
        String blockFive = block5Text;
        String blockSix = block6Text;
        String blockSeven = block7Text;
        String blockEight = block8Text;

        String day1 = "Day1";
        String day2 = "Day2";
        String day3 = "Day3";
        String day4 = "Day4";


    }
}