package com.hvyas.easymonthpicker.listener;

import java.util.ArrayList;

public interface DateMonthDialogListener {
    void onDateMonth(int year, ArrayList<Integer> selectedMonthIndexList, ArrayList<Integer> EndDate, ArrayList<String> month_name);
}
