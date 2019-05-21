package com.example.plan.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;

import com.example.plan.R;
import com.example.plan.presenter.MainController;

import java.util.Calendar;
import java.util.Date;

public class ChooseDateFragment extends Fragment {

    private MainController mController;
    private DatePicker mDatePicker;

    public void setArguments(MainController controller) {
        mController = controller;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.choose_date_fragment, container, false);
        handleDateSet(view);
        return view;
    }

    private void handleDateSet(View view) {
        mDatePicker = view.findViewById(R.id.date_picker);
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(calendar.YEAR);
        int month = calendar.get(calendar.MONTH);
        int day = calendar.get(calendar.DAY_OF_MONTH);
        setDate(year, month, day);
        mDatePicker.init(year,month,day,new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                setDate(year, monthOfYear, dayOfMonth);
            }
        });
    }

    private void setDate(int year, int month, int day){
        mController.getCurrentDataNewTask().getmRemindMe().setmDate(new Date(year, month, day).getTime());
    }
}
