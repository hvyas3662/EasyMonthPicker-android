# EasyMonthPicker-android
EasyMonthPicker :- A android library helps you to select one or more month(s) and number of days in that month.

[![](https://jitpack.io/v/hvyas3662/EasyMonthPicker-android.svg)](https://jitpack.io/#hvyas3662/EasyMonthPicker-android)

<div width="100%">
  <img src="images/single.jpg" style="float:left; margin-right:200px;" width="300" height="533">
  <img src="images/multipal_select.jpg" style="float:left;" width="300" height="533">
</div>

## How to configure

 For a working implementation, please have a look at the app directory
 1. Add maven in your root build.gradle

```gradle
allprojects {
  repositories {
    maven { url 'https://jitpack.io' }
  }
}
```
 2. Add the dependency in your app level build.gradle
 ```gradle
dependencies {
  implementation 'com.github.hvyas3662:EasyMonthPicker-android:2.0'
}
```

## How do I use EasyMonthPicker?

  ```java
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
        .setSelectedUnselectedMonthTextColor(R.color.white,R.color.black)
        .setYearTextAppearance(R.style.tp)
        .setMonthTextAppearance(R.style.tp,R.style.tp1)
        .setNextPreviousYearButtonDrawable(R.drawable.ic_arrow_forward_black_24dp,R.drawable.ic_arrow_back_black_24dp)
        .setOnMonthSelectListener(new OnMonthSelectListener(){
          @Override
          public void onPositiveButtonClicked(MonthPickerResult result){
                  Log.d("year",result.getYear()+"");
                  Log.d("month_name",result.getSelectedMonthName());
                  Log.d("month_name_short",result.getSelectedMonthShortName());
                  Log.d("selectedMonthIndex",result.getSelectedMonthIndex()+"");
                  Log.d("EndDate",result.getSelectedMonthEndDate()+"");
              }

          @Override
          public void onNegativeButtonClicked(){

             }
        })
        .show();
  ```
  
## Other methods

   Below written each method return EasyMonthPicker class current instance for method chaining.
   
   
   | Methods | Description |
   | --- | --- |
   | `EasyMonthPicker(Context context)` | take activity context in argument and init builder class  |
   | `show()` | Show the dialog |
   | `dismiss()` | Dismiss the dialog |
   | `setTitleTextColor(int TitleTextColorId)` | Set the dialog title text color |
   | `setTitleText(String title)` | Set the dialog title text |
   | `setColorThemeId(int color)` | Set the dialog theme color (Change the title background color, selected month drawable color, positive and negative button color) | 
   | `setBodyBgId(int BodyBgId)` | Set dialog body color or drawable (below title part), required resource id in argument | 
   | `setBottomBtnBgId(int BottomBtnBgId)` | Set positive and negative button background color or drawable, required resource id in argument |
   | `setYearTextAppearanceId(int id)` | Set YearTextAppearance, required resource id in argument |
   | `setYearWidgetColor(int color)` | set year widget color (change next, previous and year text color) |
   | `setNextPreviousButtonDrawable(int NextButtonDrawableId, int PreviousButtonDrawableId)` | set the Drawable of next, previous buttons, required resource id in argument  |
   | `setMonthTextAppearanceId(int selectedId, int unSelectedId)` | Set the Month selecte and unselected state textAppearanceId, required resource id in argument  |
   | `setMonthViewBackgroundId(int MonthViewBackgroundId)` | Set custom state list background to month view , required resource id in argument  |
   | `setSelectedUnselectedTextColor(int selectedColor, int unselectedColor)` | Set selected and un selected month text color |
   | `setPositiveText(String text)` | Set positive button text |
   | `setNegativeText(String text)` | Set negative button text |
   | `setShortMonthNameEnable(Boolean value)` | If set true return month short name eg. Jan, else return month full name eg. january |
   | `setLocale(Locale locale)` | Set the local |
   | `setYear(int index)` | set selected year |
   | `setMonth(int index)` | Set selected month |
   | `setCancelable(boolean cancelable)` | Set Cancelable |
   | `PositiveButton(DateMonthDialogListener dateMonthDialogListener)` | Set Positive Button click listener |
   | `setNegativeButton(OnCancelMonthDialogListener onCancelMonthDialogListener)` |  Set Negative Button click listener |
   | `setSelectionLimit(int limit)` | Set maximum month selection limit |
  

## Compatibility
  
  * Requires minimum android API 21 or above
