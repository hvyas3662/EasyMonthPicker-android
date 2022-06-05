package com.elevationsoft.sample_month_picker;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.elevationsoft.easymonthpicker.EasyMonthPicker;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new EasyMonthPicker(this)
                .setYear(2020)
                .setMonth(EasyMonthPicker.FEB)
                .setCancelable(false)
                .setBodyBgId(R.color.white)
                .setBottomBtnBgId(R.color.white)
                .setTitleTextColor(Color.WHITE)
                .setSelectedUnselectedTextColor(Color.WHITE, Color.BLACK)
                .setColorThemeId(R.color.colorAccent)
                .setPositiveButton((year, selectedMonthIndexList, EndDate, month_name) -> {
                    Log.d("year", year + "");
                    Log.d("selectedMonthIndexList", selectedMonthIndexList.toString());
                    Log.d("EndDate", EndDate.toString());
                    Log.d("month_name", month_name.toString());
                })
                .show();
    }
}
