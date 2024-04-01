package com.example.myfinances;
import android.content.ContentValues;
import android.net.Uri;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myfinances.contentprovider.FinancesContentProvider;

public class CDActivity extends AppCompatActivity {
    EditText accountNumberEditText;
    EditText initialBalanceEditText;
    EditText currentBalanceEditText;
    EditText interestRateEditText;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cd);

        accountNumberEditText = findViewById(R.id.accountNumberEditText);
        initialBalanceEditText = findViewById(R.id.initialBalanceEditText);
        currentBalanceEditText = findViewById(R.id.currentBalanceEditText);
        interestRateEditText = findViewById(R.id.interestRateEditText);
        Button submitCDButton = findViewById(R.id.submitCDButton);
        submitCDButton.setOnClickListener(new View.OnClickListener(){
            // account number, initial balance, current balance, and interest rate
            @Override
            public void onClick(View v) {
                String account_number = accountNumberEditText.getText().toString();
                String initial_balance = initialBalanceEditText.getText().toString();
                String current_balance = currentBalanceEditText.getText().toString();
                String interest_rate = interestRateEditText.getText().toString();

                ContentValues contentValues = new ContentValues();
                contentValues.put(FinancesContentProvider.ACCOUNT_NUMBER, account_number);
                contentValues.put(FinancesContentProvider.INITIAL_BALANCE, initial_balance);
                contentValues.put(FinancesContentProvider.CURRENT_BALANCE, current_balance);
                contentValues.put(FinancesContentProvider.INTEREST_RATE, interest_rate);
                Uri uri = getContentResolver().insert(FinancesContentProvider.CD_CONTENT_URI, contentValues);
                System.out.println(uri);
                clearInputFormFields();
                Toast.makeText(CDActivity.this, "CD Saved!", Toast.LENGTH_SHORT).show();
            }
        });

        Button buttonCancel = findViewById(R.id.cancelCDButton);
        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void clearInputFormFields() {
        accountNumberEditText.setText("");
        initialBalanceEditText.setText("");
        currentBalanceEditText.setText("");
        interestRateEditText.setText("");
    }
}
