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

public class LoanActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loans);

        //account number, initial balance, current balance, payment amount, and interest rate

        EditText loanAccountNumberEditText = findViewById(R.id.loanAccountNumberEditText);
        EditText loanInitialBalanceEditText = findViewById(R.id.loanInitialBalanceEditText);
        EditText loanCurrentBalanceEditText = findViewById(R.id.loanCurrentBalanceEditText);
        EditText loanPaymentAmtEditText = findViewById(R.id.loanPaymentAmtEditText);
        EditText loanInterestRateEditText = findViewById(R.id.loanInterestRateEditText);
        Button submitLoanButton = findViewById(R.id.submitLoanButton);
        submitLoanButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String account_number = loanAccountNumberEditText.getText().toString();
                String initial_balance = loanInitialBalanceEditText.getText().toString();
                String current_balance = loanCurrentBalanceEditText.getText().toString();
                String payment_amount = loanPaymentAmtEditText.getText().toString();
                String interest_rate = loanInterestRateEditText.getText().toString();

                ContentValues contentValues = new ContentValues();
                contentValues.put(FinancesContentProvider.ACCOUNT_NUMBER, account_number);
                contentValues.put(FinancesContentProvider.INITIAL_BALANCE, initial_balance);
                contentValues.put(FinancesContentProvider.CURRENT_BALANCE, current_balance);
                contentValues.put(FinancesContentProvider.PAYMENT_AMOUNT, payment_amount);
                contentValues.put(FinancesContentProvider.INTEREST_RATE, interest_rate);
                Uri uri = getContentResolver().insert(FinancesContentProvider.LOAN_CONTENT_URI, contentValues);
                System.out.println(uri);
                Toast.makeText(LoanActivity.this, "Loan Saved!", Toast.LENGTH_SHORT).show();
            }
        });


        Button buttonCancel = findViewById(R.id.cancelLoanButton);
        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
