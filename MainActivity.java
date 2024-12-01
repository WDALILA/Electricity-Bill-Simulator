package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    // Declare UI components
    EditText etUnits, etRebate;
    Button btnCalculate, btnAbout;
    TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Link UI components with XML
        etUnits = findViewById(R.id.et_units);
        etRebate = findViewById(R.id.et_rebate);
        btnCalculate = findViewById(R.id.btn_calculate);
        tvResult = findViewById(R.id.tv_result);
        btnAbout = findViewById(R.id.btn_about);

        // Set OnClickListener for the calculate button
        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateBill();
            }

            // Method to open the About page
            public void openAboutPage(View view) {
                Intent intent = new Intent(this, AboutActivity.class);
                startActivity(intent);
            }
        });


    }

    // Method to calculate the electricity bill
    private void calculateBill() {
        String unitsStr = etUnits.getText().toString();
        String rebateStr = etRebate.getText().toString();

        // Validate inputs
        if (unitsStr.isEmpty() || rebateStr.isEmpty()) {
            Toast.makeText(this, "Please enter both units and rebate.", Toast.LENGTH_SHORT).show();
            return;
        }

        int units = Integer.parseInt(unitsStr);
        double rebate = Double.parseDouble(rebateStr) / 100;

        double totalCharges = 0;

        // Calculate charges based on blocks
        if (units <= 200) {
            totalCharges = units * 0.218;
        } else if (units <= 300) {
            totalCharges = (200 * 0.218) + ((units - 200) * 0.334);
        } else if (units <= 600) {
            totalCharges = (200 * 0.218) + (100 * 0.334) + ((units - 300) * 0.516);
        } else {
            totalCharges = (200 * 0.218) + (100 * 0.334) + (300 * 0.516) + ((units - 600) * 0.546);
        }

        // Apply rebate
        double finalBill = totalCharges - (totalCharges * rebate);

        // Display the result
        tvResult.setText(String.format("Your bill is RM %.2f", finalBill));
    }


    }

