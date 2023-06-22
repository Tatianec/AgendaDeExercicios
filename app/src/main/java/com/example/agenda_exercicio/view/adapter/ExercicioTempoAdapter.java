package com.example.agenda_exercicio.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.agenda_exercicio.R;
import com.example.agenda_exercicio.model.ExercicioTempo;
import com.example.agenda_exercicio.view.ItemCliclListener;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class ExercicioTempoAdapter extends FirestoreRecyclerAdapter<ExercicioTempo, ExercicioTempoAdapter.ExercicioTempoViewHolder> {
    private ItemCliclListener clickListener;
    public ExercicioTempoAdapter(@NonNull FirestoreRecyclerOptions<ExercicioTempo> options) {
        super(options);
    }

    public void setClickListener(ItemCliclListener clickListener){
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public ExercicioTempoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.celula_exercicio_tempo, parent, false);
        return new ExercicioTempoViewHolder(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull ExercicioTempoViewHolder holder, int position, @NonNull ExercicioTempo model) {
        String distanciaString = String.valueOf(model.getDistancia());

        holder.textViewDistancia.setText(distanciaString);

    }


    static class ExercicioTempoViewHolder extends RecyclerView.ViewHolder {
        TextView textViewDistancia;

        public ExercicioTempoViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewDistancia = itemView.findViewById(R.id.textview_distancia);
        }
    }
}
