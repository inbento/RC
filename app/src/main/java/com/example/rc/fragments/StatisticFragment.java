package com.example.rc.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import com.example.rc.R;

public class StatisticFragment extends Fragment {

    private TextView statsText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_statistic, container, false);
        statsText = view.findViewById(R.id.statsText);
        loadStatistics();
        return view;
    }

    private void loadStatistics() {
        String statistics = "Статистика игры:\n\n" +
                "• Всего игр: 15\n" +
                "• Побед: 8\n" +
                "• Поражений: 5\n" +
                "• Ничьих: 2\n\n" +
                "Лучшая серия: 3 победы";

        statsText.setText(statistics);
    }
}