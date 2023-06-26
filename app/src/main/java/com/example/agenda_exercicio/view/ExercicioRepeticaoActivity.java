package com.example.agenda_exercicio.view;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.agenda_exercicio.R;
import com.example.agenda_exercicio.constant.Constants;
import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class ExercicioRepeticaoActivity extends AppCompatActivity {

    private EditText editTextNome;
    private EditText editTextRepeticoes;
    private EditText editTextPeso;
    private EditText editTextDescanso;
    private TextView textViewDataSelecionada;
    private Button buttonSelectDate;

    private FirebaseFirestore db;
    private CollectionReference exerciciosCollection;
    private MenuHelper menuHelper;

    public static final String TAG = "firebase";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercicio_repeticao);

        menuHelper = new MenuHelper(this);
        FirebaseApp.initializeApp(this);

        db = FirebaseFirestore.getInstance();
        exerciciosCollection = db.collection(Constants.EXERCICIOS_COLLECTION_REPETICAO);
        findById();

        buttonSelectDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePicker();
            }
        });

        Button buttonSalvar = findViewById(R.id.buttonSalvar);
        buttonSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nome = editTextNome.getText().toString();
                int repeticoes = Integer.parseInt(editTextRepeticoes.getText().toString());
                double peso = Double.parseDouble(editTextPeso.getText().toString());
                int descanso = Integer.parseInt(editTextDescanso.getText().toString());
                String dia = textViewDataSelecionada.getText().toString();

                saveExercise(nome, repeticoes, peso, descanso, dia);
            }
        });
    }

    private void findById() {
        editTextNome = findViewById(R.id.editTextNome);
        editTextRepeticoes = findViewById(R.id.editTextRepeticoes);
        editTextPeso = findViewById(R.id.editTextPeso);
        editTextDescanso = findViewById(R.id.editTextDescanso);
        textViewDataSelecionada = findViewById(R.id.textViewDataSelecionada);
        buttonSelectDate = findViewById(R.id.buttonSelectDate);
    }

    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        return menuHelper.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return menuHelper.onOptionsItemSelected(item);
    }

    private void showDatePicker() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                String dia = String.format(Locale.getDefault(), "%02d/%02d/%d", dayOfMonth, month, year);
                textViewDataSelecionada.setText(dia);
            }
        }, year, month, day);
        datePickerDialog.show();
    }

    private void saveExercise(String nome, int repeticoes, double peso, int descanso, String dia) {
        Map<String, Object> exerciseData = new HashMap<>();
        exerciseData.put("nome", nome);
        exerciseData.put("repeticoes", repeticoes);
        exerciseData.put("peso", peso);
        exerciseData.put("descanso", descanso);
        exerciseData.put("dia", dia);

        exerciciosCollection.add(exerciseData)
                .addOnSuccessListener(documentReference -> {
                    Toast.makeText(ExercicioRepeticaoActivity.this, "Dados salvos com sucesso!", Toast.LENGTH_SHORT).show();
                    limparCampos();
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(ExercicioRepeticaoActivity.this, "Erro ao salvar os dados", Toast.LENGTH_SHORT).show();
                });
    }

    private void limparCampos() {
        editTextNome.setText("");
        editTextRepeticoes.setText("");
        editTextPeso.setText("");
        editTextDescanso.setText("");
        textViewDataSelecionada.setText("");
    }
}
