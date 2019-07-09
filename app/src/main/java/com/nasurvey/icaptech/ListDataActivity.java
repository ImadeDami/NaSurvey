package com.nasurvey.icaptech;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ListDataActivity extends AppCompatActivity {
    private static final String TAG = "ListDataActivity";
    ListView listView;
    DatabaseHelper mDataBaseHelper;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        listView = findViewById(R.id.listview);
        mDataBaseHelper = new DatabaseHelper(this);

        populateListView();

    }

    private void populateListView() {
        Log.d(TAG, "populateListView: Displaying data in the ListView.");

// get all data and append to list
        Cursor data = mDataBaseHelper.getData();
        ArrayList<String> listData = new ArrayList<>();
        while (data.moveToNext()){
            listData.add(data.getString(1));
        }

        //create the list adapter and set adapter
        ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,listData);
        listView.setAdapter(adapter);
    }
}
