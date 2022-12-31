package com.ddq.braintrain;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SignUpActivity extends AppCompatActivity {
    private RadioGroup radioGenderGroup;
    private RadioButton radioGenderButton;
    private Button btnSignUp;
    private EditText userName, password, dob, personal_id;

    BrainTrainDatabase brainTrainDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        brainTrainDatabase = new BrainTrainDatabase(this);

        userName = findViewById(R.id.userName);
        radioGenderGroup = (RadioGroup) findViewById(R.id.gender) ;
        password = findViewById(R.id.password);
        dob = findViewById(R.id.dob);
        personal_id = findViewById(R.id.personal_id);
        btnSignUp = findViewById(R.id.btnSignUp);
        SignUp();

    }

    public void SignUp() {
        btnSignUp.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int selectedId = radioGenderGroup.getCheckedRadioButtonId();
                        radioGenderButton = (RadioButton) findViewById(selectedId);
                        String user = userName.getText().toString();
                        String pass = password.getText().toString();
                        String gen = radioGenderButton.getText().toString();
                        String birthday = dob.getText().toString();
                        String perId = personal_id.getText().toString();

                        if (TextUtils.isEmpty(user) || TextUtils.isEmpty(pass) || TextUtils.isEmpty(gen) || TextUtils.isEmpty(birthday) || TextUtils.isEmpty(perId)) {
                            Toast.makeText(SignUpActivity.this, "All fields Required!", Toast.LENGTH_SHORT).show();
                        }else {
                            boolean checkUser = brainTrainDatabase.checkUserName(user);
                            if (checkUser == false) {
                                boolean isInserted = brainTrainDatabase.insertAccount(user, pass, gen, birthday, perId);
                                if (isInserted == true){
                                    Toast.makeText(SignUpActivity.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getApplicationContext(), SignInActivity.class);
                                    startActivity(intent);
                                }else{
                                    Toast.makeText(SignUpActivity.this, "Registered Failed", Toast.LENGTH_SHORT).show();
                                }
                            }else{
                                Toast.makeText(SignUpActivity.this, "User already Exists!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                }
        );
    }

    public void Out(View view) {
        finish();
    }
}