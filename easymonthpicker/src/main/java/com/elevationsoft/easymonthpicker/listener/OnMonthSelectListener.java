package com.elevationsoft.easymonthpicker.listener;

import com.elevationsoft.easymonthpicker.model.MonthPickerResult;

public interface OnMonthSelectListener {
    void onPositiveButtonClicked(MonthPickerResult result);

    void onNegativeButtonClicked();
}
