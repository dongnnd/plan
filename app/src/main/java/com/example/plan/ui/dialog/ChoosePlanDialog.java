package com.example.plan.ui.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.example.plan.R;

import static android.view.ViewGroup.*;

public class ChoosePlanDialog extends DialogFragment {

    public static final String POSITION_SHOW_DIALOG_X = "pos_x";
    public static final String POSITION_SHOW_DIALOG_Y = "pos_y";

    private Context mContext;
    private AlertDialog mDialog;

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
        LayoutParams params = getDialog().getWindow().getAttributes();
        params.width = WindowManager.LayoutParams.WRAP_CONTENT;
        params.height = LayoutParams.WRAP_CONTENT;
        getDialog().getWindow().setLayout(500, WindowManager.LayoutParams.MATCH_PARENT);
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
        View view = LayoutInflater.from(mContext).inflate(R.layout.choose_plan_dialog, null);
        mDialog = new AlertDialog.Builder(mContext).setView(view).create();
         if(mPosShowX != -1 && mPosShowY != -1){
             WindowManager.LayoutParams wmlp = mDialog.getWindow().getAttributes();
             wmlp.gravity = Gravity.BOTTOM | Gravity.LEFT;
             wmlp.width = WindowManager.LayoutParams.WRAP_CONTENT;
             wmlp.height = WindowManager.LayoutParams.WRAP_CONTENT;
             wmlp.x = (int)mPosShowX;   //x position
             wmlp.y = (int)mPosShowY;

             Log.d("dong.nd1", mPosShowX + "");
             Log.d("dong.nd1", mPosShowY + "");
             mDialog.getWindow().setAttributes(wmlp);
         }
        return mDialog;
    }
}
