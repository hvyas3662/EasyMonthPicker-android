package com.elevationsoft.easymonthpicker;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.StyleRes;
import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;
import androidx.core.widget.TextViewCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.elevationsoft.easymonthpicker.listener.OnMonthMultiSelectListener;
import com.elevationsoft.easymonthpicker.listener.OnMonthPickerDismissListener;
import com.elevationsoft.easymonthpicker.listener.OnMonthSelectListener;
import com.elevationsoft.easymonthpicker.model.MonthPickerResult;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

@SuppressWarnings("unused")
public class EasyMonthPicker {
    private final Context context;
    private AlertDialog mAlertDialog;
    public static final int JAN = 0, FEB = 1, MAR = 2, APR = 3, MAY = 4, JUN = 5, JUL = 6, AUG = 7, SEP = 8, OCT = 9, NOV = 10, DEC = 11;
    private TextView tvYear;

    //monthPickerData
    private String titleText, positiveButtonText, negativeButtonText;
    private int selectedYear, selectedMonthIndex, maxMonthSelectionLimit;
    private ArrayList<Integer> selectedMonthIndexList = new ArrayList<>();
    private boolean isCancelable, isMultiSelect;
    @DrawableRes
    private int nextButtonDrawable, previousButtonDrawable, monthBgSelector;
    @ColorInt
    private int dialogBgColor, dialogPrimaryColor, titleTextColor, selectedMonthTextColor, unSelectedMonthTextColor, yearWidgetColor;
    @StyleRes
    private int selectedMonthTextAppearance, unSelectedMonthTextAppearance, yearTextAppearance;

    private OnMonthMultiSelectListener onMonthMultiSelectListener = null;
    private OnMonthSelectListener onMonthSelectListener = null;
    private OnMonthPickerDismissListener onMonthPickerDismissListener = null;

    private Locale locale = Locale.ENGLISH;

    public EasyMonthPicker(Context context) {
        this.context = context;
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        titleText = context.getString(R.string.emp_text_select_month);
        positiveButtonText = context.getString(R.string.emp_text_ok);
        negativeButtonText = context.getString(R.string.emp_text_cancel);
        selectedYear = cal.get(Calendar.YEAR);
        selectedMonthIndex = cal.get(Calendar.MONTH);

        maxMonthSelectionLimit = 3;
        isCancelable = false;
        isMultiSelect = false;
        nextButtonDrawable = R.drawable.ic_next_year;
        previousButtonDrawable = R.drawable.ic_previous_year;
        monthBgSelector = 0;
        dialogBgColor = ContextCompat.getColor(context, R.color.emp_dialog_bg);
        dialogPrimaryColor = ContextCompat.getColor(context, R.color.emp_dialog_primary);
        titleTextColor = ContextCompat.getColor(context, R.color.emp_title_text_color);
        selectedMonthTextColor = ContextCompat.getColor(context, R.color.emp_selected_month_text_color);
        unSelectedMonthTextColor = ContextCompat.getColor(context, R.color.emp_month_text_color);
        yearWidgetColor = ContextCompat.getColor(context, R.color.emp_year_widget_color);
        selectedMonthTextAppearance = 0;
        unSelectedMonthTextAppearance = 0;
        yearTextAppearance = 0;
    }

    public EasyMonthPicker setPositiveButtonText(String text) {
        this.positiveButtonText = text;
        return this;
    }

    public EasyMonthPicker setNegativeButtonText(String text) {
        this.negativeButtonText = text;
        return this;
    }

    public EasyMonthPicker setSelectedMonth(int monthIndex) {
        this.selectedMonthIndex = monthIndex;
        return this;
    }

    public EasyMonthPicker isCancelable(boolean cancelable) {
        this.isCancelable = cancelable;
        return this;
    }

    public EasyMonthPicker setMultiSelect(int limit, ArrayList<Integer> monthIndexList) {
        this.isMultiSelect = true;
        this.maxMonthSelectionLimit = limit;
        this.selectedMonthIndexList.clear();
        this.selectedMonthIndexList.addAll(monthIndexList);
        return this;
    }

