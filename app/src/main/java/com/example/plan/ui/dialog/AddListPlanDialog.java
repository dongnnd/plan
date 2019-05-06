package com.example.plan.ui.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.plan.R;
import com.example.plan.presenter.NavigationControler;
import com.example.plan.presenter.contant.AppConstants;
import com.example.plan.ui.injection.RepositoryFactory;
import com.example.plan.ui.storage.repository.ListPlanRepository;


public class AddListPlanDialog extends DialogFragment{

    private Context mContext;
    private AlertDialog mDialog;
    private EditText mEditText;
    private TextView mTextCancel;
    private TextView mTextError;
    private TextView mTextDone;
    private NavigationControler mController;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    public void setArguments(@Nullable NavigationControler controler) {
        mController = controler;
    }

    @Override
    public void onResume() {
        super.onResume();
        mEditText.postDelayed(new Runnable() {
            @Override
            public void run() {
                mEditText.requestFocus();
                InputMethodManager keyboard = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
                keyboard.showSoftInput(mEditText, InputMethodManager.SHOW_IMPLICIT);
            }
        }, 200);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.add_list_plan_dialog, null);
        mDialog = new AlertDialog.Builder(mContext).setView(view)
                .setTitle(R.string.add_plan_title)
                /*.setPositiveButton(R.string.add_plan_btn_done, new DialogInterface.OnClickListener() {
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
                })*/
                .create();
        mDialog.setCanceledOnTouchOutside(false);
        initView(view);
        return mDialog;
    }

    private void initView(View view){
        mTextError = view.findViewById(R.id.tv_add_plan_error);
        mEditText = view.findViewById(R.id.edt_add_plan_name);
        mEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(checkInputTextCorrectly() && checkInputTextDulicate()){
                    mTextDone.setEnabled(true);
                    mTextError.setVisibility(View.GONE);

                }else{
                    mTextDone.setEnabled(false);
                    mTextError.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        mTextCancel = view.findViewById(R.id.tv_add_plan_cancel);
        mTextCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        mTextDone = view.findViewById(R.id.tv_add_plan_done);
        mTextDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mController.addListPlan(mEditText.getText().toString());
                dismiss();
            }
        });
    }

    private boolean checkInputTextCorrectly(){
        String input = mEditText.getText().toString();
        if(input == null || input.isEmpty()){
            return false;
        }
        return true;
    }

    private boolean checkInputTextDulicate(){
        String input = mEditText.getText().toString();
        ListPlanRepository repository = (ListPlanRepository)RepositoryFactory.getRepositoryByType(mContext, AppConstants.DataType.LIST_PLAN);
        if (repository.checkDuplicatePlan(input)){
            return false;
        }
        return true;
    }
}
