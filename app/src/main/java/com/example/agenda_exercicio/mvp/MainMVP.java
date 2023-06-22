package com.example.agenda_exercicio.mvp;

import android.content.Context;

import androidx.recyclerview.widget.RecyclerView;

public interface MainMVP {

    interface View {
        Context getContext();
    }

    interface Presenter {
        void detach();

        void populate(RecyclerView recyclerView);

        void startListener();

        void stopListener();

    }
}
