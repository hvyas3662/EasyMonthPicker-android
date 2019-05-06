# EasyMonthPicker-android
EasyMonthPicker :- A android library helps you to select one or more month(s) and number of days in that month.

[![](https://jitpack.io/v/hvyas3662/EasyMonthPicker-android.svg)](https://jitpack.io/#hvyas3662/EasyMonthPicker-android)

<div width="100%">
  <img src="images/single.jpg" style="float:left; margin-right:200px;" width="300" height="533">
  <img src="images/multipal_select.jpg" style="float:left;" width="300" height="533">
</div>

## How to configure

 For a working implementation, please have a look at the app directory
 1. Add maven in your root build.gradle at the end of repositories
 2. Add the dependency
```gradle
allprojects {
  repositories {
    maven { url 'https://jitpack.io' }
  }
}

dependencies {
  implementation 'com.github.hvyas3662:EasyMonthPicker-android:1.0'
}
```

## How do I use EasyMonthPicker?

  ```java
      new EasyMonthPicker(this)
          .setYear(2020)
          .setMonth(EasyMonthPicker.FEB)
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

  ```
  
## Other methods

   | Methods | Description |
   | --- | --- |
   | `getSelectedDays()` | Return Boolean type ArrayList with 7 element (if day is selected return true else return false at same index, 0th index represent sunday )  |
   | `getSelectedDaysName()` | Return String type ArrayList of selected day's full name eg. sunday |
   | `getSelectedDaysShortName()` | Return String type ArrayList of selected day's short name eg. sun |
   | `getNthChild(int index)` | Return button view of given index, if index value is more then 7 or less then 0 then this method return null  |
   | `setOnDaySelectionChangeListener(OnDaySelectionChangeListener listener)` | set Selection change listener |
   | `setDaysName(String[] days_name)` | Set day button text using 0 to 6th index value of given argument | 
   | `setDayBtnTextAppearance(Context ctx, int id)` | Set textAppearance of each day button | 
   | `getDayBtnTextAppearance()` | Return textAppearance id |
   | `setDayBtnBackground(int id)` | Set background drawable, take id of drawable eg. R.drawable.your_custom_drawable |
   | `getDayBtnBackground()` | Return drawable id |
   | `setDayBtnMarginPx(int left, int top, int right, int bottom)` | Set left, top, bottom, right margin to each day buttom, given value is pixel |
   | `getDayBtnMarginLeftPx()` | Return left margin value in pixel |
   | `getDayBtnMarginTopPx()` | Return top margin value in pixel |
   | `getDayBtnMarginRightPx()` | Return right margin value in pixel |
   | `getDayBtnMarginBottomPx()` | Return bottom margin value in pixel |
   | `setDayBtnStateTextColors(int DayBtnSelectedTextColor, int DayBtnUnSelectedTextColor)` | Set text color of selected and unselected state of each day buttom |
   | `getDayBtnSelectedTextColor()` | Return selected state textcolor of day button |
   | `getDayBtnUnSelectedTextColor()` | Return unselected state textcolor of day button |
   | `setDayBtnTextSize()` | Set text size of each day buttom |
   | `setMultipleSelected(boolean status_sun, boolean status_mon, boolean status_tue, boolean status_wed, boolean status_thu, boolean status_fri, boolean status_sat) ` | Select Multiple days |

## Compatibility
  
  * Requires minimum android API 21 or above
