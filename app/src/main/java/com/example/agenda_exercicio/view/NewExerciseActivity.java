package com.example.agenda_exercicio.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.agenda_exercicio.R;

public class NewExerciseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_exercise);
    }

    public void novoExercicioRepeticao(View view) {
        Intent intent = new Intent(this, ExercicioRepeticaoActivity.class);
        startActivity(intent);
    }

    public void novoExercicioTempo(View view) {
        Intent intent = new Intent(this, ExercicioTempoActivity.class);
        startActivity(intent);
    }
}