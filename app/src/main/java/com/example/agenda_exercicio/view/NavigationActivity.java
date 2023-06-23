package com.example.agenda_exercicio.view;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.agenda_exercicio.R;

public class NavigationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_principal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.menu_add_exercicio) {
            abrirActivityNewExercise(item.getActionView());
            return true;
        } else if (id == R.id.menu_timer) {
            abrirActivity2(item.getActionView());
            return true;
        } else if (id == R.id.menu_historico_tempo) {
            abrirHistoricoTempo();
            return true;
        }else if (id == R.id.menu_historico_repeticao) {
            abrirHistoricoRepeticao();
        return true;
    }


        return super.onOptionsItemSelected(item);
    }

    public void abrirActivityNewExercise(View view) {
        Intent intent = new Intent(this, NewExerciseActivity.class);
        startActivity(intent);
    }

    public void abrirActivity2(View view) {
        Intent intent = new Intent(this, NewExerciseActivity.class);
        startActivity(intent);
    }

    public void abrirHistoricoTempo(){
        Intent intent2 = new Intent(NavigationActivity.this, HistoricoActivityTempo.class);
        startActivity(intent2);
    }

    public void abrirHistoricoRepeticao(){
        Intent intent1 = new Intent(NavigationActivity.this, HistoricoActivityRepeticao.class);
        startActivity(intent1);
    }

    public void abrirRegistros() {
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
