package com.example.a81c_task;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button login, signUp;
    private EditText userName, userPassword;
    SQLiteManager sqLiteManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        login = (Button) findViewById(R.id.loginButton);
        signUp = (Button) findViewById(R.id.signUpButton);
        userName = (EditText) findViewById(R.id.userNameTextView);
        userPassword = (EditText) findViewById(R.id.userPasswordTextView);
        sqLiteManager = SQLiteManager.instanceOfDatabase(getApplicationContext());
        sqLiteManager.populateItemListArray();
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean userNameExist = false;
                boolean passwordExist = false;
                for (int i = 0; i < UserData.itemList.size(); i++)
                {
                    if (UserData.itemList.get(i).getUserName().equals(userName.getText().toString()))
                    {
                        userNameExist = true;
                    }
                    if (UserData.itemList.get(i).getUserPassword().equals(userPassword.getText().toString()))
                    {
                        passwordExist = true;
                    }
                }

                if (userNameExist && passwordExist)
                {
                    Intent intent = new Intent(MainActivity.this, AfterLoginActivity.class);
                    intent.putExtra("userName", userName.getText().toString());
                    intent.putExtra("password", userPassword.getText().toString());
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(MainActivity.this, "Invalid Credentials", Toast.LENGTH_LONG).show();
                }
            }
        });
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, registerActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        sqLiteManager.populateItemListArray();
    }
}