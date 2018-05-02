package com.example.os.crm.Dingdan.Adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.example.os.crm.Dingdan.Model.KeHuAutoDetail;
import com.example.os.crm.R;

public class KehuautodetailAdapter extends BaseAdapter implements Filterable {

    private ArrayFilter mFilter;
    private ArrayList<KeHuAutoDetail> mUnfilteredData;
    private List<KeHuAutoDetail> objects = new ArrayList<KeHuAutoDetail>();

    private Context context;
    private LayoutInflater layoutInflater;

    public KehuautodetailAdapter(Context context, List<KeHuAutoDetail> keHuAutoDetailList) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        this.objects = keHuAutoDetailList;
    }

    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public KeHuAutoDetail getItem(int position) {
        return objects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.kehuautodetail, null);
            convertView.setTag(new ViewHolder(convertView));
        }
        initializeViews((KeHuAutoDetail)getItem(position), (ViewHolder) convertView.getTag());
        return convertView;
    }

    private void initializeViews(KeHuAutoDetail object, ViewHolder holder) {
        holder.kehumkig.setText(object.getKehumingcheng());
        holder.kehudivi.setText(object.getKehulianxiren());
    }

    protected class ViewHolder {
        private TextView kehumkig;
        private TextView kehudivi;

        public ViewHolder(View view) {
            kehumkig = (TextView) view.findViewById(R.id.kehumkig);
            kehudivi = (TextView) view.findViewById(R.id.kehudivi);
        }
    }

    @Override
    public Filter getFilter() {
        if (mFilter == null) {
            mFilter = new ArrayFilter();
        }
        return mFilter;
    }

    private class ArrayFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence prefix) {
            FilterResults results = new FilterResults();
            if (mUnfilteredData == null) {
                mUnfilteredData = new ArrayList<KeHuAutoDetail>(objects);
            }
            if (prefix == null || prefix.length() == 0) {
                ArrayList<KeHuAutoDetail> list = mUnfilteredData;
                results.values = list;
                results.count = list.size();
            } else {
                String prefixString = prefix.toString().toLowerCase();
                ArrayList<KeHuAutoDetail> unfilteredValues = mUnfilteredData;
                int count = unfilteredValues.size();
                ArrayList<KeHuAutoDetail> newValues = new ArrayList<KeHuAutoDetail>(count);
                for (int i = 0; i < count; i++) {
                    KeHuAutoDetail pc = unfilteredValues.get(i);
                    if (pc != null) {
                        if(pc.getKehumingcheng()!=null && pc.getKehumingcheng().contains(prefixString)){
                            newValues.add(pc);
                        }else if(pc.getKehulianxiren()!=null && pc.getKehulianxiren().contains(prefixString)){
                            newValues.add(pc);
                        }
                    }
                }
                results.values = newValues;
                results.count = newValues.size();
            }
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            objects = (List<KeHuAutoDetail>) results.values;
            if (results.count > 0) {
                notifyDataSetChanged();
            } else {
                notifyDataSetInvalidated();
            }
        }
    }
}
