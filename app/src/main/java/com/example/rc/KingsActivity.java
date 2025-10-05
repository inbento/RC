package com.example.rc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.activity.OnBackPressedCallback;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.Toast;

public class KingsActivity extends AppCompatActivity {

    private Button btnBack;
    private ImageView king_of_man, king_of_dragon, king_of_elf, king_of_gnom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kings);

        btnBack = findViewById(R.id.btnBack);
        king_of_man = findViewById(R.id.king_of_man);
        king_of_dragon = findViewById(R.id.king_of_dragon);
        king_of_elf = findViewById(R.id.king_of_elf);
        king_of_gnom = findViewById(R.id.king_of_gnom);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goBackToMain();
            }
        });

        setupBackPressedHandler();

    }

    private void setupBackPressedHandler() {
        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                goBackToMain();
            }
        });
    }


    private void goBackToMain() {
        finish();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }
}