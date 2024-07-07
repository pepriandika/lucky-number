package com.pendka.luckynumber;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class SecondActivity extends AppCompatActivity {
    TextView welcomeTxt, luckyNumberTxt;
    Button share_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_second);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        welcomeTxt = findViewById(R.id.textView2);
        luckyNumberTxt = findViewById(R.id.lucky_number_text);
        share_btn = findViewById(R.id.share_btn);

        Intent intent = getIntent();
        String userName = intent.getStringExtra("name");

        int randomNum = generateRandomNumber();

        luckyNumberTxt.setText("" + randomNum);

        share_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareData(userName, randomNum);
            }
        });
    }

    public int generateRandomNumber(){
        Random random = new Random();
        int upper_limit = 1000;

        return random.nextInt(upper_limit);
    }

    public void shareData(String username, int randomNumber) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");

        intent.putExtra(Intent.EXTRA_SUBJECT, username + " got lucky today!");
        intent.putExtra(Intent.EXTRA_TEXT, "His Lucky Number is: " + randomNumber);

        startActivity(Intent.createChooser(intent,"Choose a Platform"));
    }
 }