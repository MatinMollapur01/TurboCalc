package com.matinmollapur.turbocalc;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class PressureConversionActivity extends AppCompatActivity {

    private EditText inputEditText;
    private Spinner fromSpinner, toSpinner;
    private TextView resultTextView;
    private Button convertButton;

    private final String[] pressureUnits = {"Pascal", "Bar", "Atmosphere"};
    private final double[] pressureConversionFactors = {1.0, 1e5, 101325.0};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pressure_conversion);

        inputEditText = findViewById(R.id.inputEditText);
        fromSpinner = findViewById(R.id.fromSpinner);
        toSpinner = findViewById(R.id.toSpinner);
        resultTextView = findViewById(R.id.resultTextView);
        convertButton = findViewById(R.id.convertButton);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, pressureUnits);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        fromSpinner.setAdapter(adapter);
        toSpinner.setAdapter(adapter);

        convertButton.setOnClickListener(v -> convertPressure());
    }

    private void convertPressure() {
        double inputValue = Double.parseDouble(inputEditText.getText().toString());
        int fromPosition = fromSpinner.getSelectedItemPosition();
        int toPosition = toSpinner.getSelectedItemPosition();

        double fromFactor = pressureConversionFactors[fromPosition];
        double toFactor = pressureConversionFactors[toPosition];

        double resultValue = inputValue * (fromFactor / toFactor);
        resultTextView.setText(String.valueOf(resultValue));
    }
}
