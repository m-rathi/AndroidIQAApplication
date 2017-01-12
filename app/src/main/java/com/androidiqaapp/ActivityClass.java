package com.androidiqaapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SwitchCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ActivityClass extends AppCompatActivity {
    TextView questionTxt, answerTxt;
    ListView lv;
    List<Map<String, Object>> questionData;
    JSONArray clientArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_class);

//        ActionBar actionBar = getSupportActionBar();
//        actionBar.setDisplayHomeAsUpEnabled(true);
//        actionBar.setHomeButtonEnabled(true);
//        actionBar.setHomeAsUpIndicator(R.drawable.btnblue);
        questionTxt = (TextView) findViewById(R.id.q1);
        answerTxt = (TextView) findViewById(R.id.a1);
        lv = (ListView) findViewById(R.id.listView);
        questionData = new ArrayList<Map<String, Object>>();
        getdata();
    }

    public void loadData() {
        lv = (ListView) findViewById(R.id.listView);
        String[] from = {"Question", "Answer"};
        // view id's to which data to be binded
        int[] to = {R.id.q1, R.id.a1};
        //Creating Adapter
        android.widget.SimpleAdapter k = new android.widget.SimpleAdapter(ActivityClass.this, questionData, R.layout.questionlist, from, to) {
            public View getView(int position, View convertView, ViewGroup parent) {
                View v = super.getView(position, convertView, parent);
                return v;
            }
        };
        lv.setAdapter(k);
    }

    public void lookupListData(JSONArray arrayData) {
        Map<String, Object> singleObj1;
        questionData = new ArrayList<Map<String, Object>>();
        SwitchCompat switchCompat;
        for (int i = 0; i < arrayData.length(); i++) {
            JSONObject obj = null;
            String Question = null;
            String Answer = null;
            try {
                obj = arrayData.getJSONObject(i);
                Question = obj.getString("Question");
                Answer = obj.getString("Answer");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            singleObj1 = new HashMap<String, Object>();
            singleObj1.put("Question", Question);
            singleObj1.put("Answer", Answer);
            questionData.add(singleObj1);
        }
    }

    public String getDataFromJsonFile() {

        String json = null;
        try {
            InputStream is = getAssets().open("generated.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    public void getdata() {
       try {
            clientArray = new JSONArray(getDataFromJsonFile());
            lookupListData(clientArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        loadData();
    }
}