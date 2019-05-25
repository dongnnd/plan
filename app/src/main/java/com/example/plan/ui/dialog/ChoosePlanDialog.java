package com.example.plan.ui.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.arch.lifecycle.Observer;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.example.plan.R;
import com.example.plan.presenter.MainController;
import com.example.plan.presenter.contant.AppConstants;
import com.example.plan.ui.fragment.ChoosePlanAdapter;
import com.example.plan.ui.storage.model.ListPlan;

import java.util.List;

import static android.view.ViewGroup.*;

public class ChoosePlanDialog extends DialogFragment implements IChooseItem<ListPlan> {

    public static final String POSITION_SHOW_DIALOG_X = "pos_x";
    public static final String POSITION_SHOW_DIALOG_Y = "pos_y";

    private Context mContext;
    private AlertDialog mDialog;
    private RecyclerView mRecyclerView;
    private ChoosePlanAdapter mAdapter;
    private MainController mController;
    private View mRoot;

    private float mPosShowX = -1;
    private float mPosShowY = -1;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public void onResume() {
        super.onResume();
        mDialog.getWindow().setLayout(500, WindowManager.LayoutParams.WRAP_CONTENT);
        observerDataChange();
    }

    public void setController(MainController controller){
        mController = controller;
    }

    @Override
    public void setArguments(@Nullable Bundle args) {
        super.setArguments(args);
        mPosShowX = args.getFloat(POSITION_SHOW_DIALOG_X);
        mPosShowY = args.getFloat(POSITION_SHOW_DIALOG_Y);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        mRoot = LayoutInflater.from(mContext).inflate(R.layout.choose_plan_dialog, null);
        mRecyclerView = mRoot.findViewById(R.id.choose_list_plan);
        mAdapter = new ChoosePlanAdapter(mContext);
        mAdapter.setClickCallBack(this);
        handleDefaultListClick();
        mRecyclerView.setAdapter(mAdapter);
        mController.loadListPlan();
        mDialog = new AlertDialog.Builder(mContext).setView(mRoot).create();
         if(mPosShowX != -1 && mPosShowY != -1){
             WindowManager.LayoutParams wmlp = mDialog.getWindow().getAttributes();
             wmlp.gravity = Gravity.BOTTOM | Gravity.LEFT;
             wmlp.width = WindowManager.LayoutParams.WRAP_CONTENT;
             wmlp.height = WindowManager.LayoutParams.WRAP_CONTENT;
             wmlp.x = (int)mPosShowX;   //x position
             wmlp.y = (int)mPosShowY;

             mDialog.getWindow().setAttributes(wmlp);
         }
         mDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));


        return mDialog;
    }

    private void handleDefaultListClick(){
        LinearLayout headerToday = mRoot.findViewById(R.id.choose_plan_default_today);
        headerToday.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClick(new ListPlan(AppConstants.DefaultListPlanID.PLAN_TODAY, mContext.getString(R.string.nav_today)));
            }
        });
        LinearLayout headerImportant = mRoot.findViewById(R.id.choose_plan_default_important);
        headerImportant.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClick(new ListPlan(AppConstants.DefaultListPlanID.PLAN_IMPORTANT, mContext.getString(R.string.nav_important)));
            }
        });
    }

    private void observerDataChange(){
        mController.getListPlan().observe(this, new Observer<List<ListPlan>>() {
            @Override
            public void onChanged(@Nullable List<ListPlan> listPlans) {
                mAdapter.updateData(listPlans);
            }
        });
    }

    @Override
    public void onItemClick(ListPlan item) {
        mController.getCurrentDataNewTask().setmPlan(item);
        dismiss();
    }
}
