package com.example.myfinances;
import android.content.ContentValues;
import android.net.Uri;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.EditText;

import android.view.View;
import android.widget.Button;
import com.example.myfinances.contentprovider.FinancesContentProvider;
import android.widget.Toast;

public class CheckingAccountActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checking_accounts);

        EditText chkAccountNumberEditText = findViewById(R.id.chkAccountNumberEditText);
        EditText chkCurrentBalanceEditText = findViewById(R.id.chkCurrentBalanceEditText);
        Button submitCheckingButton = findViewById(R.id.submitCheckingButton);
        submitCheckingButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String account_number = chkAccountNumberEditText.getText().toString();
                String current_balance = chkCurrentBalanceEditText.getText().toString();
                ContentValues contentValues = new ContentValues();
                contentValues.put(FinancesContentProvider.ACCOUNT_NUMBER, account_number);
                contentValues.put(FinancesContentProvider.CURRENT_BALANCE, current_balance);
                Uri uri = getContentResolver().insert(FinancesContentProvider.CHECKING_CONTENT_URI, contentValues);
                System.out.println(uri);
                Toast.makeText(CheckingAccountActivity.this, "Checking Account Saved!", Toast.LENGTH_SHORT).show();
            }
        });

        Button buttonCancel = findViewById(R.id.cancelCheckingButton);
        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
