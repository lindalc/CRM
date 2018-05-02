package com.example.os.crm.ui.widget;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.callback.ItemDragAndSwipeCallback;
import com.chad.library.adapter.base.listener.OnItemDragListener;
import com.example.os.crm.Contact.Model.ContactBean;
import com.example.os.crm.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by OS on 2018/3/2.
 */

public class ChooseContact extends LinearLayout {

    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.title)
    TextView title;

    private View footer;

    private List<ContactBean> contactBeanList = new ArrayList<>();
    private ChooseContactAdapter chooseContactAdapter = new ChooseContactAdapter(R.layout.choose_contact_item, contactBeanList);

    public ChooseContact(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.choose_contact, this);
        ButterKnife.bind(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerview.setLayoutManager(linearLayoutManager);
        addFooter();
        initRecyler();
    }

    private void initRecyler() {
        recyclerview.setAdapter(chooseContactAdapter);
        chooseContactAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                contactBeanList.remove(position);
                chooseContactAdapter.notifyDataSetChanged();
            }
        });
        chooseContactAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
        ItemDragAndSwipeCallback itemDragAndSwipeCallback = new ItemDragAndSwipeCallback(chooseContactAdapter);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(itemDragAndSwipeCallback);
        itemTouchHelper.attachToRecyclerView(recyclerview);

        chooseContactAdapter.enableDragItem(itemTouchHelper, R.id.circleimageview, true);
        chooseContactAdapter.setOnItemDragListener(new OnItemDragListener() {
            @Override
            public void onItemDragStart(RecyclerView.ViewHolder viewHolder, int pos) {

            }

            @Override
            public void onItemDragMoving(RecyclerView.ViewHolder source, int from, RecyclerView.ViewHolder target, int to) {

            }

            @Override
            public void onItemDragEnd(RecyclerView.ViewHolder viewHolder, int pos) {

            }
        });
    }

    public void setTitle(String title) {
        this.title.setText(title);
    }

    public void addData(ContactBean contactBean) {
        contactBeanList.add(contactBean);
        chooseContactAdapter.notifyDataSetChanged();
    }

    public void addData(List<ContactBean> contactBeans) {
        contactBeanList.addAll(contactBeans);
        chooseContactAdapter.notifyDataSetChanged();
    }

    private void addFooter() {
        footer = LayoutInflater.from(getContext()).inflate(R.layout.add_new_footer,
                (ViewGroup) recyclerview.getParent(),
                false);
        chooseContactAdapter.addFooterView(footer);
        chooseContactAdapter.notifyDataSetChanged();
    }

    public void setFootClick(OnClickListener onClickListener) {
        footer.setOnClickListener(onClickListener);
    }

    public String getUfPiRf() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");
        for (ContactBean contactBean : contactBeanList) {
            stringBuilder.append("{\"ufpirf\":")
                    .append("\"")
                    .append(contactBean.getUserid())
                    .append("\"")
                    .append(",\"xkmk\":")
                    .append("\"")
                    .append(contactBean.getJibf().getXkmk())
                    .append("\"")
                    .append(",\"vltd\":0,\"bwvu\":\"\", \"uijm\":\"\"},");
        }
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    public String getUserId(){
        if (contactBeanList.size() > 0){
            StringBuilder builder = new StringBuilder();
            builder.append("[");
            for (ContactBean contactBean : contactBeanList){
                builder.append("\"");
                builder.append(contactBean.getUserid());
                builder.append("\",");
            }
            builder.deleteCharAt(builder.length() -1);
            builder.append("]");
            return builder.toString();
        }
        else{
            return "[]";
        }
    }

    public int getCount(){
        return contactBeanList.size();
    }
}
