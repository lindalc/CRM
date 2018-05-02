package com.example.os.crm.Contact.Fragments;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.os.crm.Contact.Adapter.ContactListAdapter;
import com.example.os.crm.Contact.Activity.ContactDetail;
import com.example.os.crm.Contact.Model.ContactBean;
import com.example.os.crm.Contact.Model.Contact;
import com.example.os.crm.Contact.Listener.ContactListener;
import com.example.os.crm.R;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class ContactList extends Fragment {

    @BindView(R.id.search)
    EditText search;
    @BindView(R.id.contact_list)
    RecyclerView contactList;
    Unbinder unbinder;
    private String TAG = "ContactList";

    private static final int tabPosition = 0;
    private boolean isfirst = true;


    private Contact contact = Contact.getInstance();
    private List<ContactBean> contactBeanList = contact.getContactBeanList();
    private ContactListAdapter contactListAdapter = new ContactListAdapter(R.layout.contact_item, contactBeanList);


    private Handler handler = new Handler(){
        public void handleMessage(Message msg){
            switch (msg.what){
                case 0:
                    refreshUi();
                    break;
                default:
                    break;
            }
        }
    };

    public int getTabPosition(){
        return tabPosition;
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_contact_list, container, false);
        unbinder = ButterKnife.bind(this, view);
        contactList.setLayoutManager(new LinearLayoutManager(getActivity()));
        contactList.setAdapter(contactListAdapter);

        search.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {

                    //将输入法隐藏
                    InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(
                            Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(search.getWindowToken(), 0);
                    return true;
                }
                return false;
            }
        });

        if (isfirst){
            getData();
            initClick();
            isfirst = false;
        }
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(TAG, "onDestroyView: destroyed");
        unbinder.unbind();
    }

    private void initSearch(){
        //TODO:添加通讯录搜索功能
    }

    public void initData(){
        getData();
    }

    public void getData(){
        contactBeanList = contact.getContactBeanList();

        refreshUi();
    }

    private void refreshUi() {
        contactListAdapter.notifyDataSetChanged();
    }

    private void initClick() {
        if (contact.isReady()){
            Log.d(TAG, "initClick: data ready");
        }
        else{
            contact.setFinishListener(new ContactListener() {
                @Override
                public void onFinish() {
                    contactBeanList = contact.getContactBeanList();
                    Message msg = new Message();
                    msg.what = 0;
                    handler.sendMessage(msg);
                }
            });
        }

        contactListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Log.d(TAG, "onItemClick: " + position);
                ContactBean contactBean = contactBeanList.get(position);
                new ContactDetail().startActivity(getActivity(), contactBean);
            }
        });
        contactListAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                Log.d(TAG, "onItemChildClick: " + view.getId());
                Log.d(TAG, "onItemChildClick: " + position);
                ContactBean contactBean = contactBeanList.get(position);
                switch (view.getId()){
                    case R.id.new_call:
                        String tel = "tel:" + contactBean.getLmxi().getUzji();
                        Intent intent = new Intent(Intent.ACTION_DIAL);
                        intent.setData(Uri.parse(tel));
                        startActivity(intent);
                }
            }
        });
    }
}
