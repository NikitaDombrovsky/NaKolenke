package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;

import com.example.myapplication.adapters.CatalogAdapterT;
import com.example.myapplication.models.Catalog;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity {

    JSONArray array;
    private RecyclerView recyclerView;
    private List<Object> viewItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_t);

        new GetTask().execute(new JSONObject());
        recyclerView=(RecyclerView) findViewById(R.id.list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(new CatalogAdapterT(this, viewItems));

    }
    private class GetTask extends AsyncTask<JSONObject, Void, String> {
        @Override
        protected String doInBackground(JSONObject... jsonObjects) {
            try {
                InputStream stream = null;
                // Для буферизации текста из потока
                BufferedReader reader=null;
                HttpURLConnection connection = null;
                try {
                    // Присваиваем путь
                    URL url = new URL("http://10.0.2.2:8000/api/catalog/");
                    connection =(HttpURLConnection)url.openConnection();
                    // Выбираем метод GET для запроса
                    connection.setRequestMethod("GET");
                    connection.setReadTimeout(10000);
                    connection.connect();
                    // Полученный результат разбиваем с помощью байтовых потоков
                    stream = connection.getInputStream();
                    reader= new BufferedReader(new InputStreamReader(stream));
                    StringBuilder buf=new StringBuilder();
                    String line;
                    while ((line=reader.readLine()) != null) {
                        buf.append(line).append("\n");
                    }
                    // Возвращаем разбитый по строкам результат
                    JSONObject root = new JSONObject(buf.toString());
                    array= root.getJSONArray("results");
                    addItemsFromJSON();
                    return(buf.toString());

                } catch (Exception e) {
                    e.getMessage();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;

        }
    }
    private void addItemsFromJSON() {
        try {
            for (int i=0; i<array.length(); ++i) {
                JSONObject itemObj = array.getJSONObject(i);

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
}





