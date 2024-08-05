package com.matinmollapur.turbocalc;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class SpeedConversionActivity extends AppCompatActivity {

    private EditText inputEditText;
    private Spinner fromSpinner, toSpinner;
    private TextView resultTextView;
    private Button convertButton;

    private final String[] speedUnits = {"Kilometer per Hour", "Meter per Second", "Mile per Hour"};
    private final double[] speedConversionFactors = {1.0, 3.6, 1.60934};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speed_conversion);

        inputEditText = findViewById(R.id.inputEditText);
        fromSpinner = findViewById(R.id.fromSpinner);
        toSpinner = findViewById(R.id.toSpinner);
        resultTextView = findViewById(R.id.resultTextView);
        convertButton = findViewById(R.id.convertButton);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, speedUnits);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        fromSpinner.setAdapter(adapter);
        toSpinner.setAdapter(adapter);

        convertButton.setOnClickListener(v -> convertSpeed());
    }

    private void convertSpeed() {
        double inputValue = Double.parseDouble(inputEditText.getText().toString());
        int fromPosition = fromSpinner.getSelectedItemPosition();
        int toPosition = toSpinner.getSelectedItemPosition();

        double fromFactor = speedConversionFactors[fromPosition];
        double toFactor = speedConversionFactors[toPosition];

        double resultValue = inputValue * (fromFactor / toFactor);
        resultTextView.setText(String.valueOf(resultValue));
    }
}
