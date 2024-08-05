package com.matinmollapur.turbocalc;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private static final char NO_ACTION = '0';

    private EditText editText;
    private TextView resultTextView;
    private double valueOne = Double.NaN;
    private double valueTwo;
    private char currentAction;
    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        editText = findViewById(R.id.editText);
        resultTextView = findViewById(R.id.resultTextView);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_settings:
                startActivity(new Intent(this, SettingsActivity.class));
                break;
            case R.id.nav_length_conversions:
                startActivity(new Intent(this, LengthConversionActivity.class));
                break;
            case R.id.nav_weight_conversions:
                startActivity(new Intent(this, WeightConversionActivity.class));
                break;
            case R.id.nav_temperature_conversions:
                startActivity(new Intent(this, TemperatureConversionActivity.class));
                break;
            case R.id.nav_volume_conversions:
                startActivity(new Intent(this, VolumeConversionActivity.class));
                break;
            case R.id.nav_area_conversions:
                startActivity(new Intent(this, AreaConversionActivity.class));
                break;
            case R.id.nav_speed_conversions:
                startActivity(new Intent(this, SpeedConversionActivity.class));
                break;
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void onNumberClick(View view) {
        Button button = (Button) view;
        editText.setText(editText.getText().toString() + button.getText().toString());
    }

    public void onOperatorClick(View view) {
        Button button = (Button) view;
        if (!Double.isNaN(valueOne)) {
            valueTwo = Double.parseDouble(editText.getText().toString());
            editText.setText(null);
            resultTextView.setText(String.format("%s%s%s", valueOne, currentAction, valueTwo));
            switch (currentAction) {
                case '+':
                    valueOne = valueOne + valueTwo;
                    break;
                case '-':
                    valueOne = valueOne - valueTwo;
                    break;
                case '*':
                    valueOne = valueOne * valueTwo;
                    break;
                case '/':
                    valueOne = valueOne / valueTwo;
                    break;
            }
        } else {
            valueOne = Double.parseDouble(editText.getText().toString());
        }

        currentAction = button.getText().toString().charAt(0);
        editText.setText(null);
    }

    public void onEqualClick(View view) {
        valueTwo = Double.parseDouble(editText.getText().toString());

        switch (currentAction) {
            case '+':
                valueOne = valueOne + valueTwo;
                break;
            case '-':
                valueOne = valueOne - valueTwo;
                break;
            case '*':
                valueOne = valueOne * valueTwo;
                break;
            case '/':
                valueOne = valueOne / valueTwo;
                break;
        }

        editText.setText(String.valueOf(valueOne));
        resultTextView.setText(null);
        valueOne = Double.NaN;
        currentAction = NO_ACTION;
    }

    public void onClearClick(View view) {
        valueOne = Double.NaN;
        valueTwo = Double.NaN;
        editText.setText("");
        resultTextView.setText("");
    }
}
