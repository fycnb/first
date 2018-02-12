package com.example.timebankapp.Convert;

import java.util.ArrayList;
import java.util.List;

import com.example.timebankapp.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ServiceNameListAdapter extends BaseAdapter {

    private List list = new ArrayList<>();
    private LayoutInflater inflater;

    public ServiceNameListAdapter(Context context){
        this.inflater = LayoutInflater.from(context);
    }

    public void setList(List list){
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = null;

        if(view == null){
            view = inflater.inflate(R.layout.servicenamelistview, null);
            holder = new ViewHolder();

            holder.name = (TextView) view.findViewById(R.id.servicename_TextView);


            view.setTag(holder);
        }else{
            holder = (ViewHolder) view.getTag();
        }

        holder.name.setText(list.get(i).toString());


        return view;
    }

    public class ViewHolder{

        TextView name;

    }

}
