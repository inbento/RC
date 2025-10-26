package com.example.rc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.os.Bundle;
import com.example.rc.fragments.KingsHeaderFragment;
import com.example.rc.fragments.KingsListFragment;

public class KingsActivity extends AppCompatActivity
        implements KingsHeaderFragment.OnBackButtonClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kings_container);

        KingsHeaderFragment headerFragment = new KingsHeaderFragment();
        headerFragment.setOnBackButtonClickListener(this);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        transaction.replace(R.id.headerContainer, headerFragment);
        transaction.replace(R.id.listContainer, new KingsListFragment());

        transaction.commit();
    }

    @Override
    public void onBackButtonClicked() {
        finish();
    }
}