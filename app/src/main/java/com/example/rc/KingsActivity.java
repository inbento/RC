package com.example.rc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.activity.OnBackPressedCallback;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.example.rc.adapters.KingsAdapter;
import com.example.rc.models.King;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class KingsActivity extends AppCompatActivity implements KingsAdapter.OnKingSelectedListener {

    private Button btnBack;
    private RecyclerView rvKings;
    private KingsAdapter kingsAdapter;
    private List<King> kingsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kings);

        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goBackToMain();
            }
        });

        initViews();
        setupKingsData();
        setupRecyclerView();
        setupBackPressedHandler();
    }

    private void initViews() {
        rvKings = findViewById(R.id.rvKings);
    }

    private void setupKingsData() {
        kingsList = new ArrayList<>();

        kingsList.add(new King(
                R.drawable.king_of_man_bg,
                getString(R.string.king_man_name),
                getString(R.string.king_man_description),
                getString(R.string.faction_human)
        ));

        kingsList.add(new King(
                R.drawable.king_of_dragon_bg,
                getString(R.string.king_dragon_name),
                getString(R.string.king_dragon_description),
                getString(R.string.faction_dragon)
        ));

        kingsList.add(new King(
                R.drawable.king_of_elf_bg,
                getString(R.string.king_elf_name),
                getString(R.string.king_elf_description),
                getString(R.string.faction_elf)
        ));

        kingsList.add(new King(
                R.drawable.king_of_gnom_bg,
                getString(R.string.king_gnom_name),
                getString(R.string.king_gnom_description),
                getString(R.string.faction_gnome)
        ));
    }

    private void setupRecyclerView() {
        kingsAdapter = new KingsAdapter(kingsList, this);
        rvKings.setLayoutManager(new LinearLayoutManager(this));
        rvKings.setAdapter(kingsAdapter);

        // Добавляем декоратор для отступов (опционально)
        // rvKings.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
    }

    @Override
    public void onKingSelected(King king) {
        // Обрабатываем выбор короля
        String message = getString(R.string.king_selected, king.getName());
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();

        // Здесь можно добавить логику:
        // - Сохранение выбранного короля в SharedPreferences
        // - Запуск игры с выбранным королем
        // - Показ детальной информации о короле
    }

    // Для правильной работы языка
    @Override
    protected void attachBaseContext(Context newBase) {
        SharedPreferences preferences = newBase.getSharedPreferences("AppSettings", MODE_PRIVATE);
        String language = preferences.getString("language", "ru");
        Locale locale = new Locale(language);
        Locale.setDefault(locale);
        Resources resources = newBase.getResources();
        Configuration configuration = resources.getConfiguration();
        configuration.setLocale(locale);
        Context context = newBase.createConfigurationContext(configuration);
        super.attachBaseContext(context);
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