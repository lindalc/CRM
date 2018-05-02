package com.example.os.crm.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.os.crm.R;

public class MineFragment extends Fragment {

    private static final int TabPosition = 3;
    private String TAG = "MainFragment";

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
        return inflater.inflate(R.layout.fragment_wode, container, false);
    }

    public void onStop(){
        super.onStop();
        Log.d(TAG, "onStop: ");
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    public void initData(){

    }

}
