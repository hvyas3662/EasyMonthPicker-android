package com.elevationsoft.easymonthpicker.model;

public class MonthPickerResult {
    private int year;
    private String selectedMonthName;
    private String selectedMonthShortName;
    private int selectedMonthIndex;
    private int selectedMonthEndDate;

    public MonthPickerResult(int year, String selectedMonthName, String selectedMonthShortName, int selectedMonthIndex, int selectedMonthEndDate) {
        this.year = year;
        this.selectedMonthName = selectedMonthName;
        this.selectedMonthShortName = selectedMonthShortName;
        this.selectedMonthIndex = selectedMonthIndex;
        this.selectedMonthEndDate = selectedMonthEndDate;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getSelectedMonthName() {
        return selectedMonthName;
    }

    public void setSelectedMonthName(String selectedMonthName) {
        this.selectedMonthName = selectedMonthName;
    }

    public String getSelectedMonthShortName() {
        return selectedMonthShortName;
    }

    public void setSelectedMonthShortName(String selectedMonthShortName) {
        this.selectedMonthShortName = selectedMonthShortName;
    }

    public int getSelectedMonthIndex() {
        return selectedMonthIndex;
    }

    public void setSelectedMonthIndex(int selectedMonthIndex) {
        this.selectedMonthIndex = selectedMonthIndex;
    }

    public int getSelectedMonthEndDate() {
        return selectedMonthEndDate;
    }

    public void setSelectedMonthEndDate(int selectedMonthEndDate) {
        this.selectedMonthEndDate = selectedMonthEndDate;
    }
}
