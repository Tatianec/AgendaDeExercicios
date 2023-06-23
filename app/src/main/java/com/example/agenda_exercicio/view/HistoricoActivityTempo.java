package com.example.agenda_exercicio.view;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.agenda_exercicio.R;
import com.example.agenda_exercicio.mvp.MainMVP;
import com.example.agenda_exercicio.presenter.MainTempoPresenter;

public class HistoricoActivityTempo extends AppCompatActivity
        implements MainMVP.View, View.OnClickListener {


    private MainMVP.Presenter presenter;

    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historico_tempo);

        findById();
        setListener();
        presenter = new MainTempoPresenter(this);


    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.populate(mRecyclerView);
        presenter.startListener();
    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.stopListener();
    }

    @Override
    protected void onDestroy() {
        presenter.detach();
        super.onDestroy();
    }

    private void setListener() {
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public Context getContext() {
        return this;
    }

    private void findById(){
        mRecyclerView = findViewById(R.id.recyler_view);
    }
}
