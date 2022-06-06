package com.elevationsoft.easymonthpicker.listener;

import com.elevationsoft.easymonthpicker.model.MonthPickerResult;

import java.util.ArrayList;

public interface OnMonthMultiSelectListener {
    void onPositiveButtonClicked(ArrayList<MonthPickerResult> result);

    void onNegativeButtonClicked();
}
