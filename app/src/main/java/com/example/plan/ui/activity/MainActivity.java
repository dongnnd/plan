package com.example.plan.ui.activity;

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
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.plan.R;
import com.example.plan.databinding.ActivityMainBinding;
import com.example.plan.presenter.MainController;
import com.example.plan.ui.dialog.ChooseDayRepeat;
import com.example.plan.ui.dialog.ChoosePlanDialog;
import com.example.plan.ui.dialog.ChooseDateTimeDialog;
import com.example.plan.ui.fragment.FileListTaskFragment;
import com.example.plan.ui.fragment.NavigationFragment;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    public static final String CHOOSE_PLAN_DIALOG_TAG = "choose_plan";
    public static final String CHOOSE_DATE_TIME_TAG = "choose_date_time";
    public static final String CHOOSE_REPEAT_DAY_TAG = "choose_repeat_day";

    private MainController mController;
    private DrawerLayout mDrawerLayout;
    private Toolbar mToolbar;
    private FloatingActionButton mAddTask;
    private BottomSheetBehavior mAddListBehavior;
    private ActivityMainBinding mBinding;

    private Button mChoosePlan;
    private Button mChooseDateTime;
    private Button mChooseRepeat;
    private ImageView mSubmitTask;
    private EditText mNameNewTask;

    private FileListTaskFragment mFileListFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        initController();
        observerStateDrawer();
        showDefaultFragment();
        initDrawerLayout();
        initView();
        initBottomViewAddTask();
        handleActionFromBottomSheet();
        setSoftInputMode();

    }


    private void showDefaultFragment(){
        mFileListFragment = new FileListTaskFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.page_container, mFileListFragment).commit();

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
        mChoosePlan = findViewById(R.id.btn_sheet_add_list);
        mChoosePlan.setOnClickListener(mBottomSheetClick);
        mChooseDateTime = findViewById(R.id.btn_sheet_remind);
        mChooseDateTime.setOnClickListener(mBottomSheetClick);
        mChooseRepeat = findViewById(R.id.btn_sheet_repeat);
        mChooseRepeat.setOnClickListener(mBottomSheetClick);
        mSubmitTask = findViewById(R.id.btn_add_task);
        mSubmitTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mController.addNewTaskToDB();
            }
        });
        mNameNewTask = findViewById(R.id.edt_add_task);
        mNameNewTask.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.equals("") || s.length() == 0){
                    mSubmitTask.setImageResource(R.drawable.ic_add_task_disable);
                    mSubmitTask.setClickable(false);
                }else{
                    mSubmitTask.setImageResource(R.drawable.ic_add_task);
                    mSubmitTask.setClickable(true);
                }
                mController.getCurrentDataNewTask().setName(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        observerChoosePlan();
        observerChooseDateTime();
        observerChooseRepeat();
    }

    private void observerChoosePlan(){
        Observer<String> observer = new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                mChoosePlan.setText(s);
            }
        };
        mController.getmChoosePlan().observe(this, observer);
    }

    private void observerChooseDateTime(){
        Observer<String> observer = new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                mChooseDateTime.setText(s);
            }
        };
        mController.getmChooseDateTime().observe(this, observer);
    }

    private void observerChooseRepeat(){
        Observer<String> observer = new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                mChooseRepeat.setText(s);
            }
        };
        mController.getmChooseRepeat().observe(this, observer);
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
        fragment.setNavigationChange(mFileListFragment);
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
        dayRepeat.setArguments(mController);
        dayRepeat.show(getSupportFragmentManager(), CHOOSE_REPEAT_DAY_TAG);
    }

    private void showDialogChoosePlan(View v){
        ChoosePlanDialog dialog = new ChoosePlanDialog();
        Bundle bundle = new Bundle();
        bundle.putFloat(ChoosePlanDialog.POSITION_SHOW_DIALOG_X, v.getX());
        bundle.putFloat(ChoosePlanDialog.POSITION_SHOW_DIALOG_Y, v.getY());
        dialog.setArguments(bundle);
        dialog.setController(mController);
        dialog.show(getSupportFragmentManager(), CHOOSE_PLAN_DIALOG_TAG);
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
        dialog.show(getSupportFragmentManager(), CHOOSE_DATE_TIME_TAG);
    }
}
