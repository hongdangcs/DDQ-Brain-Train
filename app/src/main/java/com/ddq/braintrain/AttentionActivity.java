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

import com.ddq.braintrain.levelmenu.DifferentLevelMenuActivity;
import com.ddq.braintrain.levelmenu.FlashCardLevelMenuActivity;
import com.ddq.braintrain.levelmenu.SharkBoatLevelMenuActivity;
import com.ddq.braintrain.models.ProgressModel;

public class AttentionActivity extends AppCompatActivity implements View.OnClickListener {

    TextView sharkBoatScore, sharkBoatProgress, flashCardScore, flashCardProgress, differentScore, differentProgress;
    CardView differentCardView, flashCardCardView, sharkBoatCardView;
    ImageView differentCompleted, flashCardCompleted, sharkBoatCompleted;

    private BrainTrainDatabase brainTrainDatabase;
    private ProgressModel differentModel, flashCardModel, sharkBoatModel;

    AppCompatButton differentGuideButton, flashCardGuideButton,sharkBoatGuideButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attention);

        sharkBoatScore = findViewById(R.id.sharkBoatScore);
        sharkBoatProgress = findViewById(R.id.sharkBoatProgress);
        flashCardScore = findViewById(R.id.flashCardScore);
        flashCardProgress = findViewById(R.id.flashCardProgress);
        differentScore = findViewById(R.id.differentScore);
        differentProgress = findViewById(R.id.differentProgress);
        differentCardView = findViewById(R.id.differentCardView);
        flashCardCardView = findViewById(R.id.flashCardCardView);
        sharkBoatCardView = findViewById(R.id.sharkBoatCardView);
        differentCompleted = findViewById(R.id.differentComplete);
        flashCardCompleted = findViewById(R.id.flashCardComplete);
        sharkBoatCompleted = findViewById(R.id.sharkBoatComplete);

        differentGuideButton = findViewById(R.id.differentGuideButton);
        flashCardGuideButton = findViewById(R.id.flashCardGuideButton);
        sharkBoatGuideButton = findViewById(R.id.sharkBoatGuideButton);

        brainTrainDatabase = new BrainTrainDatabase(AttentionActivity.this);
        differentModel = new BrainTrainDAO().getProgressStatus(brainTrainDatabase, 21);
        differentScore.setText("Điểm của bạn: " + differentModel.getUserScore());
        differentProgress.setText("Đã hoàn thành: " + ((float) differentModel.getUserScore() / (float) differentModel.getMaxScore()) + "%");
        if (differentModel.isCompletedStatus()) {
            differentCompleted.setVisibility(View.VISIBLE);
        }

        flashCardModel = new BrainTrainDAO().getProgressStatus(brainTrainDatabase, 22);
        flashCardScore.setText("Điểm của bạn: " + flashCardModel.getUserScore());
        flashCardProgress.setText("Đã hoàn thành: " + ((float) flashCardModel.getUserScore() / (float) flashCardModel.getMaxScore()) + "%");
        if (flashCardModel.isCompletedStatus()) {
            flashCardCompleted.setVisibility(View.VISIBLE);
        }

        sharkBoatModel = new BrainTrainDAO().getProgressStatus(brainTrainDatabase, 23);
        sharkBoatScore.setText("Điểm của bạn: " + sharkBoatModel.getUserScore());
        sharkBoatProgress.setText("Đã hoàn thành: " + ((float) sharkBoatModel.getUserScore() / (float) sharkBoatModel.getMaxScore()) + "%");
        if (sharkBoatModel.isCompletedStatus()) {
            sharkBoatCompleted.setVisibility(View.VISIBLE);
        }

        differentCardView.setOnClickListener(AttentionActivity.this);
        flashCardCardView.setOnClickListener(AttentionActivity.this);
        sharkBoatCardView.setOnClickListener(AttentionActivity.this);

        differentCardView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                differentGuideButton.setVisibility(View.VISIBLE);
                return false;
            }
        });

        flashCardCardView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                flashCardGuideButton.setVisibility(View.VISIBLE);
                return false;
            }
        });

        sharkBoatCardView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                sharkBoatGuideButton.setVisibility(View.VISIBLE);
                return false;
            }
        });


        differentGuideButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alert = new AlertDialog.Builder(AttentionActivity.this);
                alert.setTitle("Hướng Dẫn");
                alert.setMessage("Nhiệm vụ là tìm một điểm khác biệt duy nhất trong bức tranh");
                alert.setCancelable(false);
                alert.setNegativeButton("Không hiện lại", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        differentGuideButton.setVisibility(View.GONE);
                    }
                });
                alert.setPositiveButton("Đã Hiểu", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                AlertDialog alertDialog = alert.create();
                alertDialog.show();
            }
        });

        flashCardGuideButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alert = new AlertDialog.Builder(AttentionActivity.this);
                alert.setTitle("Hướng Dẫn");
                alert.setMessage("Trò chơi sẽ cung cấp các cặp hình ảnh khác nhau\n" +
                        "\n" +
                        "Nhiệm vụ của người chơi là chọn chính xác các hình ghép thành một cặp");
                alert.setCancelable(false);

                alert.setNegativeButton("Không hiện lại", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        flashCardGuideButton.setVisibility(View.GONE);
                    }
                });
                alert.setPositiveButton("Đã Hiểu", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });



                AlertDialog alertDialog = alert.create();
                alertDialog.show();
            }
        });

        sharkBoatGuideButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alert = new AlertDialog.Builder(AttentionActivity.this);
                alert.setTitle("Hướng Dẫn");
                alert.setMessage("Người chơi phải đảm bảo an toàn cho tàu thuyền trên biển bằng cách ngăn chặn cá mập đến cắn thuyền\n" +
                        "\n" +
                        "Khi người chơi dự đoán cá mập sẽ va vào thuyền, hãy chạm vào màn hình để tạo sóng biển\n" +
                        "\n" +
                        "Sóng sẽ khiến cá mập dạt ra xa thuyền và bơi theo hướng khác\n" +
                        "\n" +
                        "Trong 2 phút, cố gắng không để con cá mập nào phá thuyền");
                alert.setCancelable(false);

                alert.setNegativeButton("Không hiện lại", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        sharkBoatGuideButton.setVisibility(View.GONE);
                    }
                });
                alert.setPositiveButton("Đã Hiểu", new DialogInterface.OnClickListener() {
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
            case R.id.differentCardView:
                intent = new Intent(AttentionActivity.this, DifferentLevelMenuActivity.class);
                break;
            case R.id.flashCardCardView:
                intent = new Intent(AttentionActivity.this, FlashCardLevelMenuActivity.class);
                break;
            default:
                intent = new Intent(AttentionActivity.this, SharkBoatLevelMenuActivity.class);
                break;
        }
        startActivity(intent);
    }
}