    public EasyMonthPicker setNextPreviousYearButtonDrawable(@DrawableRes int nextButtonDrawable, @DrawableRes int previousButtonDrawable) {
        this.nextButtonDrawable = nextButtonDrawable;
        this.previousButtonDrawable = previousButtonDrawable;
        return this;
    }

    public EasyMonthPicker setTitleTextColor(@ColorRes int titleTextColor) {
        this.titleTextColor = ContextCompat.getColor(context, titleTextColor);
        return this;
    }

    public EasyMonthPicker setSelectedUnselectedMonthTextColor(@ColorRes int unselectedColor, @ColorRes int selectedColor) {
        this.unSelectedMonthTextColor = ContextCompat.getColor(context, unselectedColor);
        this.selectedMonthTextColor = ContextCompat.getColor(context, selectedColor);
        return this;
    }

    public EasyMonthPicker setYearWidgetColor(@ColorRes int color) {
        this.yearWidgetColor = ContextCompat.getColor(context, color);
        return this;
    }

    public EasyMonthPicker setMonthTextAppearance(@StyleRes int unSelected, @StyleRes int selected) {
        this.selectedMonthTextAppearance = selected;
        this.unSelectedMonthTextAppearance = unSelected;
        return this;
    }

    public EasyMonthPicker setYearTextAppearance(@StyleRes int id) {
        this.yearTextAppearance = id;
        return this;
    }

    public EasyMonthPicker setOnMonthMultiSelectListener(OnMonthMultiSelectListener multiSelectListener) {
        onMonthMultiSelectListener = multiSelectListener;
        return this;
    }

    public EasyMonthPicker setOnMonthSelectListener(OnMonthSelectListener monthSelectListener) {
        onMonthSelectListener = monthSelectListener;
        return this;
    }

    public EasyMonthPicker setOnMonthPickerDismissListener(OnMonthPickerDismissListener dismissListener) {
        onMonthPickerDismissListener = dismissListener;
        return this;
    }

    public void dismiss() {
        mAlertDialog.dismiss();
    }

