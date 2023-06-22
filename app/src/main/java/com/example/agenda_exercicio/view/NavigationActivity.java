package com.example.agenda_exercicio.view;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.example.agenda_exercicio.R;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class NavigationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
    }

    public void abrirActivityNewExercise(View view) {
        Intent intent = new Intent(this, NewExerciseActivity.class);
        startActivity(intent);
    }

    public void abrirActivity2(View view) {
        Intent intent = new Intent(this, NewExerciseActivity.class);
        startActivity(intent);
    }

    public void abrirRegistros(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(NavigationActivity.this);
        builder.setTitle("Selecione uma opção")
                .setItems(new CharSequence[]{"Exercícios de Repetição", "Exercícios de Tempo"}, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                Intent intent1 = new Intent(NavigationActivity.this, HistoricoActivityRepeticao.class);
                                startActivity(intent1);
                                break;
                            case 1:
                                Intent intent2 = new Intent(NavigationActivity.this, HistoricoActivityTempo.class);
                                startActivity(intent2);
                                break;
                        }
                    }
                });
        builder.show();
    }

}
