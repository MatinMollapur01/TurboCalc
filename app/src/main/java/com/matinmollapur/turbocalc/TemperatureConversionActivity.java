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

public class TemperatureConversionActivity extends AppCompatActivity {

    private EditText inputEditText;
    private Spinner fromSpinner, toSpinner;
    private TextView resultTextView;
    private Button convertButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temperature_conversion);

        inputEditText = findViewById(R.id.inputEditText);
        fromSpinner = findViewById(R.id.fromSpinner);
        toSpinner = findViewById(R.id.toSpinner);
        resultTextView = findViewById(R.id.resultTextView);
        convertButton = findViewById(R.id.convertButton);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.temperature_units, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        fromSpinner.setAdapter(adapter);
        toSpinner.setAdapter(adapter);

        convertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                convertTemperature();
            }
        });
    }

    private void convertTemperature() {
        String input = inputEditText.getText().toString();
        if (input.isEmpty()) {
            resultTextView.setText(R.string.error_message);
            return;
        }

        double inputValue = Double.parseDouble(input);
        String fromUnit = fromSpinner.getSelectedItem().toString();
        String toUnit = toSpinner.getSelectedItem().toString();

        double resultValue = getConvertedTemperature(inputValue, fromUnit, toUnit);
        resultTextView.setText(String.valueOf(resultValue));
    }

    private double getConvertedTemperature(double value, String fromUnit, String toUnit) {
        if (fromUnit.equals(toUnit)) {
            return value;
        }

        double celsiusValue = value;
        if (fromUnit.equals("Fahrenheit")) {
            celsiusValue = (value - 32) * 5 / 9;
        } else if (fromUnit.equals("Kelvin")) {
            celsiusValue = value - 273.15;
        }

        if (toUnit.equals("Fahrenheit")) {
            return (celsiusValue * 9 / 5) + 32;
        } else if (toUnit.equals("Kelvin")) {
            return celsiusValue + 273.15;
        } else {
            return celsiusValue;
        }
    }
}
