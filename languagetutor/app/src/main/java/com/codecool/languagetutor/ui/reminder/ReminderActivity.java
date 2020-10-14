package com.codecool.languagetutor.ui.reminder;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.codecool.languagetutor.databinding.ActivityReminderBinding;
import com.codecool.languagetutor.R;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class ReminderActivity extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener {

    private final String TIME_PICKER_TAG = "time_picker";
    private Calendar selectedTime;
    private ActivityReminderBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityReminderBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        setUpClickListeners();
    }

    private void setUpClickListeners() {
        binding.openTime.setOnClickListener(v -> {
            DialogFragment timePicker = new TimePicker();
            timePicker.show(getSupportFragmentManager(), TIME_PICKER_TAG);
        });

        binding.saveButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                if (selectedTime.after(GregorianCalendar.getInstance())) {
                    Toast.makeText(getApplicationContext(), R.string.reminder_saved_toast_msg, Toast.LENGTH_SHORT).show();
                    startAlarm();
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), R.string.reminder_past_time_msg, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void startAlarm() {
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, AlertReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 1, intent, 0);
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, selectedTime.getTimeInMillis(), pendingIntent);
    }

    @Override
    public void onTimeSet(android.widget.TimePicker view, int hourOfDay, int minute) {
        selectedTime = Calendar.getInstance();
        selectedTime.set(Calendar.HOUR_OF_DAY, hourOfDay);
        selectedTime.set(Calendar.MINUTE, minute);
        selectedTime.set(Calendar.SECOND, 0);
        String timeText = DateFormat.getTimeInstance(DateFormat.SHORT).format(selectedTime.getTime());
        binding.textViewTime.setText(timeText);
    }

}