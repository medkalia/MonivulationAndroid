package tn.esprit.legacy.monivulation.Activities;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

import tn.esprit.legacy.monivulation.Data.DataSuppliers.CycleDS;
import tn.esprit.legacy.monivulation.R;

public class CompleteRegisterActivity extends AppCompatActivity {

    private static final String MY_PREFS_NAME = "myPrefs" ;
    private SharedPreferences prefs;
    int userId;

    SeekBar avgPeriodSeek;
    Button confirmAvg;
    TextView avgValue;

    CardView cycleLengthCard;
    SeekBar cycleLengthSeek;
    Button confirmCycleLength;
    TextView cycleValue;

    CardView startDateCard;
    TextView startDate;
    Button confirmStartDate;

    private DatePickerDialog startDatePicker;
    Calendar calendar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complete_register);

        calendar = Calendar.getInstance();


        //view 1
        avgPeriodSeek = findViewById(R.id.avgPeriodSeek);
        confirmAvg = findViewById(R.id.confirmAvgPeriod);
        avgValue = findViewById(R.id.avgValue);

        //view 2
        cycleLengthCard = findViewById(R.id.cycleLengthCard);
        cycleLengthSeek = findViewById(R.id.cycleLengthSeek);
        confirmCycleLength = findViewById(R.id.confirmCycleLength);
        cycleValue = findViewById(R.id.cycleValue);

        //view 3
        startDateCard = findViewById(R.id.startDateCard);
        startDate = findViewById(R.id.startDatePicker);
        confirmStartDate = findViewById(R.id.confirmStartDate);

        setStartDatePicker(calendar);

        startDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startDatePicker.show();
            }
        });


        prefs = getSharedPreferences(MY_PREFS_NAME, Context.MODE_PRIVATE);
        Boolean loggedIn = prefs.getBoolean("loggedIn", false);
        if (loggedIn) {
            userId = prefs.getInt("userId", 0);
        }

        avgPeriodSeek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                avgValue.setText(Integer.toString(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        cycleLengthSeek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                cycleValue.setText(Integer.toString(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        confirmAvg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                cycleLengthCard.setVisibility(View.VISIBLE);

            }
        });

        confirmCycleLength.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                cycleLengthCard.setVisibility(View.GONE);
                startDateCard.setVisibility(View.VISIBLE);

            }
        });

        confirmStartDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CycleDS ds = new CycleDS();

                ds.firstCycle(String.valueOf(userId),  cycleValue.getText().toString(),  avgValue.getText().toString(), "02-05-2018", new CycleDS.CallbackAdd() {
                    @Override
                    public void onSuccess() {
                        Log.d("myLog","success");
                        Toast.makeText(CompleteRegisterActivity.this, "success", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onError() {
                        Log.d("myLog", "error");
                    }

                });

                Intent i1 = new Intent(CompleteRegisterActivity.this, MainActivity.class);
                startActivity(i1);
                finish();


            }
        });


    }

    private void setStartDatePicker(Calendar calendar){
        int year = calendar.get((Calendar.YEAR));
        int monthOfYear = calendar.get((Calendar.MONTH));
        int dayOfMonth = calendar.get((Calendar.DAY_OF_MONTH));

        startDatePicker = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                        setBirthdate(year, monthOfYear, dayOfMonth);
                    }
                }, year, monthOfYear, dayOfMonth);
        //calendar.add(Calendar.YEAR, -18);
        startDatePicker.getDatePicker().setMaxDate(calendar.getTime().getTime());

    }

    private void setBirthdate(int year, int monthOfYear, int dayOfMonth) {
        String day = year + "/" + monthOfYear + "/" + dayOfMonth;
        startDate.setText(day);
    }
}
