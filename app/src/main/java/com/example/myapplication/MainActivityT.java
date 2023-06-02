package com.example.myapplication;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.myapplication.adapters.CatalogAdapterT;
import com.example.myapplication.models.Catalog;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivityT extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<Object> viewItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_t);
        recyclerView=(RecyclerView) findViewById(R.id.list);

        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));

        recyclerView.setAdapter(new CatalogAdapterT(this, viewItems));

        addItemsFromJSON();

    }
    private void addItemsFromJSON() {
        try {
            JSONObject obj=new JSONObject(loadJSONFromAsset("catalog.json"));
            // Получим JSONArray результатов
            JSONArray jsonArray=obj.getJSONArray("results");
            for (int i=0; i<jsonArray.length(); ++i) {

                JSONObject itemObj = jsonArray.getJSONObject(i);

                String id = itemObj.getString("id");
                String category = itemObj.getString("category");
                String name = itemObj.getString("name");
                String description = itemObj.getString("description");
                String price = itemObj.getString("price");
                String time_result = itemObj.getString("time_result");
                String preparation = itemObj.getString("preparation");
                String bio = itemObj.getString("bio");

                Catalog holidays = new Catalog(id,category,name,description, price, time_result, preparation, bio);
                viewItems.add(holidays);
            }

        } catch (JSONException e) {
        }
    }
    // Разбираем JSON-файл
    public String loadJSONFromAsset(String path) {
        String json=null;
        try {
            InputStream is=getAssets().open(path);
            int size=is.available();
            byte[] buffer=new byte[size];
            is.read(buffer);
            is.close();
            json=new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
}