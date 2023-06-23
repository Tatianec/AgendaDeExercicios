package com.example.agenda_exercicio.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import androidx.recyclerview.widget.RecyclerView;
import com.example.agenda_exercicio.R;
import com.example.agenda_exercicio.mvp.MainMVP;
import com.example.agenda_exercicio.presenter.MainRepeticaoPresenter;

public class HistoricoActivityRepeticao extends AppCompatActivity implements MainMVP.View {

    private MainMVP.Presenter presenter;
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historico_repeticao);

        findById();
        presenter = new MainRepeticaoPresenter(this);

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

    private void findById() {
        mRecyclerView = findViewById(R.id.recyler_view);
    }

    @Override
    public Context getContext() {
        return this;
    }
}
