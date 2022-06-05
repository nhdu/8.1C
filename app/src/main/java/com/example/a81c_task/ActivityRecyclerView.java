package com.example.a81c_task;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class ActivityRecyclerView extends AppCompatActivity {
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_view_url);
        recyclerView = findViewById(R.id.recyclerViewView);
        loadItemList();
        setAdapter();

    }

    private void loadItemList() {
        SQLiteManager sqLiteManager = SQLiteManager.instanceOfDatabase(this);
        sqLiteManager.populateItemListArray();
    }

    private void setAdapter() {
        ArrayList<UserData> inputList = (ArrayList<UserData>) UserData.itemList.clone();
        for (int i=0; i < inputList.size(); i++)
        {
            if (inputList.get(i).getUserLink() == "None")
            {
                inputList.remove(i);
            }
        }

        RecyclerViewItemAdapter adapter = new RecyclerViewItemAdapter(inputList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadItemList();
        setAdapter();
    }
}