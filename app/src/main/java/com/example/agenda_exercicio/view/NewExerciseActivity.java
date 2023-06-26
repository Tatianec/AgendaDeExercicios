package com.example.agenda_exercicio.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.agenda_exercicio.R;

public class NewExerciseActivity extends AppCompatActivity {

    private MenuHelper menuHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_exercise);

        menuHelper = new MenuHelper(this);
    }

    public void novoExercicioRepeticao(View view) {
        Intent intent = new Intent(this, ExercicioRepeticaoActivity.class);
        startActivity(intent);
    }

    public void novoExercicioTempo(View view) {
        Intent intent = new Intent(this, ExercicioTempoActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        return menuHelper.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return menuHelper.onOptionsItemSelected(item);
    }
}