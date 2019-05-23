package com.example.plan.ui.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
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

public class ChooseDayRepeat extends DialogFragment {
    private View mRoot;
    private Context mContext;
    private AlertDialog mDialog;
    public int[] mSelected = new int[7];

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        ChooseDayRepeatBinding binding = DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.choose_day_repeat, null, false);
        mDialog = new AlertDialog.Builder(mContext).setView(binding.getRoot())
                .setTitle(R.string.choose_day_repeat_title)
                .setPositiveButton(R.string.add_plan_btn_done, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {


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
        binding.setArrSelected(mSelected);
        return mDialog;
    }

    public void onClick( int pos){
        Log.d("dong.nd1", "pos: " + pos);
    }

}
