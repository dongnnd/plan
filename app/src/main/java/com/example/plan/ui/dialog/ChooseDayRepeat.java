package com.example.plan.ui.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.example.plan.R;
import com.example.plan.databinding.ChooseDayRepeatBinding;
import com.example.plan.entities.Repeat;
import com.example.plan.presenter.MainController;

public class ChooseDayRepeat extends DialogFragment {
    private View mRoot;
    private Context mContext;
    private AlertDialog mDialog;
    public int[] mSelected = new int[8];
    private ChooseDayRepeatBinding mBinding;
    private MainController mController;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    public void setArguments(MainController controller) {
        mController = controller;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.choose_day_repeat, null, false);
        mBinding.setHandleClick(this);
        mDialog = new AlertDialog.Builder(mContext).setView(mBinding.getRoot())
                .setTitle(R.string.choose_day_repeat_title)
                .setPositiveButton(R.string.add_plan_btn_done, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        setDataForMainController();
                        dismiss();
                    }
                })
                .setNegativeButton(R.string.add_plan_btn_cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dismiss();
                    }
                })
                .create();
        mDialog.setCanceledOnTouchOutside(false);
        mSelected[0] = 1;
        mBinding.setArrSelected(mSelected);
        return mDialog;
    }

    public void onClick(int pos) {
        if (pos == 0) {
            mSelected[pos] = 1 - mSelected[pos];
            for (int i = 1; i < 8; i++) {
                mSelected[i] = 0;
            }
        } else {

            mSelected[pos] = 1 - mSelected[pos];
            mSelected[0] = checkAllNotSelect() ? 1 : 0;
        }
        mBinding.setArrSelected(mSelected);
    }

    private boolean checkAllNotSelect() {
        for (int i = 1; i < 8; i++) {
            if (mSelected[i] == 1) {
                return false;
            }
        }
        return true;
    }

    private void setDataForMainController() {
        Repeat repeat = new Repeat();
        if (mSelected[0] == 1) {
            repeat.setmNone(1);
            repeat.setmMon(0);
            repeat.setmTue(0);
            repeat.setmWed(0);
            repeat.setmThurs(0);
            repeat.setmFri(0);
            repeat.setSat(0);
            repeat.setSun(0);
        } else {
            for (int i = 0; i < 8; i++) {
                int value = mSelected[i];
                switch (i) {
                    case 0:
                        repeat.setmNone(value);
                        break;
                    case 1:
                        repeat.setmMon(value);
                        break;
                    case 2:
                        repeat.setmTue(value);
                        break;
                    case 3:
                        repeat.setmWed(value);
                        break;
                    case 4:
                        repeat.setmThurs(value);
                        break;
                    case 5:
                        repeat.setmFri(value);
                        break;
                    case 6:
                        repeat.setSat(value);
                        break;
                    case 7:
                        repeat.setSun(value);
                        break;
                }
            }
        }

        mController.getCurrentDataNewTask().setmRepeat(repeat);

    }

}
