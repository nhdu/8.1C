package com.example.a81c_task;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AfterLoginActivity extends AppCompatActivity {
    private EditText urlInput;
    private Button play, add, show;
    SQLiteManager sqLiteManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_login);
        urlInput = (EditText) findViewById(R.id.urlInput);
        play = (Button) findViewById(R.id.playButton);
        add = (Button) findViewById(R.id.addButton);
        show = (Button) findViewById(R.id.showPlayListButton);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
                String userName = intent.getStringExtra("userName");
                String userPassword = intent.getStringExtra("userPassword");
                UserData user = new UserData(userName, userPassword, urlInput.getText().toString(), UserData.itemList.size());
                sqLiteManager = SQLiteManager.instanceOfDatabase(AfterLoginActivity.this);
                sqLiteManager.addItemToDatabase(user);
                sqLiteManager.populateItemListArray();
            }
        });

        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AfterLoginActivity.this, ActivityRecyclerView.class);
                startActivity(intent);
            }
        });

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AfterLoginActivity.this, YouTubeActivity.class);
                intent.putExtra("video_code", urlInput.getText().toString());
                startActivity(intent);
            }
        });

    }
}