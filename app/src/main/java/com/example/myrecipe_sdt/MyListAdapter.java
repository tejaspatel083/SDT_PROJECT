package com.example.myrecipe_sdt;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

class MyListAdapter extends BaseAdapter {

    ArrayList<MyNamelist> myNamelistArrayList;

   

    @Override
    public int getCount() {
        return myNamelistArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.myrecipelist_model,parent,false);
        TextView text = convertView.findViewById(R.id.recipename_mylist);
        text.setText(myNamelistArrayList.get(position).getMyname());

        return convertView;
    }
}
