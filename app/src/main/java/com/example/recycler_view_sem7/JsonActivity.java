package com.example.recycler_view_sem7;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;

public class JsonActivity extends AppCompatActivity {

    ListView lv;
    ArrayList<String> al;
    ArrayAdapter<String> ad;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json);

        lv = findViewById(R.id.lv);
        al = new ArrayList<>();
        ad = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,al);

        String s = "{\"student\":[{\"name\":\"Roshan\",\"mobno\":123456},{\"name\":\"Ram\",\"mobno\":111111},{\"name\":\"Shyam\",\"mobno\":22222}]}";






        try {
            JSONObject rootObject = new JSONObject(s);
            JSONArray jsonArray = rootObject.getJSONArray("student");

            for(int i=0; i<jsonArray.length();i++)
            {
                JSONObject stu = jsonArray.getJSONObject(i);
                String name = stu.getString("name");
                int mob = stu.getInt("mobno");
                String s1 = name + " "+mob;
                al.add(s1);
                lv.setAdapter(ad);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}

