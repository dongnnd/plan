package com.example.plan.ui.activity;

import android.arch.lifecycle.Observer;
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
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.plan.R;
import com.example.plan.presenter.MainController;
import com.example.plan.ui.fragment.NavigationFragment;

public class MainActivity extends AppCompatActivity {

    private MainController mController;
    private DrawerLayout mDrawerLayout;
    private Toolbar mToolbar;
    private FloatingActionButton mAddTask;
    private BottomSheetBehavior mAddListBehavior;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initController();
        observerStateDrawer();
        initDrawerLayout();
        initView();
        initBottomViewAddTask();

    }

    private void observerStateDrawer(){
        mController.getStateDrawer().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean aBoolean) {
                if(aBoolean){
                    mDrawerLayout.closeDrawer(GravityCompat.START);
                }
            }
        });
    }

    private void initView(){
        //Floating button
        mAddTask = findViewById(R.id.fab);
        mAddTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showBottomAddTask();
            }
        });


    }

    private void initBottomViewAddTask(){
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
        mAddListBehavior.setHideable(true);

// set callback for changes
        mAddListBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                Log.d("dong.nd1", "onStateChanged");
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
                Log.d("dong.nd1", "on Slide");
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

    private void showBottomAddTask(){
        mAddListBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
    }
}
