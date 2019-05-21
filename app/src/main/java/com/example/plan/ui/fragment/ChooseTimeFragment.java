package com.example.plan.ui.fragment;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TimePicker;

import com.example.plan.R;
import com.example.plan.presenter.MainController;

import java.util.Calendar;


public class ChooseTimeFragment extends Fragment {

    private TimePicker mTimePicker;
    private MainController mController;

    public void setArguments(MainController controller) {
        mController = controller;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.choose_time_fragment, container, false);
        handleTimeSet(view);
        return view;
    }

    private void handleTimeSet(View view){
        mTimePicker = view.findViewById(R.id.time_picker);
        //Default value for time
        Calendar mcurrentTime = Calendar.getInstance();
        setTime(mcurrentTime.get(Calendar.HOUR_OF_DAY),mcurrentTime.get(Calendar.MINUTE));
        mTimePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                setTime(hourOfDay, minute);
            }
        });
    }

    private void setTime(int hour, int minute){
        mController.getCurrentDataNewTask().getmRemindMe().setmHour(hour);
        mController.getCurrentDataNewTask().getmRemindMe().setmMinute(minute);
        mController.getCurrentDataNewTask().getmRemindMe().setmSecond(00);
    }
}
