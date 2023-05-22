package com.example.healthhub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;


public class LoginActivity extends AppCompatActivity {

    EditText edUsername, edPassword;
    Button btn;
    TextView tv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edUsername = findViewById(R.id.editTextUsername);
        edPassword = findViewById(R.id.editTextPassword);
        btn = findViewById(R.id.buttonLog);
        tv = findViewById(R.id.textViewRegNew);
        ImageView imageView = findViewById(R.id.imageView_gif);
        Glide.with(this).asGif().load(R.drawable.gif_logo).into(imageView);


        btn.setOnClickListener(view -> {

            String username = edUsername.getText().toString();
            String password = edPassword.getText().toString();
            Database db = new Database(getApplicationContext(),"health",null,1);
            if(username.length()==0 || password.length()==0){
                Toast.makeText(getApplicationContext(), "Please fill all Details", Toast.LENGTH_SHORT).show();
            }else{
                if (db.login(username,password)==1){
                    Toast.makeText(getApplicationContext(), "Login Success", Toast.LENGTH_SHORT).show();
                    SharedPreferences sharedPreferences = getSharedPreferences("shared prefs", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("username", username);
                    //to save our data with key and value
                    editor.apply();
                    startActivity(new Intent(LoginActivity.this,MainActivity.class));
                }else{
                    Toast.makeText(getApplicationContext(), "Invalid username and password", Toast.LENGTH_SHORT).show();
                }}

        });

        tv.setOnClickListener(view -> startActivity(new Intent(LoginActivity.this,RegisterActivity.class)));

    }
}