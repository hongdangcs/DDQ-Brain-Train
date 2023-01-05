package com.ddq.braintrain.gameactivity;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
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
import com.ddq.braintrain.models.FindWordGameModel;
import com.ddq.braintrain.models.ProgressModel;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.List;

public class FindWordGameActivity extends AppCompatActivity {
    private static final int START_TIMER = 120000;
    private String userInput, topicWord;
    CountDownTimer timer;
    long timeLeft = START_TIMER;
    int totalScore = 0, score = 0, countWord = 0, index = 0, currentScore = 0;
    private TextView txtFindWordCount, txtFindWordTime, txtFindWordScore, txtFindWordQuestion, txtFindWordNoti, txtFindWordError;
    AppCompatButton tryAgainButton, submitFindWordButton;
    private EditText editFindWordAnswer;
    private BrainTrainDatabase brainTrainDatabase;
    private ProgressModel maxScore;
    private static List<FindWordGameModel> findWordGameModels;
    String wordFound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_word_game);

        txtFindWordQuestion = (TextView) findViewById(R.id.txtFindWordQuestion);
        txtFindWordTime = (TextView) findViewById(R.id.txtFindWordTime);
        txtFindWordScore = (TextView) findViewById(R.id.txtFindWordScore);
        txtFindWordNoti = (TextView) findViewById(R.id.txtFindWordNoti);
        txtFindWordCount = (TextView) findViewById(R.id.txtFindWordCount);
        editFindWordAnswer = findViewById(R.id.editFindWordAnswer);
        tryAgainButton = findViewById(R.id.tryAgainButton);
        submitFindWordButton = findViewById(R.id.submitFindWordButton);
        txtFindWordError = findViewById(R.id.txtFindWordError);
        brainTrainDatabase = new BrainTrainDatabase(FindWordGameActivity.this);
        findWordGameModels = new BrainTrainDAO().findWordGameModels(brainTrainDatabase);

        wordFound = "";

        Collections.shuffle(findWordGameModels);
        maxScore = new BrainTrainDAO().getProgressStatus(brainTrainDatabase, 32);
        gameStart();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        getTotalScore();
        if (maxScore.getUserScore() < currentScore) {
            totalScore = currentScore;
            brainTrainDatabase.updateUserScore(32, totalScore);
        }
        startActivity(new Intent(FindWordGameActivity.this, LanguageActivity.class));
        this.finish();
    }

    // Game section:
    private void gameStart() {
        txtFindWordScore.setText("Điểm: " + score);
        editFindWordAnswer.setVisibility(View.VISIBLE);
        tryAgainButton.setVisibility(View.GONE);
        txtFindWordNoti.setVisibility(View.GONE);
        txtFindWordError.setVisibility(View.GONE);
        txtFindWordCount.setText("Số câu đúng:" + countWord);

        topicWord = findWordGameModels.get(index).getWord();
        topicWord = topicWord.substring(0, 1).toUpperCase() + topicWord.substring(1);
        txtFindWordQuestion.setText(topicWord);
        editFindWordAnswer.setText(topicWord + " ");

        submitFindWordButton.setEnabled(false);
        editFindWordAnswer.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                int limit = 2 + topicWord.length();
                submitFindWordButton.setEnabled(s.toString().trim().length() >= limit);
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
        startTimer();
    }

    public boolean spellingCheck(String sb) throws IOException {
        sb = sb.replaceAll(" ", "");
        sb = sb.toLowerCase();
/*
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(getAssets().open("output.txt")));
            String line;
            while ((line = br.readLine()) != null) {
                if (line.matches(".*\\b" + sb + "\\b.*")) {
                    return true;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();

        }
        return false;
*/

        try {
       //     BufferedReader br = new BufferedReader(new FileReader("dict" + sb.length() + ".txt"));
            BufferedReader reader = new BufferedReader(   new InputStreamReader(getAssets().open("dict" + sb.length() + ".txt")));
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.matches(".*\\b" + sb + "\\b.*")) {
                    return true;
                }

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Log.d(TAG, "spellingCheck: file not found" );
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
                editFindWordAnswer.setVisibility(View.GONE);
                tryAgainButton.setVisibility(View.VISIBLE);
                txtFindWordNoti.setVisibility(View.VISIBLE);
                getTotalScore();
                if (maxScore.getUserScore() < currentScore) {
                    totalScore = currentScore;
                    brainTrainDatabase.updateUserScore(32, totalScore);
                }
            }
        }.start();
    }

    public void updateTimeCounterText() {
        txtFindWordTime.setText("Bạn còn: " + timeLeft + " giây");
    }

    // Score section:
    public void updateScore() {
        score += 200;
        txtFindWordScore.setText("Điểm: " + score);
    }

    public int getTotalScore() {
        currentScore = score * countWord;
        return currentScore;
    }

    // Submit button handle:
    public void Submit(View view) throws IOException {
        userInput = editFindWordAnswer.getText().toString();
        if (spellingCheck(userInput) == true && !wordFound.contains(userInput)) {
            updateScore();
            countWord = countWord + 1;
            txtFindWordCount.setText("Số câu đúng: " + countWord);
            editFindWordAnswer.getText().clear();
            editFindWordAnswer.setText(topicWord + " ");
            editFindWordAnswer.setSelection(editFindWordAnswer.getText().length());
            wordFound+= userInput;
        } else {
            Toast.makeText(FindWordGameActivity.this, "Câu trả lời Sai!", Toast.LENGTH_LONG).show();
        }
    }

    // Try again button handle:
    public void tryAgain(View view) {
        if (index == findWordGameModels.size()) {
            index = 0;
        } else {
            index++;
        }
        countWord = 0;
        score = 0;
        editFindWordAnswer.getText().clear();
        submitFindWordButton.setVisibility(View.VISIBLE);
        gameStart();
    }
}