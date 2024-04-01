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
    EditText loanAccountNumberEditText;
    EditText loanInitialBalanceEditText;
    EditText loanCurrentBalanceEditText;
    EditText loanPaymentAmtEditText;
    EditText loanInterestRateEditText;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loans);

        //account number, initial balance, current balance, payment amount, and interest rate
        loanAccountNumberEditText = findViewById(R.id.loanAccountNumberEditText);
        loanInitialBalanceEditText = findViewById(R.id.loanInitialBalanceEditText);
        loanCurrentBalanceEditText = findViewById(R.id.loanCurrentBalanceEditText);
        loanPaymentAmtEditText = findViewById(R.id.loanPaymentAmtEditText);
        loanInterestRateEditText = findViewById(R.id.loanInterestRateEditText);
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
                clearInputFormFields();
                Toast.makeText(LoanActivity.this, "Loan data is saved in the system!", Toast.LENGTH_SHORT).show();
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

    private void clearInputFormFields() {
        loanAccountNumberEditText.setText("");
        loanInitialBalanceEditText.setText("");
        loanCurrentBalanceEditText.setText("");
        loanPaymentAmtEditText.setText("");
        loanInterestRateEditText.setText("");
    }
}
