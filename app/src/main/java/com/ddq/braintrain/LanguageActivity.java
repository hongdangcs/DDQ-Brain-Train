package com.ddq.braintrain;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.cardview.widget.CardView;

import com.ddq.braintrain.gameactivity.CompleteWordGameActivity;
import com.ddq.braintrain.gameactivity.ConjunctionGameActivity;
import com.ddq.braintrain.gameactivity.FindWordGameActivity;
import com.ddq.braintrain.gameactivity.SortingCharGameActivity;
import com.ddq.braintrain.models.ProgressModel;

public class LanguageActivity extends AppCompatActivity implements View.OnClickListener {

    TextView completeWordScore, completeWordProgress, findWordScore, findWordProgress, conjunctionScore, conjunctionProgress, sortingCharScore, sortingCharProgress;
    CardView completeWordCardView, findWordCardView, conjunctionCardView, sortingCharCardView;
    ImageView completeWordCompleted, findWordCompleted, conjunctionCompleted, sortingCharCompleted;

    private BrainTrainDatabase brainTrainDatabase;
    private ProgressModel completeWordModel, findWordModel, conjunctionModel, sortingCharModel;

    AppCompatButton completeWordGuideButton, findWordGuideButton,conjunctionGuideButton,sortingCharGuideButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language);

        completeWordScore = findViewById(R.id.completeWordScore);
        completeWordProgress = findViewById(R.id.completeWordProgress);
        findWordScore = findViewById(R.id.findWordScore);
        findWordProgress = findViewById(R.id.findWordProgress);
        conjunctionScore = findViewById(R.id.conjunctionScore);
        conjunctionProgress = findViewById(R.id.conjunctionProgress);
        sortingCharScore = findViewById(R.id.sortingCharScore);
        sortingCharProgress = findViewById(R.id.sortingCharProgress);
        completeWordCardView = findViewById(R.id.completeWordCardView);
        findWordCardView = findViewById(R.id.findWordCardView);
        conjunctionCardView = findViewById(R.id.conjunctionCardView);
        sortingCharCardView = findViewById(R.id.sortingCharCardView);
        completeWordCompleted = findViewById(R.id.completeWordComplete);
        findWordCompleted = findViewById(R.id.findWordCompleted);
        conjunctionCompleted = findViewById(R.id.conjunctionComplete);
        sortingCharCompleted = findViewById(R.id.sortingCharComplete);

        completeWordGuideButton = findViewById(R.id.completeWordGuideButton);
        findWordGuideButton = findViewById(R.id.findWordGuideButton);
        conjunctionGuideButton = findViewById(R.id.conjunctionGuideButton);
        sortingCharGuideButton = findViewById(R.id.sortingCharGuideButton);


        brainTrainDatabase = new BrainTrainDatabase(LanguageActivity.this);
        completeWordModel = new BrainTrainDAO().getProgressStatus(brainTrainDatabase, 31);
        completeWordScore.setText("Điểm của bạn: " + completeWordModel.getUserScore());
        completeWordProgress.setText("Đã hoàn thành: " + ((float) completeWordModel.getUserScore() / (float) completeWordModel.getMaxScore()) + "%");
        if (completeWordModel.isCompletedStatus()) {
            completeWordCompleted.setVisibility(View.VISIBLE);
        }

        findWordModel = new BrainTrainDAO().getProgressStatus(brainTrainDatabase, 32);
        findWordScore.setText("Điểm của bạn: " + findWordModel.getUserScore());
        findWordProgress.setText("Đã hoàn thành: " + ((float) findWordModel.getUserScore() / (float) findWordModel.getMaxScore()) + "%");
        if (findWordModel.isCompletedStatus()) {
            findWordCompleted.setVisibility(View.VISIBLE);
        }

        conjunctionModel = new BrainTrainDAO().getProgressStatus(brainTrainDatabase, 33);
        conjunctionScore.setText("Điểm của bạn: " + conjunctionModel.getUserScore());
        conjunctionProgress.setText("Đã hoàn thành: " + ((float) conjunctionModel.getUserScore() / (float) conjunctionModel.getMaxScore()) + "%");
        if (conjunctionModel.isCompletedStatus()) {
            conjunctionCompleted.setVisibility(View.VISIBLE);
        }

        sortingCharModel = new BrainTrainDAO().getProgressStatus(brainTrainDatabase, 34);
        sortingCharScore.setText("Điểm của bạn: " + sortingCharModel.getUserScore());
        sortingCharProgress.setText("Đã hoàn thành: " + ((float) sortingCharModel.getUserScore() / (float) sortingCharModel.getMaxScore()) + "%");
        if (sortingCharModel.isCompletedStatus()) {
            sortingCharCompleted.setVisibility(View.VISIBLE);
        }
        completeWordCardView.setOnClickListener(LanguageActivity.this);
        findWordCardView.setOnClickListener(LanguageActivity.this);
        conjunctionCardView.setOnClickListener(LanguageActivity.this);
        sortingCharCardView.setOnClickListener(LanguageActivity.this);

        completeWordGuideButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alert = new AlertDialog.Builder(LanguageActivity.this);
                alert.setTitle("Hướng Dẫn");
                alert.setMessage("Trò chơi sẽ cung cấp cho người dùng 1 chữ cái\n" +
                        "\n" +
                        "Trong vòng 2 phút, hãy tìm những từ có nghĩa bắt đầu bằng chữ cái này\n" +
                        "\n" +
                        "Từ bạn tìm thấy càng dài, bạn càng nhận được số điểm cao");
                alert.setCancelable(false);
                alert.setNegativeButton("Dã Hiểu", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                AlertDialog alertDialog = alert.create();
                alertDialog.show();
            }
        });

        findWordGuideButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alert = new AlertDialog.Builder(LanguageActivity.this);
                alert.setTitle("Hướng Dẫn");
                alert.setMessage("Trong vòng 2 phút, nhiệm vụ là tìm những từ có thể ghép với từ cho sẵn ban đầu thành từ ghép có nghĩa");
                alert.setCancelable(false);
                alert.setNegativeButton("Dã Hiểu", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                AlertDialog alertDialog = alert.create();
                alertDialog.show();
            }
        });

        conjunctionGuideButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alert = new AlertDialog.Builder(LanguageActivity.this);
                alert.setTitle("Hướng Dẫn");
                alert.setMessage("Người dùng tiếp tục tìm một từ khác để nối với từ cuối cùng trong từ ghép đã tìm được trước đó để tạo từ ghép có nghĩa mới\n" +
                        "\n" +
                        "Tiếp tục làm điều này cho đến khi không thể tìm thấy nhiều từ hơn để phù hợp");
                alert.setCancelable(false);
                alert.setNegativeButton("Dã Hiểu", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                AlertDialog alertDialog = alert.create();
                alertDialog.show();
            }
        });

        sortingCharGuideButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alert = new AlertDialog.Builder(LanguageActivity.this);
                alert.setTitle("Hướng Dẫn");
                alert.setMessage("Trò chơi này sẽ cung cấp một cụm từ có các chữ cái được xáo trộn\n" +
                        "\n" +
                        "Nhiệm vụ của người chơi là sắp xếp lại thứ tự các chữ cái để tìm ra từ chính xác");
                alert.setCancelable(false);
                alert.setNegativeButton("Dã Hiểu", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                AlertDialog alertDialog = alert.create();
                alertDialog.show();
            }
        });

    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.completeWordCardView:
                intent = new Intent(LanguageActivity.this, CompleteWordGameActivity.class);
                break;

            case R.id.findWordCardView:
                intent = new Intent(LanguageActivity.this, FindWordGameActivity.class);
                break;
            case R.id.conjunctionCardView:
                intent = new Intent(LanguageActivity.this, ConjunctionGameActivity.class);
                break;
            default:
                intent = new Intent(LanguageActivity.this, SortingCharGameActivity.class);
                break;
        }
        startActivity(intent);
    }


}