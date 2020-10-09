package com.example.todolist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    DatabaseHelper db;
    EditText username_register;
    EditText email_register;
    EditText password_register;
    Button registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        db = new DatabaseHelper(this);
        username_register=findViewById(R.id.username_register);
        email_register=findViewById(R.id.email_register);
        password_register=findViewById(R.id.password_register);
        registerButton=findViewById(R.id.registerButton);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String user = username_register.getText().toString().trim();
                String password = password_register.getText().toString().trim();
                String email = email_register.getText().toString().trim();

               long val = db.addUser(user,password,email);
               if(val>0){
                   Toast.makeText(RegisterActivity.this,"Succefully Registered",Toast.LENGTH_SHORT).show();
                Intent moveToLoging = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(moveToLoging);
            }
               else{
                   Toast.makeText(RegisterActivity.this,"Registartion Error",Toast.LENGTH_SHORT).show();
               }

        }});


    }
}