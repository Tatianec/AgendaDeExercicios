package com.example.agenda_exercicio.view;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.agenda_exercicio.R;

public class NavigationActivity extends AppCompatActivity {
    Button buttonRegistros, buttonRelatorio, buttonTimer, buttonNewExercicio;
    MenuHelper menuHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);

        menuHelper = new MenuHelper(this);

        findById();
        setClickListeners();
    }

    private void findById() {
        buttonRegistros = findViewById(R.id.butRegistros);
        buttonRelatorio = findViewById(R.id.butRelatorio);
        buttonTimer = findViewById(R.id.butTimer);
        buttonNewExercicio = findViewById(R.id.button1);
    }

    private void setClickListeners() {
        View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.butRegistros:
                        menuHelper.abrirRegistros();
                        break;
                    case R.id.butRelatorio:
                        menuHelper.abrirRelatorio();
                        break;
                    case R.id.butTimer:
                        menuHelper.abrirActivityTimer();
                        break;
                    case R.id.button1:
                        menuHelper.abrirActivityNewExercise();
                        break;
                }
            }
        };

        buttonRegistros.setOnClickListener(clickListener);
        buttonRelatorio.setOnClickListener(clickListener);
        buttonTimer.setOnClickListener(clickListener);
        buttonNewExercicio.setOnClickListener(clickListener);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return menuHelper.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return menuHelper.onOptionsItemSelected(item);
    }
}
