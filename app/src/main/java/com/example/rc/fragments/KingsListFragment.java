package com.example.rc.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.rc.R;
import com.example.rc.adapters.KingsAdapter;
import com.example.rc.models.King;
import java.util.ArrayList;
import java.util.List;

public class KingsListFragment extends Fragment implements KingsAdapter.OnKingSelectedListener {

    private RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_kings_list, container, false);
        recyclerView = view.findViewById(R.id.rvKings);
        setupRecyclerView();
        return view;
    }

    private void setupRecyclerView() {
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(layoutManager);

        List<King> kings = createSampleKings();

        KingsAdapter adapter = new KingsAdapter(kings, this);
        recyclerView.setAdapter(adapter);
    }

    private List<King> createSampleKings() {
        List<King> kings = new ArrayList<>();

        kings.add(new King(
                R.drawable.king_of_man_bg,
                getString(R.string.king_man_name),
                getString(R.string.king_man_description),
                R.string.faction_human
        ));

        kings.add(new King(
                R.drawable.king_of_dragon_bg,
                getString(R.string.king_dragon_name),
                getString(R.string.king_dragon_description),
                R.string.faction_dragon
        ));

        kings.add(new King(
                R.drawable.king_of_elf_bg,
                getString(R.string.king_elf_name),
                getString(R.string.king_elf_description),
                R.string.faction_elf
        ));

        kings.add(new King(
                R.drawable.king_of_gnom_bg,
                getString(R.string.king_gnom_name),
                getString(R.string.king_gnom_description),
                R.string.faction_gnome
        ));

        return kings;
    }

    @Override
    public void onKingSelected(King king) {
        if (getActivity() != null) {
            android.widget.Toast.makeText(
                    getActivity(),
                    "Выбран: " + king.getName(),
                    android.widget.Toast.LENGTH_SHORT
            ).show();
        }
    }
}