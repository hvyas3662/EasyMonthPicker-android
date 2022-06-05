package com.elevationsoft.easymonthpicker;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.StateListDrawable;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;


public class MonthAdapter extends RecyclerView.Adapter<MonthAdapter.MonthHolder> {

    private String[] months;
    private int selectedColor, unselectedColor;
    private ArrayList<String> selectedItem = new ArrayList<>();
    private Context context;
    private int color;
    private int selectedTextAppearance = R.style.easymonthpicker, unSelectedTextAppearance = R.style.easymonthpicker;
    private int max_select = 1;
    private int monthViewBackground = -1;

    MonthAdapter(Context context, int selectedColor, int unselectedColor) {
        this.context = context;
        this.selectedColor = selectedColor;
        this.unselectedColor = unselectedColor;
        months = new DateFormatSymbols(Locale.ENGLISH).getShortMonths();

    }

    @Override
    public MonthHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MonthHolder(LayoutInflater.from(context).inflate(R.layout.item_view_month, parent, false));
    }

    @Override
    public void onBindViewHolder(MonthHolder holder, int position) {
        holder.textViewMonth.setText(months[position]);
        holder.textViewMonth.setTextAppearance(context, selectedItem.contains(position + "") ? selectedTextAppearance : unSelectedTextAppearance);
        holder.textViewMonth.setTextColor(selectedItem.contains(position + "") ? selectedColor : unselectedColor);
        holder.itemView.setSelected(selectedItem.contains(position + "") ? true : false);
    }

    @Override
    public int getItemCount() {
        return months.length;
    }

    void setLocale(Locale locale) {
        months = new DateFormatSymbols(locale).getShortMonths();
        notifyDataSetChanged();
    }

    void setItem(int index) {
        if (index < 12 || index > -1) {
            selectedItem.add(index + "");
            notifyDataSetChanged();
        }
    }

    void setSelectedUnselectedTextColor(int selectedColor, int unselectedColor) {
        this.unselectedColor = unselectedColor;
        this.selectedColor = selectedColor;
        notifyDataSetChanged();
    }

    void setBackgroundMonth(int color) {
        this.color = color;
    }

    void setSelectedTextAppearanceId(int selectedId, int unSelectedId) {
        selectedTextAppearance = selectedId;
        unSelectedTextAppearance = unSelectedId;
        notifyDataSetChanged();
    }

    void setSelectionLimit(int limit) {
        max_select = limit;
    }

    void setMonthViewBackground(int id) {
        monthViewBackground = id;
        notifyDataSetChanged();
    }

    private String getMonth_name(boolean short_name_enable, int index) {
        int n = index + 1;
        if (short_name_enable) {
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
        cal.set(Calendar.DAY_OF_MONTH, month);
        return cal.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    ArrayList<String> getMonthList(boolean short_name_enable) {
        ArrayList<String> result = new ArrayList<>();
        for (int i = 0; i < selectedItem.size(); i++) {
            result.add(getMonth_name(short_name_enable, Integer.parseInt(selectedItem.get(i))));

        }
        return result;
    }

    ArrayList<Integer> getMonthListIndex() {
        ArrayList<Integer> result = new ArrayList<>();
        for (int i = 0; i < selectedItem.size(); i++) {
            result.add(Integer.parseInt(selectedItem.get(i)));

        }
        return result;
    }

    ArrayList<Integer> getEndDateList() {
        ArrayList<Integer> result = new ArrayList<>();
        for (int i = 0; i < selectedItem.size(); i++) {
            result.add(getEndDate(Integer.parseInt(selectedItem.get(i))));

        }
        return result;
    }


    class MonthHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        LinearLayout layoutMain;
        TextView textViewMonth;

        MonthHolder(View itemView) {
            super(itemView);
            layoutMain = itemView.findViewById(R.id.main_layout);
            textViewMonth = itemView.findViewById(R.id.text_month);
            if (monthViewBackground != -1) {
                layoutMain.setBackgroundResource(monthViewBackground);
            } else {
                if (color != 0) {
                    setMonthBackgroundSelected(color);
                }
            }
            itemView.setClickable(true);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (max_select == 1) {
                selectedItem.clear();
                selectedItem.add(getAdapterPosition() + "");
            } else {
                if (selectedItem.contains(getAdapterPosition() + "")) {
                    selectedItem.remove(getAdapterPosition() + "");
                } else {
                    if (selectedItem.size() < max_select) {
                        selectedItem.add(getAdapterPosition() + "");
                    }
                }
            }
            notifyDataSetChanged();
        }

        private void setMonthBackgroundSelected(int color) {
            LayerDrawable layerDrawable = (LayerDrawable) ContextCompat.getDrawable(context, R.drawable.month_selected);
            GradientDrawable gradientDrawable = (GradientDrawable) layerDrawable.getDrawable(1);
            gradientDrawable.setColor(ContextCompat.getColor(context, color));
            layerDrawable.setDrawableByLayerId(1, gradientDrawable);

            StateListDrawable states = new StateListDrawable();
            states.addState(new int[]{android.R.attr.state_selected}, gradientDrawable);
            states.addState(new int[]{}, ContextCompat.getDrawable(context, R.drawable.month_default));
            layoutMain.setBackground(states);
        }

    }

}
