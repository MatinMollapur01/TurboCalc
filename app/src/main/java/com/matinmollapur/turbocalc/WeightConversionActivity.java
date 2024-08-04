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

public class WeightConversionActivity extends AppCompatActivity {

    private EditText inputEditText;
    private Spinner fromSpinner, toSpinner;
    private TextView resultTextView;
    private Button convertButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weight_conversion);

        inputEditText = findViewById(R.id.inputEditText);
        fromSpinner = findViewById(R.id.fromSpinner);
        toSpinner = findViewById(R.id.toSpinner);
        resultTextView = findViewById(R.id.resultTextView);
        convertButton = findViewById(R.id.convertButton);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.weight_units, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        fromSpinner.setAdapter(adapter);
        toSpinner.setAdapter(adapter);

        convertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                convertWeight();
            }
        });
    }

    private void convertWeight() {
        String input = inputEditText.getText().toString();
        if (input.isEmpty()) {
            resultTextView.setText(R.string.error_message);
            return;
        }

        double inputValue = Double.parseDouble(input);
        String fromUnit = fromSpinner.getSelectedItem().toString();
        String toUnit = toSpinner.getSelectedItem().toString();

        double conversionFactor = getConversionFactor(fromUnit, toUnit);
        double resultValue = inputValue * conversionFactor;

        resultTextView.setText(String.valueOf(resultValue));
    }

    private double getConversionFactor(String fromUnit, String toUnit) {
        switch (fromUnit) {
            case "Kilogram":
                if (toUnit.equals("Gram")) {
                    return 1000.0;
                } else if (toUnit.equals("Milligram")) {
                    return 1e6;
                } else if (toUnit.equals("Pound")) {
                    return 2.20462;
                } else if (toUnit.equals("Ounce")) {
                    return 35.274;
                }
                break;
            case "Gram":
                if (toUnit.equals("Kilogram")) {
                    return 0.001;
                } else if (toUnit.equals("Milligram")) {
                    return 1000.0;
                } else if (toUnit.equals("Pound")) {
                    return 0.00220462;
                } else if (toUnit.equals("Ounce")) {
                    return 0.035274;
                }
                break;
            case "Milligram":
                if (toUnit.equals("Kilogram")) {
                    return 1e-6;
                } else if (toUnit.equals("Gram")) {
                    return 0.001;
                } else if (toUnit.equals("Pound")) {
                    return 2.20462e-6;
                } else if (toUnit.equals("Ounce")) {
                    return 3.5274e-5;
                }
                break;
            case "Pound":
                if (toUnit.equals("Kilogram")) {
                    return 0.453592;
                } else if (toUnit.equals("Gram")) {
                    return 453.592;
                } else if (toUnit.equals("Milligram")) {
                    return 453592.0;
                } else if (toUnit.equals("Ounce")) {
                    return 16.0;
                }
                break;
            case "Ounce":
                if (toUnit.equals("Kilogram")) {
                    return 0.0283495;
                } else if (toUnit.equals("Gram")) {
                    return 28.3495;
                } else if (toUnit.equals("Milligram")) {
                    return 28349.5;
                } else if (toUnit.equals("Pound")) {
                    return 0.0625;
                }
                break;
        }
        return 1.0; // If the fromUnit and toUnit are the same
    }
}
