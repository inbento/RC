package com.example.rc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.activity.OnBackPressedCallback;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button btnStart, btnKings, btnStats, btnExit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnStart = findViewById(R.id.btnStart);
        btnKings = findViewById(R.id.btnKings);
        btnStats = findViewById(R.id.btnStats);
        btnExit = findViewById(R.id.btnExit);

        setupButtonListeners();

        setupBackPressedHandler();
    }

    private void setupBackPressedHandler() {
        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                exitAppWithConfirmation();
            }
        });
    }

    private void setupButtonListeners() {
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startGame();
            }
        });

        btnKings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openKingsScreen();
            }
        });

        btnStats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showStatistics();
            }
        });

        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exitAppWithConfirmation();
            }
        });
    }

    private void startGame() {

    }

    private void openKingsScreen() {
        Intent intent = new Intent(MainActivity.this, KingsActivity.class);
        startActivity(intent);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    private void showStatistics() {
        Intent intent = new Intent(MainActivity.this, StatisticActivity.class);
        startActivity(intent);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    private void exitAppWithConfirmation() {

        finishAffinity();
        System.exit(0);
    }
}