    public void show() {
        @SuppressLint("InflateParams")
        View contentView = LayoutInflater.from(context).inflate(R.layout.dialog_month_picker, null);
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(context, R.style.EasyMonthPicker_Dialog);
        mAlertDialog = alertBuilder.setView(contentView).setCancelable(isCancelable).create();
        mAlertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));


        contentView.setBackgroundColor(dialogBgColor);
        TextView tvTitleView = contentView.findViewById(R.id.title);
        ImageButton ivPreviousYear = contentView.findViewById(R.id.btn_previous);
        tvYear = contentView.findViewById(R.id.text_year);
        ImageButton ivNextYear = contentView.findViewById(R.id.btn_next);
        RecyclerView recyclerView = contentView.findViewById(R.id.recycler_view);
        TextView mPositiveButton = contentView.findViewById(R.id.btn_positive);
        TextView mNegativeButton = contentView.findViewById(R.id.btn_negative);

        tvTitleView.setBackgroundColor(dialogPrimaryColor);
        tvTitleView.setText(titleText);
        tvTitleView.setTextColor(titleTextColor);

        ivPreviousYear.setImageResource(previousButtonDrawable);
        ivPreviousYear.setColorFilter(yearWidgetColor, PorterDuff.Mode.SRC_IN);
        ivPreviousYear.setOnClickListener(v -> {
            selectedYear--;
            tvYear.setText(String.valueOf(selectedYear));

        });
        if (yearTextAppearance != 0) {
            TextViewCompat.setTextAppearance(tvYear, yearTextAppearance);
        }
        tvYear.setText(String.valueOf(selectedYear));
        tvYear.setTextColor(yearWidgetColor);
        ivNextYear.setImageResource(nextButtonDrawable);
        ivNextYear.setColorFilter(yearWidgetColor, PorterDuff.Mode.SRC_IN);
        ivNextYear.setOnClickListener(v -> {
            selectedYear++;
            tvYear.setText(String.valueOf(selectedYear));
        });

        ArrayList<Integer> selectedIndex = new ArrayList<>();
        if (isMultiSelect) {
            for (int i = 0; i < maxMonthSelectionLimit; i++) {
                if (selectedMonthIndexList.size() > i) {
                    selectedIndex.add(selectedMonthIndexList.get(i));
                }
            }
        } else {
            selectedIndex.add(selectedMonthIndex);
        }
        MonthAdapter monthAdapter = new MonthAdapter(this, selectedIndex);
        recyclerView.setLayoutManager(new GridLayoutManager(context, 4));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(monthAdapter);

        mPositiveButton.setText(positiveButtonText);
        mPositiveButton.setTextColor(dialogPrimaryColor);
        mPositiveButton.setOnClickListener(v -> {
                    if (isMultiSelect) {
                        if (onMonthMultiSelectListener != null) {
                            ArrayList<MonthPickerResult> result = monthAdapter.getSelectedMonthResultList();
                            onMonthMultiSelectListener.onPositiveButtonClicked(result);
                        }
                    } else {
                        if (onMonthSelectListener != null) {
                            ArrayList<MonthPickerResult> result = monthAdapter.getSelectedMonthResultList();
                            onMonthSelectListener.onPositiveButtonClicked(result.get(0));
                        }
                    }
                    mAlertDialog.dismiss();
                }
        );

        mNegativeButton.setText(negativeButtonText);
        mNegativeButton.setTextColor(dialogPrimaryColor);
        mNegativeButton.setOnClickListener(v -> {
                    if (isMultiSelect) {
                        if (onMonthMultiSelectListener != null) {
                            onMonthMultiSelectListener.onNegativeButtonClicked();
                        }
                    } else {
                        if (onMonthSelectListener != null) {
                            onMonthSelectListener.onNegativeButtonClicked();
                        }
                    }
                    mAlertDialog.dismiss();
                }
        );

        mAlertDialog.setOnDismissListener(dialog -> {
            if (onMonthPickerDismissListener != null) {
                onMonthPickerDismissListener.onMonthPickerDismissed();
            }
        });

        mAlertDialog.show();
    }

    public Context getContext() {
        return context;
    }

    public String getTitleText() {
        return titleText;
    }

    public EasyMonthPicker setTitleText(String title) {
        this.titleText = title;
        return this;
    }

    public int getSelectedYear() {
        return selectedYear;
    }

    public EasyMonthPicker setSelectedYear(int year) {
        this.selectedYear = year;
        return this;
    }

    public int getSelectedMonthIndex() {
        return selectedMonthIndex;
    }

    public int getMaxMonthSelectionLimit() {
        return maxMonthSelectionLimit;
    }

    public boolean setMultiSelect() {
        return isMultiSelect;
    }

    public int getMonthBgSelector() {
        return monthBgSelector;
    }

    public EasyMonthPicker setMonthBgSelector(@DrawableRes int monthBgSelector) {
        this.monthBgSelector = monthBgSelector;
        return this;
    }

    public int getDialogBgColor() {
        return dialogBgColor;
    }

    public EasyMonthPicker setDialogBgColor(@ColorRes int bgColor) {
        this.dialogBgColor = ContextCompat.getColor(context, bgColor);
        return this;
    }

    public int getDialogPrimaryColor() {
        return dialogPrimaryColor;
    }

    public EasyMonthPicker setDialogPrimaryColor(@ColorRes int color) {
        this.dialogPrimaryColor = ContextCompat.getColor(context, color);
        return this;
    }

    public int getSelectedMonthTextColor() {
        return selectedMonthTextColor;
    }

    public int getUnSelectedMonthTextColor() {
        return unSelectedMonthTextColor;
    }

    public int getSelectedMonthTextAppearance() {
        return selectedMonthTextAppearance;
    }

    public int getUnSelectedMonthTextAppearance() {
        return unSelectedMonthTextAppearance;
    }

    public Locale getLocale() {
        return locale;
    }

    public EasyMonthPicker setLocale(Locale locale) {
        this.locale = locale;
        return this;
    }
}
