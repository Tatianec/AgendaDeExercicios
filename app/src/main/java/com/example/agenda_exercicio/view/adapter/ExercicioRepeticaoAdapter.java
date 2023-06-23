package com.example.agenda_exercicio.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.agenda_exercicio.R;
import com.example.agenda_exercicio.model.ExercicioRepeticao;
import com.example.agenda_exercicio.view.ItemCliclListener;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class ExercicioRepeticaoAdapter extends FirestoreRecyclerAdapter<ExercicioRepeticao, ExercicioRepeticaoAdapter.ExercicioRepeticaoViewHolder> {
    private ItemCliclListener clickListener;

    public ExercicioRepeticaoAdapter(@NonNull FirestoreRecyclerOptions<ExercicioRepeticao> options) {
        super(options);
    }

    public void setClickListener(ItemCliclListener clickListener) {
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public ExercicioRepeticaoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.celula_exercicio_repeticao, parent, false);
        return new ExercicioRepeticaoViewHolder(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull ExercicioRepeticaoViewHolder holder, int position, @NonNull ExercicioRepeticao model) {
        String nome = model.getNome();
        int repeticoes = model.getRepeticoes();
        double peso = model.getPeso();
        int descanso = model.getDescanso();
        String dia = model.getDia();

        String exercicioInfo = nome + ", " + repeticoes + " repetições, " + peso + "kg, " + descanso + "s, " + dia;
        holder.textViewExercicioInfo.setText(exercicioInfo);
    }

    static class ExercicioRepeticaoViewHolder extends RecyclerView.ViewHolder {
        TextView textViewExercicioInfo;

        public ExercicioRepeticaoViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewExercicioInfo = itemView.findViewById(R.id.textview_nome);
        }
    }
}
