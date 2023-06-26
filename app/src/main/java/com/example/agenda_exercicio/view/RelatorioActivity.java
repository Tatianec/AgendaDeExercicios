package com.example.agenda_exercicio.view;

import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.agenda_exercicio.R;
import com.example.agenda_exercicio.constant.Constants;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class RelatorioActivity extends AppCompatActivity {

    private BarChart barChart;
    private FirebaseFirestore db;
    private CollectionReference exerciciosCollection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relatorio);

        barChart = findViewById(R.id.barChart);
        db = FirebaseFirestore.getInstance();
        exerciciosCollection = db.collection(Constants.EXERCICIOS_COLLECTION_REPETICAO);

        fetchExerciseData();
    }

    private void fetchExerciseData() {
        exerciciosCollection.orderBy("dia").get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                QuerySnapshot querySnapshot = task.getResult();
                if (querySnapshot != null && !querySnapshot.isEmpty()) {
                    List<BarEntry> entries = new ArrayList<>();

                    for (DocumentSnapshot document : querySnapshot.getDocuments()) {
                        double peso = document.getDouble("peso");
                        String dia = document.getString("dia");

                        entries.add(new BarEntry(entries.size() + 1, (float) peso));
                    }

                    Collections.sort(entries, new Comparator<BarEntry>() {
                        @Override
                        public int compare(BarEntry entry1, BarEntry entry2) {
                            return Float.compare(entry1.getX(), entry2.getX());
                        }
                    });

                    BarDataSet dataSet = new BarDataSet(entries, "Dados de Pesos");
                    dataSet.setColor(Color.BLUE);

                    BarData barData = new BarData(dataSet);

                    barChart.setData(barData);
                    Description description = new Description();
                    description.setText("Relatório de Pesos");
                    barChart.setDescription(description);
                    barChart.notifyDataSetChanged();
                    barChart.invalidate();
                }
            }
        });
    }
}
