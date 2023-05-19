package com.example.healthhub;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


public class HealthArticlesDetailsActivity extends AppCompatActivity {

    TextView tv1;
    ImageView img;
    Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_articles_details);

        btnBack = findViewById(R.id.buttonHADBack);
        tv1 = findViewById(R.id.textViewHADTitle);
        img = findViewById(R.id.imageViewHAD);

        Intent intent = getIntent();
        tv1.setText(intent.getStringExtra("text1"));

        Bundle bundle = getIntent().getExtras();
        if (bundle != null)
        {
            int resID = bundle.getInt("text2");
            img.setImageResource(resID);
        }

        btnBack.setOnClickListener(view -> {
            finish();
            //NavController navController = Navigation.findNavController(HealthArticlesDetailsActivity.this, R.id.nav_host_fragment_activity_main); navController.navigate(R.id.navigation_health_articles);

        });
    }
}