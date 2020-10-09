package com.example.todolist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    DatabaseHelper db;
    EditText username_editext_login;
    EditText password_editext_login;
    TextView register_textview;
    Button loginbutton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        db = new DatabaseHelper(this);
        username_editext_login = findViewById(R.id.username_editext_login);
        password_editext_login = findViewById(R.id.password_editext_login);
        register_textview = findViewById(R.id.register_textview);
        loginbutton = findViewById(R.id.login_button);

        register_textview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerIntent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(registerIntent);
            }
        });


        loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username_editext_login.getText().toString().trim();
                String password = password_editext_login.getText().toString().trim();
                Boolean res = db.checkUser(user,password);

                if(res==true){
                   Intent tabbedIntenet = new Intent(LoginActivity.this,TabbedActivity.class);
                   tabbedIntenet.putExtra("username",username_editext_login.getText().toString());
                   startActivity(tabbedIntenet);

                }
                else{
                    Toast.makeText(LoginActivity.this,"User not found",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}