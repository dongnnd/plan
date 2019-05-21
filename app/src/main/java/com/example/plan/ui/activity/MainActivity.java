package com.example.plan.ui.activity;

import android.app.TimePickerDialog;
import android.arch.lifecycle.Observer;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TimePicker;

import com.example.plan.R;
import com.example.plan.databinding.ActivityMainBinding;
import com.example.plan.presenter.MainController;
import com.example.plan.ui.dialog.ChooseDayRepeat;
import com.example.plan.ui.dialog.ChooseItemDialog;
import com.example.plan.ui.dialog.ChooseDateTimeDialog;
import com.example.plan.ui.fragment.NavigationFragment;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {


    private MainController mController;
    private DrawerLayout mDrawerLayout;
    private Toolbar mToolbar;
    private FloatingActionButton mAddTask;
    private BottomSheetBehavior mAddListBehavior;
    private ActivityMainBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        initController();
        observerStateDrawer();
        initDrawerLayout();
        initView();
        initBottomViewAddTask();
        handleActionFromBottomSheet();
        setSoftInputMode();

    }

    private void setSoftInputMode(){
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
    }


    private void observerStateDrawer() {
        mController.getStateDrawer().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean aBoolean) {
                if (aBoolean) {
                    mDrawerLayout.closeDrawer(GravityCompat.START);
                }
            }
        });
    }

    private void handleActionFromBottomSheet() {
        Button btnAddPlan = findViewById(R.id.btn_sheet_add_list);
        btnAddPlan.setOnClickListener(mBottomSheetClick);
        Button btnRepeat = findViewById(R.id.btn_sheet_repeat);
        btnRepeat.setOnClickListener(mBottomSheetClick);
        Button btnRemind = findViewById(R.id.btn_sheet_remind);
        btnRemind.setOnClickListener(mBottomSheetClick);
    }


    private void initView() {

        //Floating button
        mAddTask = findViewById(R.id.fab);
        mAddTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showBottomAddTask(true);
            }
        });


    }

    private void initBottomViewAddTask() {
        // BottomSheet AddTask
        LinearLayout llBottomSheet = findViewById(R.id.bottom_sheet);
// init the bottom sheet behavior
        mAddListBehavior = BottomSheetBehavior.from(llBottomSheet);

// change the state of the bottom sheet
        //bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        mAddListBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        //bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);

// set the peek height
        mAddListBehavior.setPeekHeight(0);

// set hideable or not
        showBottomAddTask(false);

// set callback for changes
        mAddListBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                if (newState == BottomSheetBehavior.STATE_COLLAPSED) {
                    showBottomAddTask(false);
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
            }
        });
    }

    private void initController() {
        mController = new MainController(getApplication());
    }

    private void initDrawerLayout() {
        mToolbar = findViewById(R.id.toolbar);
        mDrawerLayout = findViewById(R.id.navigation_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        NavigationFragment fragment = new NavigationFragment();
        fragment.setMainController(mController);
        getSupportFragmentManager().beginTransaction().replace(R.id.navigation_container, fragment).commit();
    }

    private void showBottomAddTask(boolean value) {
        if (value) {
            mAddListBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
            mBinding.containerViewId.setShowFloatBtn(false);
        } else {
            mAddListBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
            mBinding.containerViewId.setShowFloatBtn(true);
        }

    }

    View.OnClickListener mBottomSheetClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_sheet_add_list:
                    showDialogChoosePlan(v);
                    break;
                case R.id.btn_sheet_repeat:
                    showRepeatDialog();
                    break;
                case R.id.btn_sheet_remind:
                    showTimePickerDialog();
                    break;
                default:
                    break;
            }
        }
    };

    private void showRepeatDialog(){
        ChooseDayRepeat dayRepeat = new ChooseDayRepeat();
        dayRepeat.show(getSupportFragmentManager(), "D");
    }

    private void showDialogChoosePlan(View v){
        ChooseItemDialog dialog = new ChooseItemDialog();
        Bundle bundle = new Bundle();
        bundle.putFloat(ChooseItemDialog.POSITION_SHOW_DIALOG_X, v.getX());
        bundle.putFloat(ChooseItemDialog.POSITION_SHOW_DIALOG_Y, v.getY());
        dialog.setArguments(bundle);
        dialog.setController(mController);
        dialog.show(getSupportFragmentManager(), "Choose Plan");
    }


    private void showTimePickerDialog(){
        Calendar mcurrentTime = Calendar.getInstance();
        int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
        int minute = mcurrentTime.get(Calendar.MINUTE);
        /*TimePickerDialog mTimePicker;
        mTimePicker = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                Log.d("dong.nd1", selectedHour +" "+ selectedMinute);
            }
        }, hour, minute, true);//Yes 24 hour time
        mTimePicker.setTitle("Select Time");
        mTimePicker.show();*/

        ChooseDateTimeDialog dialog = new ChooseDateTimeDialog();
        dialog.setArguments(mController);
        dialog.show(getSupportFragmentManager(), "ChooseDateTime");
    }
}
