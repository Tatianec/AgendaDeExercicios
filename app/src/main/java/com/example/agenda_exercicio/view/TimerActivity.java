package com.example.agenda_exercicio.view;

import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.MenuItem;
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
    private Button stopButton;
    private TextView countdownText;
    private CountDownTimer countDownTimer;
    private boolean timerRunning;
    private long timeLeftInMillis;

    private Ringtone ringtone;
    private MenuHelper menuHelper;

    private static final int NOTIFICATION_ID = 1;
    private static final String CHANNEL_ID = "channel_id";
    private static final CharSequence CHANNEL_NAME = "Channel Name";

    @SuppressLint( "MissingInflatedId" )
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);

        menuHelper = new MenuHelper(this);
        createNotificationChannel();

        findById();

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ( timerRunning ) {
                    stopTimer();
                    stopAlarm();
                } else {
                    String minutesString = minutesInput.getText().toString().trim();
                    String secondsString = secondsInput.getText().toString().trim();

                    if ( minutesString.isEmpty() && secondsString.isEmpty() ) {
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

        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopAlarm();
            }
        });

        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        ringtone = RingtoneManager.getRingtone(this, alarmSound);
    }

    private void findById() {
        minutesInput = findViewById(R.id.minutesInput);
        secondsInput = findViewById(R.id.secondsInput);
        startButton = findViewById(R.id.startButton);
        stopButton = findViewById(R.id.stopButton);
        countdownText = findViewById(R.id.countdownText);
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
                timerRunning = false;
                startButton.setText("Iniciar Timer");
                minutesInput.setEnabled(true);
                secondsInput.setEnabled(true);
                minutesInput.setText("");
                secondsInput.setText("");
                updateCountdownText();
                playAlarm();
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
        int minutes = ( int ) (timeLeftInMillis / 1000) / 60;
        int seconds = ( int ) (timeLeftInMillis / 1000) % 60;

        String timeLeftFormatted = String.format("%02d:%02d", minutes, seconds);
        countdownText.setText("Tempo Restante: " + timeLeftFormatted);
    }

    private void createNotificationChannel() {
        if ( Build.VERSION.SDK_INT >= Build.VERSION_CODES.O ) {
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH);
            channel.setDescription("Channel Description");
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }
    }

    private void playAlarm() {
        if ( ringtone != null ) {
            ringtone.play();
        }
    }

    private void stopAlarm() {
        if ( ringtone != null && ringtone.isPlaying() ) {
            ringtone.stop();
        }
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
