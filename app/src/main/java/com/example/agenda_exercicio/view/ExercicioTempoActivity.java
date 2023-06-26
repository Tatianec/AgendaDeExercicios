package com.example.agenda_exercicio.view;

import android.Manifest;
import android.app.DatePickerDialog;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.example.agenda_exercicio.R;
import com.example.agenda_exercicio.constant.Constants;
import com.example.agenda_exercicio.dao.LocationDao;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class ExercicioTempoActivity extends AppCompatActivity {
    private static final int PERMISSION_REQUEST_CODE = 1;
    private CheckBox checkBoxLocation;
    private EditText editTextDistance;
    private EditText editTextTime;
    private Button buttonSave;
    private Button buttonStartStop;
    private Button buttonSelectDate;

    private FirebaseFirestore db;
    private CollectionReference exerciciosCollection;

    private boolean isTracking = false;
    private LocationManager locationManager;

    private LocationDao locationDao;

    private Calendar calendar;
    private SimpleDateFormat dateFormat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_exercicio_tempo);

        db = FirebaseFirestore.getInstance();
        exerciciosCollection = db.collection(Constants.EXERCICIOS_COLLECTION);

        findById();

        checkBoxLocation.setOnCheckedChangeListener((buttonView, isChecked) -> {
            updateUI();
        });

        buttonSave.setOnClickListener(v -> {
            if (isTracking) {
                stopTracking();
            } else {
                String distanceText = editTextDistance.getText().toString();
                String timeText = editTextTime.getText().toString();

                if (distanceText.isEmpty() || timeText.isEmpty()) {
                    Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
                    return;
                }

                double distance = Double.parseDouble(distanceText);
                long time = Long.parseLong(timeText);

                saveData(distance, time);
            }
        });

        buttonStartStop.setOnClickListener(v -> {
            if (!isTracking) {
                startTracking();
            } else {
                stopTracking();
            }
        });

        buttonSelectDate.setOnClickListener(v -> {
            showDatePickerDialog();
        });

        updateUI();
        locationDao = new LocationDao();

        calendar = Calendar.getInstance();
        dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
    }

    private void findById() {
        checkBoxLocation = findViewById(R.id.checkBoxLocation);
        editTextDistance = findViewById(R.id.editTextDistance);
        editTextTime = findViewById(R.id.editTextTime);
        buttonSave = findViewById(R.id.buttonSave);
        buttonStartStop = findViewById(R.id.buttonStartStop);
        buttonSelectDate = findViewById(R.id.buttonSelectDate);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuHelper menuHelper = new MenuHelper(this);
        return menuHelper.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        MenuHelper menuHelper = new MenuHelper(this);
        return menuHelper.onOptionsItemSelected(item);
    }
    private void updateUI() {
        boolean isExternal = checkBoxLocation.isChecked();

        if (isExternal) {
            editTextDistance.setEnabled(false);
            editTextTime.setEnabled(false);
            buttonSave.setVisibility(View.VISIBLE);
            buttonStartStop.setVisibility(View.VISIBLE);

            buttonStartStop.setText("Começar");
        } else {
            editTextDistance.setEnabled(true);
            editTextTime.setEnabled(true);
            buttonSave.setVisibility(View.VISIBLE);
            buttonStartStop.setVisibility(View.GONE);

            buttonStartStop.setText("Começar/Parar");
        }
    }

    private void startTracking() {
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        if (locationManager != null) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                    ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, PERMISSION_REQUEST_CODE);
                return;
            }

            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationDao);
        }

        isTracking = true;
        buttonStartStop.setText("Parar");
    }

    private void stopTracking() {
        if (locationManager != null) {
            locationManager.removeUpdates(locationDao);
        }

        double distance = locationDao.getTotalDistance();
        long endTime = System.currentTimeMillis();
        long startTime = locationDao.getStartTime();
        long timeInSeconds = (endTime - startTime) / 1000;

        saveData(distance, timeInSeconds);

        locationDao.reset();
        isTracking = false;
        buttonStartStop.setText("Começar");

        editTextDistance.setText(String.valueOf(distance));
        editTextTime.setText(String.valueOf(timeInSeconds));
    }

    private void saveData(double distance, long time) {
        int roundedDistance = (int) Math.round(distance);

        String selectedDate = dateFormat.format(calendar.getTime());

        Map<String, Object> exerciseData = new HashMap<>();
        exerciseData.put("distancia", roundedDistance);
        exerciseData.put("tempo", time);
        exerciseData.put("data", selectedDate);

        exerciciosCollection.add(exerciseData)
                .addOnSuccessListener(documentReference -> {
                    Toast.makeText(ExercicioTempoActivity.this, "Dados salvos com sucesso!", Toast.LENGTH_SHORT).show();
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(ExercicioTempoActivity.this, "Erro ao salvar os dados", Toast.LENGTH_SHORT).show();
                });
    }

    private void showDatePickerDialog() {
        DatePickerDialog.OnDateSetListener dateSetListener = (view, year, month, dayOfMonth) -> {
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, month);
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

            buttonSelectDate.setText(dateFormat.format(calendar.getTime()));
        };

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                dateSetListener,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
        );

        datePickerDialog.show();
    }
}