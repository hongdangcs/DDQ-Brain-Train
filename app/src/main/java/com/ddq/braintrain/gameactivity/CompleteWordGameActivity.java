package com.ddq.braintrain.gameactivity;

import android.content.Intent;
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

import com.ddq.braintrain.BrainTrainDAO;
import com.ddq.braintrain.BrainTrainDatabase;
import com.ddq.braintrain.LanguageActivity;
import com.ddq.braintrain.R;
import com.ddq.braintrain.models.ProgressModel;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CompleteWordGameActivity extends AppCompatActivity {
    private final String alphabet = "BCDĐGHKLMNPQRSTVX";
    private static final int START_TIMER = 120000;
    private String userInput;
    CountDownTimer timer;
    long timeLeft = START_TIMER, timeRes, totalTimeRes;
    int totalScore = 0, score = 0, countWord = 0, currentScore;
    private char correctLetter;
    private ProgressModel maxScore;
    private TextView txtCompleteWordCount, txtCompleteWordTime, txtCompleteWordScore, txtCompleteWordQuestion, txtCompleteWordNoti, txtCompleteWordError;
    AppCompatButton tryAgainButton, submitCompleteWordButton;
    private BrainTrainDatabase brainTrainDatabase;
    private EditText editCompleteWordAnswer;
    String wordFound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complete_word_game);

        txtCompleteWordQuestion = (TextView) findViewById(R.id.txtCompleteWordQuestion);
        txtCompleteWordTime = (TextView) findViewById(R.id.txtCompleteWordTime);
        txtCompleteWordScore = (TextView) findViewById(R.id.txtCompleteWordScore);
        txtCompleteWordNoti = (TextView) findViewById(R.id.txtCompleteWordNoti);
        txtCompleteWordCount = (TextView) findViewById(R.id.txtCompleteWordCount);
        editCompleteWordAnswer = findViewById(R.id.editCompleteWordAnswer);
        tryAgainButton = findViewById(R.id.tryAgainButton);
        submitCompleteWordButton = findViewById(R.id.submitCompleteWordButton);
        txtCompleteWordError = findViewById(R.id.txtCompleteWordError);
        brainTrainDatabase = new BrainTrainDatabase(CompleteWordGameActivity.this);
        maxScore = new BrainTrainDAO().getProgressStatus(brainTrainDatabase, 31);

        wordFound = "";
        gameStart();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        getTotalScore();
        if (maxScore.getUserScore() < currentScore) {
            totalScore = currentScore;
            brainTrainDatabase.updateUserScore(31, totalScore);
        }
        startActivity(new Intent(CompleteWordGameActivity.this, LanguageActivity.class));
        this.finish();
    }

    // Game section:
    private void gameStart() {
        txtCompleteWordScore.setText("Điểm: " + score);
        submitCompleteWordButton.setVisibility(View.VISIBLE);
        editCompleteWordAnswer.setVisibility(View.VISIBLE);
        tryAgainButton.setVisibility(View.GONE);
        txtCompleteWordNoti.setVisibility(View.GONE);
        txtCompleteWordError.setVisibility(View.GONE);
        txtCompleteWordCount.setText("Số câu đúng:" + countWord);
        submitCompleteWordButton.setEnabled(false);
        editCompleteWordAnswer.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                submitCompleteWordButton.setEnabled(s.toString().trim().length() >= 2);
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
        txtCompleteWordQuestion.setText("" + correctLetter);

        startTimer();
    }

    public String shuffle(String input) {
        List<Character> characters = new ArrayList<Character>();
        for (char c : input.toCharArray()) {
            characters.add(c);
        }
        StringBuilder output = new StringBuilder(input.length());
        while (characters.size() != 0) {
            int randPicker = (int) (Math.random() * characters.size());
            output.append(characters.remove(randPicker));
        }
        return output.toString();
    }

    public boolean spellingCheck(String sb) throws IOException {
        sb = sb.replaceAll(" ", "");
        sb = sb.toLowerCase();

        try {
            //BufferedReader br = new BufferedReader(new FileReader("dict" + sb.length() + ".txt"));
            BufferedReader reader = new BufferedReader(   new InputStreamReader(getAssets().open("dict" + sb.length() + ".txt")));

            String line;
            while ((line = reader.readLine()) != null) {
                if (line.matches(".*\\b" + sb + "\\b.*")) {
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
                submitCompleteWordButton.setVisibility(View.GONE);
                editCompleteWordAnswer.setVisibility(View.GONE);
                tryAgainButton.setVisibility(View.VISIBLE);
                txtCompleteWordNoti.setVisibility(View.VISIBLE);
                getTotalScore();
                if (maxScore.getUserScore() < currentScore) {
                    totalScore = currentScore;
                    brainTrainDatabase.updateUserScore(31, totalScore);
                }
            }
        }.start();
    }

    public void updateTimeCounterText() {
        txtCompleteWordTime.setText("Bạn còn: " + timeLeft + " giây");
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
        txtCompleteWordScore.setText("Điểm: " + score);
    }

    public int getTotalScore() {
        currentScore = score * countWord;
        return currentScore;
    }

    // Submit button handle:
    public void Submit(View view) throws IOException {
        userInput = editCompleteWordAnswer.getText().toString();
        char firstChar = userInput.charAt(0);
        if (Character.toUpperCase(firstChar) == correctLetter && spellingCheck(userInput) == true && !wordFound.contains(userInput)) {
            for (char c : userInput.toCharArray()) {
                txtCompleteWordError.setVisibility(View.GONE);
                editCompleteWordAnswer.getText().clear();
                if (c == ' ') {
                    txtCompleteWordError.setVisibility(View.VISIBLE);
                    return;
                }
            }
            updateScore();
            countWord = countWord + 1;
            txtCompleteWordCount.setText("Số câu đúng: " + countWord);
            editCompleteWordAnswer.getText().clear();
            wordFound+= userInput;
        } else {
            Toast.makeText(CompleteWordGameActivity.this, "Câu trả lời Sai!", Toast.LENGTH_LONG).show();
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