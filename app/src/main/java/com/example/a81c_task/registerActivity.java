package com.example.a81c_task;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class registerActivity extends AppCompatActivity {
    private EditText fullName, userName, password, confirmPassword;
    private Button register;
    SQLiteManager sqLiteManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        fullName = (EditText) findViewById(R.id.fullNameTextView);
        userName = (EditText) findViewById(R.id.registerName);
        password = (EditText) findViewById(R.id.registerPassword);
        confirmPassword = (EditText) findViewById(R.id.registerConfirmPassword);
        register = (Button) findViewById(R.id.registerButton);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean userNameExist = false;
                boolean passwordMatched = false;
                for (int i = 0; i < UserData.itemList.size(); i++)
                {
                    if (UserData.itemList.get(i).getUserName().equals(userName.getText().toString()))
                    {
                        Toast.makeText(registerActivity.this, "User name already exists", Toast.LENGTH_SHORT).show();
                        userNameExist = true;
                        break;
                    }
                }

                if (password.getText().toString().equals(confirmPassword.getText().toString()))
                {
                    passwordMatched = true;
                }
                else
                {
                    Toast.makeText(registerActivity.this, "Password and Confirm Password does not match!", Toast.LENGTH_SHORT).show();
                }

                if (!userNameExist & passwordMatched)
                {
                    sqLiteManager = SQLiteManager.instanceOfDatabase(registerActivity.this);
                    UserData user = new UserData(userName.getText().toString(), password.getText().toString(), "None", UserData.itemList.size());
                    sqLiteManager.addItemToDatabase(user);
                    finish();
                }
            }
        });
    }
}