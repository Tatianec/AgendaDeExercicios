package com.example.agenda_exercicio.presenter;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.agenda_exercicio.constant.Constants;
import com.example.agenda_exercicio.model.ExercicioRepeticao;
import com.example.agenda_exercicio.mvp.MainMVP;
import com.example.agenda_exercicio.view.ItemCliclListener;
import com.example.agenda_exercicio.view.adapter.ExercicioRepeticaoAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class MainRepeticaoPresenter implements MainMVP.Presenter {
    private MainMVP.View view;
    private final FirebaseFirestore database;
    private ExercicioRepeticaoAdapter adapter;

    public MainRepeticaoPresenter(MainMVP.View view) {
        this.view = view;
        database = FirebaseFirestore.getInstance();
    }

    @Override
    public void detach() {
        this.view = null;
    }


    @Override
    public void populate(RecyclerView recyclerView) {
        Query query = database.collection(Constants.EXERCICIOS_COLLECTION_REPETICAO).orderBy(Constants.ATTR_NOME);
        FirestoreRecyclerOptions<ExercicioRepeticao> options = new FirestoreRecyclerOptions.Builder<ExercicioRepeticao>()
                .setQuery(query, ExercicioRepeticao.class)
                .build();

        adapter = new ExercicioRepeticaoAdapter(options);
        adapter.setClickListener(new ItemCliclListener() {
            @Override
            public void onClick(String referenceId) {

            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setAdapter(adapter);
    }

    public void startListener() {
        if ( adapter != null ) {
            adapter.startListening();
        }
    }

    public void stopListener() {
        if ( adapter != null ) {
            adapter.stopListening();
        }
    }
}
