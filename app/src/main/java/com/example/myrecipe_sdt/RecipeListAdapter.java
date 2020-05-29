package com.example.myrecipe_sdt;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

class RecipeListAdapter extends BaseAdapter {

    ArrayList<RecipeGetterSetter> namelist;

    public RecipeListAdapter(ArrayList<RecipeGetterSetter> namelist) {
        this.namelist = namelist;
    }

    @Override
    public int getCount() {
        return namelist.size();
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

        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.homepage_model,parent,false);
        TextView name = convertView.findViewById(R.id.recipename_homepage);
        ImageView image = convertView.findViewById(R.id.imgviewmylist);
        name.setText(namelist.get(position).getRname());
        //image.setImageResource(namelist.get(position));

        return convertView;
    }
}
