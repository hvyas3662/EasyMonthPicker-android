package com.elevationsoft.easymonthpicker;

import android.annotation.SuppressLint;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.StateListDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.core.widget.TextViewCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.elevationsoft.easymonthpicker.model.MonthPickerResult;

import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Objects;

@SuppressWarnings("unused")
public class MonthAdapter extends RecyclerView.Adapter<MonthAdapter.MonthHolder> {
    private final String[] months;
    private final EasyMonthPicker picker;
    private final ArrayList<Integer> selectedMonthIndexList;

    MonthAdapter(EasyMonthPicker picker, ArrayList<Integer> selectedMonthIndexList) {
        this.picker = picker;
        this.selectedMonthIndexList = selectedMonthIndexList;
        months = new DateFormatSymbols(picker.getLocale()).getShortMonths();
    }

    @NonNull
    @Override
    public MonthHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MonthHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view_month, parent, false));
    }

    @Override
    public void onBindViewHolder(MonthHolder holder, int position) {
        holder.textViewMonth.setText(months[position]);
        boolean isSelected = isItemSelected(position);
        if (picker.getSelectedMonthTextAppearance() != 0 && picker.getUnSelectedMonthTextAppearance() != 0) {
            TextViewCompat.setTextAppearance(holder.textViewMonth, isSelected ? picker.getSelectedMonthTextAppearance() : picker.getUnSelectedMonthTextAppearance());
        }
        holder.textViewMonth.setTextColor(isSelected ? picker.getSelectedMonthTextColor() : picker.getUnSelectedMonthTextColor());
        holder.itemView.setSelected(isSelected);
    }

    @Override
    public int getItemCount() {
        return months.length;
    }

    private boolean isItemSelected(int pos) {
        return selectedMonthIndexList.contains(pos);
    }

    private StateListDrawable setMonthBackgroundSelected(int color) {
        LayerDrawable layerDrawable = (LayerDrawable) ContextCompat.getDrawable(picker.getContext(), R.drawable.month_selected);
        GradientDrawable gradientDrawable = (GradientDrawable) Objects.requireNonNull(layerDrawable).getDrawable(1);
        gradientDrawable.setColor(color);
        layerDrawable.setDrawableByLayerId(1, gradientDrawable);

        StateListDrawable states = new StateListDrawable();
        states.addState(new int[]{android.R.attr.state_selected}, gradientDrawable);
        states.addState(new int[]{}, ContextCompat.getDrawable(picker.getContext(), R.drawable.month_default));
        return states;
    }

    private String getMonthName(boolean returnShortName, int index) {
        int n = index + 1;
        if (returnShortName) {
            switch (n) {
                case 1:
                    return "Jan";
                case 2:
                    return "Feb";
                case 3:
                    return "Mar";
                case 4:
                    return "Apr";
                case 5:
                    return "May";
                case 6:
                    return "Jun";
                case 7:
                    return "Jul";
                case 8:
                    return "Aug";
                case 9:
                    return "Sep";
                case 10:
                    return "Oct";
                case 11:
                    return "Nov";
                case 12:
                    return "Dec";
                default:
                    return "None";
            }
        } else {
            switch (n) {
                case 1:
                    return "January";
                case 2:
                    return "February";
                case 3:
                    return "March";
                case 4:
                    return "April";
                case 5:
                    return "May";
                case 6:
                    return "June";
                case 7:
                    return "July";
                case 8:
                    return "August";
                case 9:
                    return "September";
                case 10:
                    return "October";
                case 11:
                    return "November";
                case 12:
                    return "December";
                default:
                    return "None";
            }

        }
    }

    private int getEndDate(int month) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.MONTH, month);
        cal.set(Calendar.YEAR, picker.getSelectedYear());
        return cal.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    ArrayList<MonthPickerResult> getSelectedMonthResultList() {
        ArrayList<MonthPickerResult> resultList = new ArrayList<>();
        for (int i = 0; i < selectedMonthIndexList.size(); i++) {
            int index = selectedMonthIndexList.get(i);
            String monthName = getMonthName(false, index);
            String monthNameShort = getMonthName(true, index);
            int monthLastDate = getEndDate(index);
            MonthPickerResult result = new MonthPickerResult(picker.getSelectedYear(), monthName, monthNameShort, index, monthLastDate);
            resultList.add(result);
        }
        return resultList;
    }

    class MonthHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        LinearLayout layoutMain;
        TextView textViewMonth;

        MonthHolder(View itemView) {
            super(itemView);
            layoutMain = itemView.findViewById(R.id.main_layout);
            textViewMonth = itemView.findViewById(R.id.text_month);
            if (picker.getMonthBgSelector() == 0) {
                layoutMain.setBackground(setMonthBackgroundSelected(picker.getDialogPrimaryColor()));
            } else {
                layoutMain.setBackgroundResource(picker.getMonthBgSelector());
            }
            itemView.setClickable(true);
            itemView.setOnClickListener(this);

        }

        @SuppressLint("NotifyDataSetChanged")
        @Override
        public void onClick(View v) {
            if (picker.setMultiSelect()) {
                if (selectedMonthIndexList.contains(getBindingAdapterPosition())) {
                    selectedMonthIndexList.remove((Integer) getBindingAdapterPosition());
                } else {
                    if (picker.getMaxMonthSelectionLimit() > selectedMonthIndexList.size()) {
                        selectedMonthIndexList.add(getBindingAdapterPosition());
                    }
                }
            } else {
                selectedMonthIndexList.clear();
                selectedMonthIndexList.add(getBindingAdapterPosition());
            }
            notifyDataSetChanged();
        }

    }
}
