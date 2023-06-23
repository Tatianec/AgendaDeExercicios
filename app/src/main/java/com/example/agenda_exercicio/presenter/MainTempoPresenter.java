package com.example.agenda_exercicio.presenter;

import android.util.Log;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.agenda_exercicio.constant.Constants;
import com.example.agenda_exercicio.model.ExercicioTempo;
import com.example.agenda_exercicio.mvp.MainMVP;
import com.example.agenda_exercicio.view.ItemCliclListener;
import com.example.agenda_exercicio.view.adapter.ExercicioTempoAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class MainTempoPresenter implements MainMVP.Presenter {
    private MainMVP.View view;
    private FirebaseFirestore database;
    private ExercicioTempoAdapter adapter;

    public MainTempoPresenter(MainMVP.View view) {
        this.view = view;
        database = FirebaseFirestore.getInstance();
    }

    @Override
    public void detach() {
        this.view = null;
    }

    public void populate(RecyclerView recyclerView) {
        Query query = database.collection(Constants.EXERCICIOS_COLLECTION).orderBy(Constants.ATTR_DATA);
        FirestoreRecyclerOptions<ExercicioTempo> options = new FirestoreRecyclerOptions.Builder<ExercicioTempo>()
                .setQuery(query, ExercicioTempo.class)
                .build();

        adapter = new ExercicioTempoAdapter(options);
        adapter.setClickListener(new ItemCliclListener() {
            @Override
            public void onClick(String referenceId) {

            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setAdapter(adapter);
    }

    public void startListener() {
        if (adapter != null) {
            adapter.startListening();
        }
    }

    public void stopListener() {
        if (adapter != null) {
            adapter.stopListening();
        }
    }
}
