package com.ddq.braintrain.gameactivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.ddq.braintrain.R;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FindWordGameActivity extends AppCompatActivity {

    private final String alphabet = "BCDĐGHKLMNPQRSTVX";
    private static final int START_TIMER = 120000;
    private String userInput;
    CountDownTimer timer;
    long timeLeft = START_TIMER;
    int totalScore = 0, score = 0, countWord = 0;
    private char correctLetter;
    private TextView txtFindWordCount, txtFindWordTime, txtFindWordScore, txtFindWordQuestion, txtFindWordNoti, txtFindWordError;
    AppCompatButton tryAgainButton, submitFindWordButton;
    private EditText editCompleteWordAnswer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_word_game);

        txtFindWordQuestion = (TextView) findViewById(R.id.txtFindWordQuestion);
        txtFindWordTime = (TextView) findViewById(R.id.txtFindWordTime);
        txtFindWordScore = (TextView) findViewById(R.id.txtFindWordScore);
        txtFindWordNoti = (TextView) findViewById(R.id.txtFindWordNoti);
        txtFindWordCount = (TextView) findViewById(R.id.txtFindWordCount);
        editCompleteWordAnswer = findViewById(R.id.editFindWordAnswer);
        tryAgainButton = findViewById(R.id.tryAgainButton);
        submitFindWordButton = findViewById(R.id.submitFindWordButton);
        txtFindWordError = findViewById(R.id.txtFindWordError);

        gameStart();
    }

    // Game section:
    private void gameStart() {
        txtFindWordScore.setText("Điểm: " + score);
        submitFindWordButton.setVisibility(View.VISIBLE);
        editCompleteWordAnswer.setVisibility(View.VISIBLE);
        tryAgainButton.setVisibility(View.GONE);
        txtFindWordNoti.setVisibility(View.GONE);
        txtFindWordError.setVisibility(View.GONE);
        txtFindWordCount.setText("Số câu đúng:" + countWord);
        submitFindWordButton.setEnabled(false);
        editCompleteWordAnswer.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if(s.toString().trim().length()<2){
                    submitFindWordButton.setEnabled(false);
                } else {
                    submitFindWordButton.setEnabled(true);
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                // TODO Auto-generated method stub

            }

            @Override
            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub

            }
        });
        String shuffleLetter = shuffle(alphabet);
        Random rnd = new Random();
        correctLetter = shuffleLetter.charAt(rnd.nextInt(shuffleLetter.length()));
        txtFindWordQuestion.setText(""+correctLetter);

        startTimer();
    }

    public String shuffle(String input){
        List<Character> characters = new ArrayList<Character>();
        for(char c:input.toCharArray()){
            characters.add(c);
        }
        StringBuilder output = new StringBuilder(input.length());
        while(characters.size()!=0){
            int randPicker = (int)(Math.random()*characters.size());
            output.append(characters.remove(randPicker));
        }
        return output.toString();
    }

    public boolean spellingCheck(String sb) throws IOException {
        sb = sb.replaceAll(" ", "");
        sb = sb.toLowerCase();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(getAssets().open("output.txt")));
            String line;
            while((line = br.readLine())!=null) {
                if(line.matches(".*\\b"+sb+"\\b.*")) {
                    return true;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return false;

    }

    // Time section:
    public void startTimer() {
        timer = new CountDownTimer(START_TIMER, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeft = millisUntilFinished / 1000;
                updateTimeCounterText();
            }

            @Override
            public void onFinish() {
                totalScore = getTotalScore();
                timer.cancel();
                submitFindWordButton.setVisibility(View.GONE);
                editCompleteWordAnswer.setVisibility(View.GONE);
                tryAgainButton.setVisibility(View.VISIBLE);
                txtFindWordNoti.setVisibility(View.VISIBLE);
            }
        }.start();
    }

    public void updateTimeCounterText() {
        txtFindWordTime.setText("Bạn còn: " + timeLeft + " giây");
    }

    // Score section:
    public void updateScore() {
        switch (userInput.length()) {
            case 2:
                score += 200;
                break;
            case 3:
                score += 300;
                break;
            case 4:
                score += 400;
                break;
            case 5:
                score += 500;
                break;
            case 6:
                score += 600;
                break;
            case 7:
                score += 700;
                break;
            default:
                score += 0;
                break;
        }
        txtFindWordScore.setText("Điểm: " + score);
    }

    public int getTotalScore () {
        return score * countWord;
    }

    // Submit button handle:
    public void Submit(View view) throws IOException {
        userInput = editCompleteWordAnswer.getText().toString();
        char firstChar = userInput.charAt(0);
        if (Character.toUpperCase(firstChar) == correctLetter && spellingCheck(userInput) == true){
            for (char c : userInput.toCharArray()) {
                txtFindWordError.setVisibility(View.GONE);
                editCompleteWordAnswer.getText().clear();
                if (c == ' ') {
                    txtFindWordError.setVisibility(View.VISIBLE);
                    return;
                }
            }
            updateScore();
            countWord = countWord + 1;
            txtFindWordCount.setText("Số câu đúng: " + countWord);
            editCompleteWordAnswer.getText().clear();
        } else {
            Toast.makeText(FindWordGameActivity.this, "Câu trả lời Sai!", Toast.LENGTH_LONG).show();
        }
    }

    // Try again button handle:
    public void tryAgain(View view) {
        countWord = 0;
        score = 0;
        editCompleteWordAnswer.getText().clear();
        gameStart();
    }
}