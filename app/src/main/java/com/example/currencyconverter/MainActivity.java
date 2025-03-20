package com.example.currencyconverter;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    double fromValue = 0.0;
    double toValue = 0.0;
    String toUnit = "";
    String fromUnit = "";

    Spinner spinnerFromUnit;
    Spinner spinnerToUnit;
    EditText editTextFromValue;
    EditText editTextToValue;

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
        spinnerFromUnit = findViewById(R.id.fromUnit);
        spinnerToUnit = findViewById(R.id.toUnit);
        editTextFromValue = findViewById(R.id.fromValue);
        editTextToValue = findViewById(R.id.toValue);
    }

    public void onClick(View view) {
        try {
            fromUnit = spinnerFromUnit.getSelectedItem().toString();
            toUnit = spinnerToUnit.getSelectedItem().toString();
            fromValue = Double.parseDouble(editTextFromValue.getText().toString());
            convertValue();
        } catch (Exception e) {
            Toast.makeText(this, "Ошибка", Toast.LENGTH_SHORT).show();
        }
    }

    private void convertValue() {
        try {
            if (fromUnit.equalsIgnoreCase("RUB") && toUnit.equalsIgnoreCase("USD")) {
                toValue = fromValue / 83;
            } else if (fromUnit.equalsIgnoreCase("USD") && toUnit.equalsIgnoreCase("RUB")) {
                toValue = fromValue * 83;
            } else if (fromUnit.equalsIgnoreCase("RUB") && toUnit.equalsIgnoreCase("EUR")) {
                toValue = fromValue / 91;
            } else if (fromUnit.equalsIgnoreCase("EUR") && toUnit.equalsIgnoreCase("RUB")) {
                toValue = fromValue * 91;
            } else if (fromUnit.equalsIgnoreCase("USD") && toUnit.equalsIgnoreCase("EUR")) {
                toValue = fromValue / 1.1;
            } else if (fromUnit.equalsIgnoreCase("EUR") && toUnit.equalsIgnoreCase("USD")) {
                toValue = fromValue * 1.1;
            }
            else {
                Toast.makeText(this, "Одинаковая валюта", Toast.LENGTH_SHORT).show();
                return;
            }
            editTextToValue.setText(String.format("%.03f", toValue));
        } catch (Exception e) {
            Toast.makeText(this, "Ошибка", Toast.LENGTH_SHORT).show();
        }
    }
}