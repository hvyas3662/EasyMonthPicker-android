package com.hvyas.easymonthpicker;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hvyas.easymonthpicker.listener.DateMonthDialogListener;
import com.hvyas.easymonthpicker.listener.OnCancelMonthDialogListener;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class EasyMonthPicker {

    private AlertDialog mAlertDialog;
    private EasyMonthPicker.Builder builder;
    private Context context;
    private Button mPositiveButton;
    private Button mNegativeButton;
    private DateMonthDialogListener dateMonthDialogListener;
    private OnCancelMonthDialogListener onCancelMonthDialogListener;
    private boolean isBuild = false;
    private boolean ShortMonthNameEnable = false;
    public static final int JAN = 0, FEB = 1, MAR = 2, APR = 3, MAY = 4, JUN = 5, JUL = 6, AUG = 7, SEP = 8, OCT = 9, NOV = 10, DEC = 11;

    public EasyMonthPicker(Context context) {
        this.context = context;
        builder = new Builder();
    }

    public void show() {
        if (isBuild) {
            mAlertDialog.show();
        } else {
            builder.build();
            isBuild = true;
        }
    }

    public void dismiss() {
        mAlertDialog.dismiss();
    }

    public EasyMonthPicker setTitleTextColor(int TitleTextColorId) {
        builder.setTitleTextColor(TitleTextColorId);
        return this;
    }

    public EasyMonthPicker setTitleText(String title) {
        builder.setTitleText(title);
        return this;
    }

    public EasyMonthPicker setColorThemeId(int color) {
        builder.setColorTheme(color);
        return this;
    }

    public EasyMonthPicker setBodyBgId(int BodyBgId) {
        builder.setBodyBg(BodyBgId);
        return this;
    }

    public EasyMonthPicker setBottomBtnBgId(int BottomBtnBgId) {
        builder.setBottomBtnBg(BottomBtnBgId);
        return this;
    }

    public EasyMonthPicker setYearTextAppearanceId(int id) {
        builder.setYearTextAppearanceId(id);
        return this;
    }

    public EasyMonthPicker setYearWidgetColor(int color) {
        builder.setYearWidgetColor(color);
        return this;
    }

    public EasyMonthPicker setNextPreviousButtonDrawable(int NextButtonDrawableId, int PreviousButtonDrawableId) {
        builder.setNextPreviousButtonDrawable(NextButtonDrawableId, PreviousButtonDrawableId);
        return this;
    }

    public EasyMonthPicker setMonthTextAppearanceId(int selectedId, int unSelectedId) {
        builder.setMonthViewTextAppearanceId(selectedId, unSelectedId);
        return this;
    }

    public EasyMonthPicker setMonthViewBackgroundId(int MonthViewBackgroundId) {
        builder.setMonthViewBackgroundId(MonthViewBackgroundId);
        return this;
    }

    public EasyMonthPicker setSelectedUnselectedTextColor(int selectedColor, int unselectedColor) {
        builder.setSelectedUnselectedTextColor(selectedColor, unselectedColor);
        return this;
    }

    public EasyMonthPicker setPositiveText(String text) {
        mPositiveButton.setText(text);
        return this;
    }

    public EasyMonthPicker setNegativeText(String text) {
        mNegativeButton.setText(text);
        return this;
    }

    public EasyMonthPicker setShortMonthNameEnable(Boolean value) {
        builder.setShortMonthNameEnable(value);
        return this;
    }

    public EasyMonthPicker setLocale(Locale locale) {
        builder.setLocale(locale);
        return this;
    }

    public EasyMonthPicker setYear(int index) {
        builder.setYear(index);
        return this;
    }

    public EasyMonthPicker setMonth(int index) {
        builder.setMonth(index);
        return this;
    }

    public EasyMonthPicker setCancelable(boolean cancelable) {
        builder.cancelable = cancelable;
        return this;
    }

    public EasyMonthPicker setPositiveButton(DateMonthDialogListener dateMonthDialogListener) {
        this.dateMonthDialogListener = dateMonthDialogListener;
        mPositiveButton.setOnClickListener(builder.positiveButtonClick());
        return this;
    }

    public EasyMonthPicker setNegativeButton(OnCancelMonthDialogListener onCancelMonthDialogListener) {
        this.onCancelMonthDialogListener = onCancelMonthDialogListener;
        mNegativeButton.setOnClickListener(builder.negativeButtonClick());
        return this;
    }

    public EasyMonthPicker setSelectionLimit(int limit) {
        builder.setSelectionLimit(limit);
        return this;
    }

    private class Builder {

        private MonthAdapter monthAdapter;
        private TextView mTitleView;
        private TextView mYear;
        private int year;
        private AlertDialog.Builder alertBuilder;
        private View contentView;
        private boolean cancelable = true;
        private LinearLayout background, buttonLayout;
        private int NextButtonDrawable = R.drawable.forward, PreviousButtonDrawable = R.drawable.back, TitleTextColor = -1, BodyBg = -1, BottomBtnBg = -1;
        private int selectedColor = Color.WHITE, unselectedColor = Color.BLACK, YearWidgetColor = Color.BLACK;
        private ImageButton next, previous;
        private int selectedTextAppearance, unSelectedTextAppearance, selectedYearTextAppearance;
        private int max_select;

        private Builder() {
            alertBuilder = new AlertDialog.Builder(context);
            contentView = LayoutInflater.from(context).inflate(R.layout.dialog_month_picker, null, false);
            contentView.setFocusable(true);
            contentView.setFocusableInTouchMode(true);

            Date date = new Date();
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            year = cal.get(Calendar.YEAR);

            mTitleView = contentView.findViewById(R.id.title);
            background = contentView.findViewById(R.id.material_background);
            buttonLayout = contentView.findViewById(R.id.buttonLayout);

            mYear = contentView.findViewById(R.id.text_year);
            mYear.setText(year + "");

            next = contentView.findViewById(R.id.btn_next);
            next.setOnClickListener(nextButtonClick());

            previous = contentView.findViewById(R.id.btn_previous);
            previous.setOnClickListener(previousButtonClick());

            mPositiveButton = contentView.findViewById(R.id.btn_p);
            mNegativeButton = contentView.findViewById(R.id.btn_n);


            monthAdapter = new MonthAdapter(context, selectedColor, unselectedColor);
            RecyclerView recyclerView = contentView.findViewById(R.id.recycler_view);
            recyclerView.setLayoutManager(new GridLayoutManager(context, 4));
            recyclerView.setHasFixedSize(true);
            recyclerView.setAdapter(monthAdapter);
        }

        void setLocale(Locale locale) {
            monthAdapter.setLocale(locale);
        }

        void setYear(int year) {
            this.year = year;
            mYear.setText(year + "");
        }

        void setSelectionLimit(int limit) {
            max_select = limit;
            monthAdapter.setSelectionLimit(max_select);
        }

        void setMonth(int index) {
            monthAdapter.setItem(index);
        }

        void setTitleTextColor(int TitleTextColorId) {
            TitleTextColor = TitleTextColorId;
            mTitleView.setTextColor(TitleTextColor);
        }

        void setBodyBg(int BodyBgId) {
            BodyBg = BodyBgId;
            background.setBackgroundResource(BodyBg);
        }

        void setBottomBtnBg(int BottomBtnBgId) {
            BottomBtnBg = BottomBtnBgId;
            buttonLayout.setBackgroundResource(BottomBtnBg);
        }

        void setTitleText(String title) {
            mTitleView.setText(title);
        }

        void setSelectedUnselectedTextColor(int selectedColor, int unselectedColor) {
            this.unselectedColor = unselectedColor;
            this.selectedColor = selectedColor;
            monthAdapter.setSelectedUnselectedTextColor(selectedColor, unselectedColor);
        }

        void setNextPreviousButtonDrawable(int NextButtonDrawableId, int PreviousButtonDrawableId) {
            NextButtonDrawable = NextButtonDrawableId;
            PreviousButtonDrawable = PreviousButtonDrawableId;
            next.setImageResource(NextButtonDrawable);
            next.setColorFilter(YearWidgetColor, PorterDuff.Mode.SRC_IN);
            previous.setImageResource(PreviousButtonDrawable);
            previous.setColorFilter(YearWidgetColor, PorterDuff.Mode.SRC_IN);
            mYear.setTextColor(YearWidgetColor);
            mYear.setTextAppearance(context, selectedYearTextAppearance);
        }

        void setColorTheme(int color) {
            LinearLayout linearToolbar = contentView.findViewById(R.id.linear_toolbar);
            linearToolbar.setBackgroundColor(ContextCompat.getColor(context, color));

            monthAdapter.setBackgroundMonth(color);
            mPositiveButton.setTextColor(ContextCompat.getColor(context, color));
            mNegativeButton.setTextColor(ContextCompat.getColor(context, color));
        }

        void setMonthViewTextAppearanceId(int selectedId, int unSelectedId) {
            selectedTextAppearance = selectedId;
            unSelectedTextAppearance = unSelectedId;
            monthAdapter.setSelectedTextAppearanceId(selectedTextAppearance, unSelectedTextAppearance);
        }

        void setYearTextAppearanceId(int id) {
            selectedYearTextAppearance = id;
            mYear.setTextAppearance(context, selectedYearTextAppearance);
        }

        void setShortMonthNameEnable(Boolean value) {
            ShortMonthNameEnable = value;
        }

        void setYearWidgetColor(int color) {
            YearWidgetColor = color;
            next.setImageResource(NextButtonDrawable);
            next.setColorFilter(YearWidgetColor, PorterDuff.Mode.SRC_IN);
            previous.setImageResource(PreviousButtonDrawable);
            previous.setColorFilter(YearWidgetColor, PorterDuff.Mode.SRC_IN);
            mYear.setTextColor(YearWidgetColor);
            mYear.setTextAppearance(context, selectedYearTextAppearance);
        }

        void setMonthViewBackgroundId(int MonthViewBackgroundId) {
            monthAdapter.setMonthViewBackground(MonthViewBackgroundId);
        }

        void build() {
            mAlertDialog = alertBuilder.create();
            mAlertDialog.show();
            mAlertDialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
            mAlertDialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_MASK_STATE);
            mAlertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
            mAlertDialog.getWindow().setBackgroundDrawableResource(R.drawable.material_dialog_window);
            mAlertDialog.getWindow().setContentView(contentView);
            mAlertDialog.setCancelable(cancelable);

        }

        View.OnClickListener nextButtonClick() {
            return new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    year++;
                    mYear.setText(year + "");
                }
            };
        }

        View.OnClickListener previousButtonClick() {
            return new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    year--;
                    mYear.setText(year + "");
                }
            };
        }

        View.OnClickListener positiveButtonClick() {
            return new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dateMonthDialogListener.onDateMonth(
                            year,
                            monthAdapter.getMonthListIndex(),
                            monthAdapter.getEndDateList(),
                            monthAdapter.getMonthList(ShortMonthNameEnable));

                    mAlertDialog.dismiss();
                }
            };
        }

        View.OnClickListener negativeButtonClick() {
            return new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onCancelMonthDialogListener.onCancel(mAlertDialog);
                }
            };
        }

    }
}
