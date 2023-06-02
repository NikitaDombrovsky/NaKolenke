package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MAdapter extends ArrayAdapter<JSONObject>

    {
        int listLayout;
        int field;
        ArrayList<JSONObject> list;
        Context context;

    public MAdapter(Context context, int listLayout, int field , ArrayList<JSONObject> list) {
        super(context, listLayout, field, list);
        this.context = context;
        this.listLayout = listLayout;
        this.field = field;
        this.list = list;

    }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(listLayout,parent,false);
        TextView name = itemView.findViewById(R.id.list2_text1);
        TextView email = itemView.findViewById(R.id.list2_text2);
        try {
            name.setText(list.get(position).getString("name"));
            email.setText(list.get(position).getString("url"));
        }catch (JSONException e){
            e.printStackTrace();
        }
        return itemView;
    }

    }
