package com.example.os.crm.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.FrameLayout;

import com.chaychan.library.BottomBarItem;
import com.chaychan.library.BottomBarLayout;
import com.example.os.crm.R;
import com.example.os.crm.ui.fragment.ContactFragment;
import com.example.os.crm.ui.fragment.FirstFragment;
import com.example.os.crm.ui.fragment.FunctionFragment;
import com.example.os.crm.ui.fragment.MineFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.vp_content)
    FrameLayout vpContent;
    @BindView(R.id.bbl)
    BottomBarLayout bbl;

    private String TAG = "MainActivity";

    private List<Fragment> fragmentList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        ButterKnife.bind(this);
        initFragments();
    }

    private void initFragments(){
        fragmentList.add(new FirstFragment());
        fragmentList.add(new ContactFragment());
        fragmentList.add(new FunctionFragment());
        fragmentList.add(new MineFragment());
        bbl.setOnItemSelectedListener(new BottomBarLayout.OnItemSelectedListener() {
            @Override
            public void onItemSelected(BottomBarItem bottomBarItem, int i, int i1) {
                changeFragment(i1);
            }
        });
        changeFragment(0);
    }

    private void changeFragment(int currentPosition) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        switch (currentPosition){
            case 0:
                FirstFragment firstFragment = (FirstFragment) fragmentList.get(currentPosition);
                transaction.replace(R.id.vp_content, firstFragment);
//                firstFragment.initData();
                break;
            case 1:
                ContactFragment contactFragment = (ContactFragment) fragmentList.get(currentPosition);
                transaction.replace(R.id.vp_content, contactFragment);
//                contactFragment.initData();
                break;
            case 2:
                FunctionFragment functionFragment = (FunctionFragment) fragmentList.get(currentPosition);
                transaction.replace(R.id.vp_content, functionFragment);
//                functionFragment.initData();
                break;
            case 3:
                MineFragment mineFragment = (MineFragment) fragmentList.get(currentPosition);
                transaction.replace(R.id.vp_content, mineFragment);
//                mineFragment.initData();
                break;
        }
        transaction.commit();
    }

    @Override
    protected void onActivityResult(int reqCode, int resCode, Intent data){
        Log.d(TAG, "onActivityResult: " + reqCode);
    }
}
