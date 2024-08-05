package com.matinmollapur.turbocalc;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class DataConversionActivity extends AppCompatActivity {

    private EditText inputEditText;
    private Spinner fromSpinner, toSpinner;
    private TextView resultTextView;
    private Button convertButton;

    private final String[] dataUnits = {"Byte", "Kilobyte", "Megabyte", "Gigabyte"};
    private final double[] dataConversionFactors = {1.0, 1024.0, 1024.0 * 1024.0, 1024.0 * 1024.0 * 1024.0};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_conversion);

        inputEditText = findViewById(R.id.inputEditText);
        fromSpinner = findViewById(R.id.fromSpinner);
        toSpinner = findViewById(R.id.toSpinner);
        resultTextView = findViewById(R.id.resultTextView);
        convertButton = findViewById(R.id.convertButton);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, dataUnits);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        fromSpinner.setAdapter(adapter);
        toSpinner.setAdapter(adapter);

        convertButton.setOnClickListener(v -> convertData());
    }

    private void convertData() {
        double inputValue = Double.parseDouble(inputEditText.getText().toString());
        int fromPosition = fromSpinner.getSelectedItemPosition();
        int toPosition = toSpinner.getSelectedItemPosition();

        double fromFactor = dataConversionFactors[fromPosition];
        double toFactor = dataConversionFactors[toPosition];

        double resultValue = inputValue * (fromFactor / toFactor);
        resultTextView.setText(String.valueOf(resultValue));
    }
}
