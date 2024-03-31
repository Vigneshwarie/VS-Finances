package com.example.myfinances;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.content.Intent;


public class MainActivity extends AppCompatActivity {
    RadioGroup radioGroup;
    Button buttonSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        radioGroup = findViewById(R.id.radioGroupFinanceOption);
        buttonSubmit = findViewById(R.id.btnSubmit);

        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedId = radioGroup.getCheckedRadioButtonId();
                RadioButton radioButton = findViewById(selectedId);

                if (radioButton != null) {
                    String selection = radioButton.getText().toString();

                    if(selection.equals("CD")){
                        Intent intent = new Intent(MainActivity.this, CDFragment.class);
                        intent.putExtra("selection", selection);
                        startActivity(intent);
                    } else if (selection.equals("Loans")) {
                        Intent intent = new Intent(MainActivity.this, LoanFragment.class);
                        intent.putExtra("selection", selection);
                        startActivity(intent);
                    }
                }
            }
        });

    }
}