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

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.ddq.braintrain.BrainTrainDAO;
import com.ddq.braintrain.BrainTrainDatabase;
import com.ddq.braintrain.LanguageActivity;
import com.ddq.braintrain.R;
import com.ddq.braintrain.models.ConjunctionGameModel;
import com.ddq.braintrain.models.ProgressModel;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.List;

public class ConjunctionGameActivity extends AppCompatActivity {
    private static final int START_TIMER = 60000;
    private String userInput, topicWord;
    CountDownTimer timer;
    long timeLeft = START_TIMER;
    int totalScore = 0, score = 0, countWord = 0, index = 0, currentScore = 0;
    private TextView txtConjunctionWordCount, txtConjunctionWordTime, txtConjunctionWordScore, txtConjunctionWordQuestion, txtConjunctionWordNoti, txtConjunctionWordError;
    AppCompatButton tryAgainButton, submitConjunctionWordButton;
    private EditText editConjunctionWordAnswer;
    private BrainTrainDatabase brainTrainDatabase;
    private ProgressModel maxScore;
    private static List<ConjunctionGameModel> conjunctionGameModels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conjunction_game);
        txtConjunctionWordQuestion = (TextView) findViewById(R.id.txtConjunctionWordQuestion);
        txtConjunctionWordTime = (TextView) findViewById(R.id.txtConjunctionWordTime);
        txtConjunctionWordScore = (TextView) findViewById(R.id.txtConjunctionWordScore);
        txtConjunctionWordNoti = (TextView) findViewById(R.id.txtConjunctionWordNoti);
        txtConjunctionWordCount = (TextView) findViewById(R.id.txtConjunctionWordCount);
        editConjunctionWordAnswer = findViewById(R.id.editConjunctionWordAnswer);
        tryAgainButton = findViewById(R.id.tryAgainButton);
        submitConjunctionWordButton = findViewById(R.id.submitConjunctionWordButton);
        txtConjunctionWordError = findViewById(R.id.txtConjunctionWordError);
        brainTrainDatabase = new BrainTrainDatabase(ConjunctionGameActivity.this);
        conjunctionGameModels = new BrainTrainDAO().conjunctionGameModels(brainTrainDatabase);
        Collections.shuffle(conjunctionGameModels);

        maxScore = new BrainTrainDAO().getProgressStatus(brainTrainDatabase, 33);
        gameStart();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        getTotalScore();
        if (maxScore.getUserScore() < currentScore) {
            totalScore = currentScore;
            brainTrainDatabase.updateUserScore(33, totalScore);
        }
        startActivity(new Intent(ConjunctionGameActivity.this, LanguageActivity.class));
        this.finish();
    }

    // Game section:
    private void gameStart() {
        txtConjunctionWordScore.setText("Điểm: " + score);
        editConjunctionWordAnswer.setVisibility(View.VISIBLE);
        tryAgainButton.setVisibility(View.GONE);
        txtConjunctionWordNoti.setVisibility(View.GONE);
        txtConjunctionWordError.setVisibility(View.GONE);
        txtConjunctionWordCount.setText("Số câu đúng:" + countWord);

        topicWord = conjunctionGameModels.get(index).getWord();
        topicWord = topicWord.substring(0, 1).toUpperCase() + topicWord.substring(1);
        txtConjunctionWordQuestion.setText(topicWord);
        editConjunctionWordAnswer.setText(topicWord + " ");

        submitConjunctionWordButton.setEnabled(false);
        editConjunctionWordAnswer.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                int limit = 2 + topicWord.length();
                submitConjunctionWordButton.setEnabled(s.toString().trim().length() >= limit);
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

        try {
           // BufferedReader br = new BufferedReader(new FileReader("dict" + sb.length() + ".txt"));
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
                timer.cancel();
                submitConjunctionWordButton.setVisibility(View.GONE);
                editConjunctionWordAnswer.setVisibility(View.GONE);
                tryAgainButton.setVisibility(View.VISIBLE);
                txtConjunctionWordNoti.setVisibility(View.VISIBLE);
                getTotalScore();
                if (maxScore.getUserScore() < currentScore) {
                    totalScore = currentScore;
                    brainTrainDatabase.updateUserScore(33, totalScore);
                }
            }
        }.start();
    }

    public void updateTimeCounterText() {
        txtConjunctionWordTime.setText("Bạn còn: " + timeLeft + " giây");
    }

    // Score section:
    public void updateScore() {
        score += 200;
        txtConjunctionWordScore.setText("Điểm: " + score);
    }

    public int getTotalScore() {
        currentScore = score * countWord;
        return currentScore;
    }

    // Submit button handle:
    public void Submit(View view) throws IOException {
        userInput = editConjunctionWordAnswer.getText().toString();
        if (spellingCheck(userInput) == true) {
            timer.cancel();
            updateScore();
            countWord = countWord + 1;
            txtConjunctionWordCount.setText("Số câu đúng: " + countWord);
            editConjunctionWordAnswer.getText().clear();
            String[] words = userInput.split("\\s");
            topicWord = words[1].substring(0, 1).toUpperCase() + words[1].substring(1);
            editConjunctionWordAnswer.setText(topicWord + " ");
            txtConjunctionWordQuestion.setText(topicWord);
            editConjunctionWordAnswer.setSelection(editConjunctionWordAnswer.getText().length());
            startTimer();
        } else {
            Toast.makeText(ConjunctionGameActivity.this, "Câu trả lời Sai!", Toast.LENGTH_LONG).show();
        }
    }

    // Try again button handle:
    public void tryAgain(View view) {
        if (index == conjunctionGameModels.size()) {
            index = 0;
        } else {
            index++;
        }
        countWord = 0;
        score = 0;
        editConjunctionWordAnswer.getText().clear();
        submitConjunctionWordButton.setVisibility(View.VISIBLE);
        gameStart();
    }
}