package com.example.plan.ui.fragment;

import android.arch.lifecycle.Observer;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.plan.R;
import com.example.plan.presenter.MainController;
import com.example.plan.presenter.NavigationControler;
import com.example.plan.ui.dialog.AddListPlanDialog;
import com.example.plan.ui.storage.AppDatabase;
import com.example.plan.ui.storage.model.ListPlan;

import java.util.List;

public class NavigationFragment extends Fragment implements ILoading, NavigationAdapter.IItemAction {

    private NavigationControler mController;
    private MainController mMainController;
    private NavigationAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private TextView mAddList;
    private INavigationChange mNavigationChange;

    public void setNavigationChange(INavigationChange change){
        mNavigationChange = change;
    }

    public void setMainController(MainController mainController){
        mMainController = mainController;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mController = new NavigationControler(getActivity().getApplication());
        observerDataChange();
        startLoading();
    }



    public void onRefresh(){
        startLoading();
    }

    @Override
    public void startLoading() {
        mController.loadNavigationItem();
    }

    private void observerDataChange(){
        mController.getListData().observe(this, new Observer<List<ListPlan>>() {
            @Override
            public void onChanged(@Nullable List<ListPlan> listPlans) {
                Log.d("dong.nd1", "change");
                mAdapter.updateDataChange(listPlans);
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.nav_fragment, container, false);
        AppDatabase appDatabase = AppDatabase.getInstance(getContext());
        appDatabase.getListPlanDao().getAllListPlan();
        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
    }

    private void initView(View view){
        mAddList = view.findViewById(R.id.textAddlist);
        mAddList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialogAddList();
            }
        });
        mAdapter = new NavigationAdapter(getContext(), this);
        mAdapter.setItemActionCallBack(this);
        mRecyclerView = view.findViewById(R.id.nav_recyclerview);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
    }

    private void showDialogAddList(){
        AddListPlanDialog dialog = new AddListPlanDialog();
        dialog.setArguments(mController);
        dialog.show(getFragmentManager(), "AddList");
    }

    public class SimpleDividerItemDecoration extends RecyclerView.ItemDecoration {
        private Drawable mDivider;

        public SimpleDividerItemDecoration(Context context) {
            mDivider = context.getResources().getDrawable(R.drawable.line_divider);
        }

        @Override
        public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
            int left = parent.getPaddingLeft();
            int right = parent.getWidth() - parent.getPaddingRight();

            int childCount = parent.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View child = parent.getChildAt(i);

                RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();

                int top = child.getBottom() + params.bottomMargin;
                int bottom = top + mDivider.getIntrinsicHeight();

                mDivider.setBounds(left, top, right, bottom);
                mDivider.draw(c);
            }
        }
    }

    @Override
    public void itemClick(ListPlan item) {
        mNavigationChange.onNavigationChange(item);
        mMainController.setValueStateDrawer(true);
    }

    @Override
    public void deleteItem(ListPlan listPlan) {
        mController.deletePlan(listPlan);
    }

}
