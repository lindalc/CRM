package com.example.os.crm.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.os.crm.R;
import com.example.os.crm.ui.fragment.Approval_ApplyFragment;
import com.example.os.crm.ui.fragment.Approval_WaitFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 待审批
 */
public class Approval_WaitActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tabs)
    TabLayout tabs;
    @BindView(R.id.appbar)
    AppBarLayout appbar;
    @BindView(R.id.viewpager)
    ViewPager viewpager;

    private int pageNo;

    public void startActivity(Context context){
        Intent intent = new Intent(context, Approval_WaitActivity.class);
        context.startActivity(intent);
    }

    public void startActivity(Context context, int page){
        Intent intent = new Intent(context, Approval_WaitActivity.class);
        intent.putExtra("page", page);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dai_shen_pi);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        this.pageNo = intent.getIntExtra("page", 0);
        initTabs();
    }

    private void initTabs() {
        List<String> titles = new ArrayList<>();
        titles.add("待我审批");
        titles.add("我的申请");
        tabs.addTab(tabs.newTab().setText(titles.get(0)));
        tabs.addTab(tabs.newTab().setText(titles.get(1)));
        List<android.support.v4.app.Fragment> fragments = new ArrayList<>();
        fragments.add(new Approval_WaitFragment());
        fragments.add(new Approval_ApplyFragment());
        FragmentAdapter fragmentAdapter = new FragmentAdapter(getSupportFragmentManager(), fragments, titles);
        viewpager.setAdapter(fragmentAdapter);
        viewpager.setCurrentItem(pageNo);
        tabs.setupWithViewPager(viewpager);
    }

    public class FragmentAdapter extends FragmentStatePagerAdapter{
        private List<Fragment> mFragments;
        private List<String> mTitles;

        public FragmentAdapter(FragmentManager fm, List<Fragment> fragments, List<String> titles){
            super(fm);
            mFragments = fragments;
            mTitles = titles;
        }

        @Override
        public Fragment getItem(int position){
            return mFragments.get(position);
        }

        @Override
        public int getCount(){
            return mFragments. size();
        }

        @Override
        public CharSequence getPageTitle(int position){
            return mTitles.get(position);
        }
    }

}
