package com.hvyas.sample_month_picker;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.hvyas.easymonthpicker.EasyMonthPicker;
import com.hvyas.easymonthpicker.listener.DateMonthDialogListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new EasyMonthPicker(this)
                .setYear(2020)
                .setCancelable(false)
                .setBodyBgId(R.color.white)
                .setBottomBtnBgId(R.color.white)
                .setTitleTextColor(Color.WHITE)
                .setSelectedUnselectedTextColor(Color.WHITE, Color.BLACK)
                .setColorThemeId(R.color.colorAccent)
                .setPositiveButton(new DateMonthDialogListener() {
                    @Override
                    public void onDateMonth(int year, ArrayList<Integer> selectedMonthIndexList, ArrayList<Integer> EndDate, ArrayList<String> month_name) {
                        Log.d("year", year + "");
                        Log.d("selectedMonthIndexList", selectedMonthIndexList.toString());
                        Log.d("EndDate", EndDate.toString());
                        Log.d("month_name", month_name.toString());
                    }
                })
                .show();
    }
}
