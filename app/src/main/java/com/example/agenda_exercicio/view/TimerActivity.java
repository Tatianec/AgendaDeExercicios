package com.example.agenda_exercicio.view;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.agenda_exercicio.R;

public class TimerActivity extends AppCompatActivity {

    private EditText minutesInput;
    private EditText secondsInput;
    private Button startButton;
    private TextView countdownText;
    private CountDownTimer countDownTimer;
    private boolean timerRunning;
    private long timeLeftInMillis;

    @SuppressLint( "MissingInflatedId" )
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);

        minutesInput = findViewById(R.id.minutesInput);
        secondsInput = findViewById(R.id.secondsInput);
        startButton = findViewById(R.id.startButton);
        countdownText = findViewById(R.id.countdownText);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (timerRunning) {
                    stopTimer();
                } else {
                    String minutesString = minutesInput.getText().toString().trim();
                    String secondsString = secondsInput.getText().toString().trim();

                    if (minutesString.isEmpty() && secondsString.isEmpty()) {
                        Toast.makeText(TimerActivity.this, "Digite os minutos e/ou segundos", Toast.LENGTH_SHORT).show();
                    } else {
                        int minutes = minutesString.isEmpty() ? 0 : Integer.parseInt(minutesString);
                        int seconds = secondsString.isEmpty() ? 0 : Integer.parseInt(secondsString);

                        int timeInMillis = (minutes * 60 + seconds) * 1000;
                        startTimer(timeInMillis);
                    }
                }
            }
        });
    }

    private void startTimer(long timeInMillis) {
        timeLeftInMillis = timeInMillis;

        countDownTimer = new CountDownTimer(timeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMillis = millisUntilFinished;
                updateCountdownText();
            }

            @Override
            public void onFinish() {
            }
        }.start();

        timerRunning = true;
        startButton.setText("Parar");
        minutesInput.setEnabled(false);
        secondsInput.setEnabled(false);
    }

    private void stopTimer() {
        countDownTimer.cancel();
        timerRunning = false;
        startButton.setText("Iniciar Timer");
        minutesInput.setEnabled(true);
        secondsInput.setEnabled(true);
        minutesInput.setText("");
        secondsInput.setText("");
        updateCountdownText();
    }

    private void updateCountdownText() {
        int minutes = (int) (timeLeftInMillis / 1000) / 60;
        int seconds = (int) (timeLeftInMillis / 1000) % 60;

        String timeLeftFormatted = String.format("%02d:%02d", minutes, seconds);
        countdownText.setText("Tempo Restante: " + timeLeftFormatted);
    }
}
