package com.example.plan.ui.dialog;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.DialogFragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.plan.R;
import com.example.plan.presenter.MainController;
import com.example.plan.ui.fragment.ChooseDateFragment;
import com.example.plan.ui.fragment.ChooseTimeFragment;
import com.example.plan.ui.fragment.TabChooseDateTimeAdapter;

public class ChooseDateTimeDialog extends DialogFragment implements View.OnClickListener {

    private AlertDialog mDialog;
    private Context mContext;
    private TabChooseDateTimeAdapter mAdapter;
    private TabLayout mTablayout;
    private ViewPager mViewPager;
    private TextView tvDone;
    private TextView tvCancel;
    private MainController mController;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    public void setArguments(MainController controller) {
        mController = controller;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.choose_date_time_dialog, container, false);
        initView(view);
        return view;
    }

    /*@NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.choose_date_time_dialog, null);

        mDialog = new AlertDialog.Builder(mContext).setView(view)
                .setTitle(R.string.add_plan_title)
                *//*.setPositiveButton(R.string.add_plan_btn_done, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if(checkInputTextCorrectly()){
                            mController.addListPlan();
                        }else{
                            Toast.makeText(mContext, R.string.add_plan_error_string, Toast.LENGTH_LONG).show();
                        }

                    }
                })
                .setNegativeButton(R.string.add_plan_btn_cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dismiss();
                    }
                })*//*
                .create();

        initView(view);
        mDialog.setCanceledOnTouchOutside(false);
        return mDialog;
    }*/

    private void initView(View view){
        mTablayout = view.findViewById(R.id.tabLayout);
        mViewPager = view.findViewById(R.id.viewPager);
        mAdapter = new TabChooseDateTimeAdapter(getChildFragmentManager());
        ChooseTimeFragment timeDialog = new ChooseTimeFragment();
        timeDialog.setArguments(mController);
        ChooseDateFragment dateDialog = new ChooseDateFragment();
        dateDialog.setArguments(mController);
        mAdapter.addFragment(timeDialog, mContext.getString(R.string.choose_time_dialog_title));
        mAdapter.addFragment(dateDialog, mContext.getString(R.string.choose_date_dialog_title));
        mViewPager.setAdapter(mAdapter);
        mTablayout.setupWithViewPager(mViewPager);

        tvDone = view.findViewById(R.id.tv_done);
        tvDone.setOnClickListener(this);
        tvCancel = view.findViewById(R.id.tv_cancel);
        tvCancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_done:
                mController.addNewTaskToDB();
                break;
            case R.id.tv_cancel:
                dismiss();
                break;
        }
    }
}
