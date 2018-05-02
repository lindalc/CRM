package com.example.os.crm.Common;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.bigkoo.pickerview.TimePickerView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by OS on 2018/2/8.
 */

public class TimeUtil {

    Context context;

    public TimeUtil(Context context){
        this.context = context;
    }

    public TimeUtil(){

    }

    public TimePickerView getTimePicker(){
        //控制时间范围(如果不设置范围，则使用默认时间1900-2100年，此段代码可注释)
        //因为系统Calendar的月份是从0-11的,所以如果是调用Calendar的set方法来设置时间,月份的范围也要是从0-11
        Calendar selectedDate = Calendar.getInstance();
        TimePickerView timePickerView = new TimePickerView.Builder(context, new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调
                // 这里回调过来的v,就是show()方法里面所添加的 View 参数，如果show的时候没有添加参数，v则为null
                /*btn_Time.setText(getTime(date));*/
                Log.d("DatePicker", "onTimeSelect: " + date);
                String datetime = getTime(date);
                Log.d("DatePicker", "onTimeSelect: " + datetime);
                EditText editText = (EditText) v;
                editText.setText(datetime);
            }
        })
                //年月日时分秒 的显示与否，不设置则默认全部显示
                .setType(new boolean[]{true, true, true, false, false, false})
                .setLabel("年", "月", "日", "", "", "")
                .isCenterLabel(false)
                .setDividerColor(Color.DKGRAY)
                .setContentSize(21)
                .setDate(selectedDate)
                .setDecorView(null)
                .build();
        return timePickerView;
    }

    private String getTime(Date date) {//可根据需要自行截取数据显示
        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日", Locale.CHINA);
        return format.format(date);
    }

    public String getCurrentDate(){
        Date date = new Date();
        return getTime(date);
    }

    public String getYearAfterDate(){
        long timestamp = System.currentTimeMillis();
        long yearafter = timestamp + 31536000000L;
        Date date = new Date(yearafter);
        return getTime(date);
    }

    public String get7Daybefore(){
        long timestamp = System.currentTimeMillis();
        long yearafter = timestamp - 7*24*60*60*1000;
        Date date = new Date(yearafter);
        return getTime(date);
    }

    public String getDate(long timestamp){
        Date date = new Date(timestamp);
        return getTime(date);
    }

    public String getTimeStamp(String type, String time){
        SimpleDateFormat sdr = new SimpleDateFormat(type, Locale.CHINA);
        Date date;
        String times = null;
        try {
            date = sdr.parse(time);
            long l = date.getTime();
            String stf = String.valueOf(l);
            times = stf.substring(0, 10);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return times;
    }
}
