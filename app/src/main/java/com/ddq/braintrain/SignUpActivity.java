package com.ddq.braintrain;

import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SignUpActivity extends AppCompatActivity {
    private RadioGroup radioGenderGroup;
    private RadioButton radioGenderButton;
    private EditText userName, password, dob, personal_id;

    BrainTrainDatabase brainTrainDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        userName = findViewById(R.id.userName);
        radioGenderGroup = (RadioGroup) findViewById(R.id.gender) ;
        password = findViewById(R.id.password);
        dob = findViewById(R.id.dob);
        personal_id = findViewById(R.id.personal_id);
        userName = findViewById(R.id.userName);
    }

    public void SignUp(View view) {
        int selectedId = radioGenderGroup.getCheckedRadioButtonId();
        radioGenderButton = (RadioButton) findViewById(selectedId);
        boolean isInserted = brainTrainDatabase.insertAccount(userName.getText().toString(), password.getText().toString(), radioGenderButton.getText().toString(), dob.getText().toString(), personal_id.getText().toString());
        if (isInserted == true){
            Toast.makeText(SignUpActivity.this, "Inserted", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(SignUpActivity.this, "Not Inserted", Toast.LENGTH_SHORT).show();
        }
    }

    public void Out(View view) {
        finish();
    }
}