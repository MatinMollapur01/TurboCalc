package com.matinmollapur.turbocalc;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class VolumeConversionActivity extends AppCompatActivity {

    private EditText inputEditText;
    private Spinner fromSpinner, toSpinner;
    private TextView resultTextView;
    private Button convertButton;

    private String[] volumeUnits = {"Liter", "Milliliter", "Gallon", "Cup"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volume_conversion);

        inputEditText = findViewById(R.id.inputEditText);
        fromSpinner = findViewById(R.id.fromSpinner);
        toSpinner = findViewById(R.id.toSpinner);
        resultTextView = findViewById(R.id.resultTextView);
        convertButton = findViewById(R.id.convertButton);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, volumeUnits);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        fromSpinner.setAdapter(adapter);
        toSpinner.setAdapter(adapter);

        convertButton.setOnClickListener(view -> {
            String fromUnit = fromSpinner.getSelectedItem().toString();
            String toUnit = toSpinner.getSelectedItem().toString();
            String input = inputEditText.getText().toString();

            if (!input.isEmpty()) {
                double inputValue = Double.parseDouble(input);
                double result = convertVolume(inputValue, fromUnit, toUnit);
                resultTextView.setText(String.valueOf(result));
            } else {
                resultTextView.setText(R.string.error_message);
            }
        });
    }

    private double convertVolume(double value, String fromUnit, String toUnit) {
        double literValue = 0;

        switch (fromUnit) {
            case "Liter":
                literValue = value;
                break;
            case "Milliliter":
                literValue = value / 1000;
                break;
            case "Gallon":
                literValue = value * 3.78541;
                break;
            case "Cup":
                literValue = value * 0.24;
                break;
        }

        switch (toUnit) {
            case "Liter":
                return literValue;
            case "Milliliter":
                return literValue * 1000;
            case "Gallon":
                return literValue / 3.78541;
            case "Cup":
                return literValue / 0.24;
        }

        return 0;
    }
}
