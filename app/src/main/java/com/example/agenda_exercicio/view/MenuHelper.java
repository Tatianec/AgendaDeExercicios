package com.example.agenda_exercicio.view;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AlertDialog;

import com.example.agenda_exercicio.R;

public class MenuHelper {
    private Activity activity;

    public MenuHelper(Activity activity) {
        this.activity = activity;
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        activity.getMenuInflater().inflate(R.menu.menu_principal, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.menu_add_exercicio) {
            abrirActivityNewExercise();
            return true;
        } else if (id == R.id.menu_timer) {
            abrirActivityTimer();
            return true;
        } else if (id == R.id.menu_historico_tempo) {
            abrirHistoricoTempo();
            return true;
        } else if (id == R.id.menu_historico_repeticao) {
            abrirHistoricoRepeticao();
            return true;
        } else if (id == R.id.menu_relatorio) {
            abrirRelatorio();
            return true;
        } else if (id == R.id.menu_sair) {
            voltarHome();
            return true;
        }

        return false;
    }

    public void abrirActivityNewExercise() {
        Intent intent = new Intent(activity, NewExerciseActivity.class);
        activity.startActivity(intent);
    }

    public void abrirActivityTimer() {
        Intent intent = new Intent(activity, TimerActivity.class);
        activity.startActivity(intent);
    }

    public void abrirHistoricoTempo() {
        Intent intent2 = new Intent(activity, HistoricoActivityTempo.class);
        activity.startActivity(intent2);
    }

    public void abrirHistoricoRepeticao() {
        Intent intent1 = new Intent(activity, HistoricoActivityRepeticao.class);
        activity.startActivity(intent1);
    }

    public void abrirRelatorio() {
        Intent intent1 = new Intent(activity, RelatorioActivity.class);
        activity.startActivity(intent1);
    }

    public void voltarHome() {
        Intent intent1 = new Intent(activity, MainActivity.class);
        activity.startActivity(intent1);
    }

    public void abrirRegistros() {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle("Selecione uma opção")
                .setItems(new CharSequence[]{"Exercícios de Repetição", "Exercícios de Tempo"}, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                abrirHistoricoRepeticao();
                                break;
                            case 1:
                                abrirHistoricoTempo();
                                break;
                        }
                    }
                });
        builder.show();
    }
}
