package com.example.samplesensorproviderapp;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class AccessSensorsActivity extends AppCompatActivity {

    private SensorManager sensorManager;
    private Sensor mLight, mTemperature;

    private TextView textViewLuminosity;
    private TextView textViewTemperature;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_access_sensors);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        textViewLuminosity = findViewById(R.id.textViewLuminosity);
        textViewTemperature = findViewById(R.id.textViewTemperature);

        // Verifique se o sensor de luminosidade está disponível
        if (sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT) != null) {
            mLight = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
            LightSensorAccess lightSensorAccess = new LightSensorAccess(sensorManager, textViewLuminosity);
        } else {
            textViewLuminosity.setText("Light sensor not available on this device");
        }

        // Verifique se o sensor de temperatura está disponível
        if (sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE) != null) {
            mTemperature = sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
            TemperatureSensorAccess temperatureSensorAccess = new TemperatureSensorAccess(sensorManager, textViewTemperature);
        } else {
            textViewTemperature.setText("Temperature sensor not available on this device");
        }
    }

}
