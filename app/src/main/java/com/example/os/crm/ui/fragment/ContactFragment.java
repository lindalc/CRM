package com.example.os.crm.ui.fragment;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.os.crm.Contact.Fragments.ContactList;
import com.example.os.crm.Contact.Fragments.CustomerList;
import com.example.os.crm.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ContactFragment extends Fragment {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tabs)
    TabLayout tabs;
    @BindView(R.id.appbar)
    AppBarLayout appbar;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    Unbinder unbinder;

    private static final int TabPosition = 1;
    private boolean isfirst = true;
    private String TAG = "ContactFragment";
    List<Fragment> fragmentList = new ArrayList<>();

    public int getTabPosition(){
        return TabPosition;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tsxb, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        Log.d(TAG, "onDestroyView: ");
        super.onDestroyView();
        unbinder.unbind();

    }

    @Override
    public void onStart(){
        super.onStart();
        initFragments();
    }

    @Override
    public void onStop(){
        super.onStop();
        if (fragmentList != null) {
            fragmentList.clear();
            viewpager.clearOnPageChangeListeners();
        }
    }

    public void initData(){

    }

    private void initFragments(){
        List<String> titles = new ArrayList<>();
        titles.add("联系人");
        titles.add("客户");
        tabs.addTab(tabs.newTab().setText(titles.get(0)));
        tabs.addTab(tabs.newTab().setText(titles.get(1)));
        fragmentList.add(new ContactList());
        fragmentList.add(new CustomerList());

        final FragmentAdapter fragmentAdapter = new FragmentAdapter(getChildFragmentManager(), fragmentList, titles);
        viewpager.setAdapter(fragmentAdapter);
        tabs.setupWithViewPager(viewpager);
        viewpager.setCurrentItem(0);
        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        ((ContactList)fragmentAdapter.getItem(position)).initData();
                        break;
                    case 1:
                        ((CustomerList)fragmentAdapter.getItem(position)).initData();
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private class FragmentAdapter extends FragmentStatePagerAdapter {
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

        @Override
        public void restoreState(Parcelable state, ClassLoader loader) {
        }
    }
}
