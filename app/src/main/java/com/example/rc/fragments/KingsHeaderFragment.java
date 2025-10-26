package com.example.rc.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.fragment.app.Fragment;
import com.example.rc.R;

public class KingsHeaderFragment extends Fragment {

    private OnBackButtonClickListener backButtonListener;

    public interface OnBackButtonClickListener {
        void onBackButtonClicked();
    }

    public void setOnBackButtonClickListener(OnBackButtonClickListener listener) {
        this.backButtonListener = listener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_kings_header, container, false);

        Button btnBack = view.findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v -> {
            if (backButtonListener != null) {
                backButtonListener.onBackButtonClicked();
            }
        });

        return view;
    }
}