package com.nasurvey.icaptech;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class InSaveActivity extends AppCompatActivity {
    DatabaseHelper mDatabaseHelper;
    Button btnAdd, btnView;
    EditText editText;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insave);

        editText = findViewById(R.id.editText);
        btnAdd = findViewById(R.id.btnAdd);
        btnView = findViewById(R.id.btnView);
        mDatabaseHelper = new DatabaseHelper(this);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newEntry = editText.getText().toString();
                if (editText.length() != 0) {
                    AddData(newEntry);
                    editText.setText("");
                } else {
                    Toast.makeText(InSaveActivity.this, "field vacant", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InSaveActivity.this, ListDataActivity.class);
                startActivity(intent);
            }
        });
    }

    public void AddData(String newEntry){
        boolean insertData = mDatabaseHelper.addData(newEntry);

        if(insertData){
            Toast.makeText(this, "data inserted...", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "something went wrong", Toast.LENGTH_SHORT).show();
        }
    }

   /** public void toastMessage(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();;
    } **/
}
