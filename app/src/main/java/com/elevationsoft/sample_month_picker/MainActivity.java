package com.elevationsoft.sample_month_picker;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.elevationsoft.easymonthpicker.EasyMonthPicker;
import com.elevationsoft.easymonthpicker.listener.OnMonthSelectListener;
import com.elevationsoft.easymonthpicker.model.MonthPickerResult;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new EasyMonthPicker(this)
                .setTitleText("Month Picker")
                .setTitleTextColor(R.color.black)
                .setYearWidgetColor(R.color.white)
                .setPositiveButtonText("Select")
                .setNegativeButtonText("Dismiss")
                .setSelectedYear(2024)
                .setSelectedMonth(EasyMonthPicker.FEB)
                .isCancelable(true)
                .setDialogBgColor(R.color.colorRed)
                .setDialogPrimaryColor(R.color.white)
                .setSelectedUnselectedMonthTextColor(R.color.white, R.color.black)
                .setYearTextAppearance(R.style.tp)
                .setMonthTextAppearance(R.style.tp, R.style.tp1)
                .setNextPreviousYearButtonDrawable(R.drawable.ic_arrow_forward_black_24dp, R.drawable.ic_arrow_back_black_24dp)
                .setOnMonthSelectListener(new OnMonthSelectListener() {
                    @Override
                    public void onPositiveButtonClicked(MonthPickerResult result) {
                        Log.d("year", result.getYear() + "");
                        Log.d("month_name", result.getSelectedMonthName());
                        Log.d("month_name_short", result.getSelectedMonthShortName());
                        Log.d("selectedMonthIndex", result.getSelectedMonthIndex() + "");
                        Log.d("EndDate", result.getSelectedMonthEndDate() + "");
                    }

                    @Override
                    public void onNegativeButtonClicked() {

                    }
                })
                .show();
    }
}
