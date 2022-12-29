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

import com.ddq.braintrain.levelmenu.CompareLevelMenuActivity;
import com.ddq.braintrain.levelmenu.FindOperatorLevelMenuActivity;
import com.ddq.braintrain.models.ProgressModel;

public class MathActivity extends AppCompatActivity {

    TextView findOperatorScore, compareScore, findOperatorProgress, compareProgress;
    CardView findOperatorCardView, compareCardView;
    ImageView findOperatorCompleted, compareCompleted;

    private BrainTrainDatabase brainTrainDatabase;
    private ProgressModel findOperatorModel, compareModel;

    AppCompatButton findOperatorGuideButton, compareGuideButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_math);

        findOperatorCardView = findViewById(R.id.findOperatorCardView);
        findOperatorScore = findViewById(R.id.findOperatorScore);
        compareScore = findViewById(R.id.compareScore);
        findOperatorProgress = findViewById(R.id.findOperatorProgress);
        compareProgress = findViewById(R.id.compareProgress);
        compareCardView = findViewById(R.id.compareCardView);
        findOperatorCompleted = findViewById(R.id.findOperatorCompleted);
        compareCompleted = findViewById(R.id.compareComplete);

        findOperatorGuideButton = findViewById(R.id.findOperatorGuideButton);
        compareGuideButton = findViewById(R.id.compareGuideButton);

        brainTrainDatabase = new BrainTrainDatabase(MathActivity.this);
        findOperatorModel = new BrainTrainDAO().getProgressStatus(brainTrainDatabase, 41);
        findOperatorScore.setText("Điểm của bạn: " + findOperatorModel.getUserScore());
        String result1 = String.format("%.2f", 100*((float) findOperatorModel.getUserScore() / (float) findOperatorModel.getMaxScore()));
        findOperatorProgress.setText("Đã hoàn thành: " + result1 + "%");
        if (findOperatorModel.isCompletedStatus()) {
            findOperatorCompleted.setVisibility(View.VISIBLE);
        }

        compareModel = new BrainTrainDAO().getProgressStatus(brainTrainDatabase, 42);
        compareScore.setText("Điểm của bạn: " + compareModel.getUserScore());
        String result2 = String.format("%.2f", 100*((float) compareModel.getUserScore() / (float) compareModel.getMaxScore()));
        compareProgress.setText("Đã hoàn thành: " + result2 + "%");
        if (compareModel.isCompletedStatus()) {
            compareCompleted.setVisibility(View.VISIBLE);
        }

        compareCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MathActivity.this, CompareLevelMenuActivity.class);
                startActivity(intent);
            }
        });
        findOperatorCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MathActivity.this, FindOperatorLevelMenuActivity.class);
                startActivity(intent);
            }
        });


        compareCardView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                compareGuideButton.setVisibility(View.VISIBLE);
                return false;
            }
        });

        findOperatorCardView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                findOperatorGuideButton.setVisibility(View.VISIBLE);
                return false;
            }
        });

        compareGuideButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alert = new AlertDialog.Builder(MathActivity.this);
                alert.setTitle("Hướng Dẫn");
                alert.setMessage("Trò chơi mô phỏng cảnh mua sắm. Nhiệm vụ là chọn sản phẩm có chi phí thấp hơn để mua\n" +
                        "\n" +
                        "Giá của sản phẩm được thể hiện bằng các biểu thức toán học bao gồm các phép tính đơn giản như cộng, trừ, nhân và chia\n" +
                        "\n" +
                        "Người chơi phải tính giá của các sản phẩm rồi chạm vào sản phẩm có giá trị thấp nhất");
                alert.setCancelable(false);

                alert.setNegativeButton("Không hiện lại", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        compareGuideButton.setVisibility(View.GONE);
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

        findOperatorGuideButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alert = new AlertDialog.Builder(MathActivity.this);
                alert.setTitle("Hướng Dẫn");
                alert.setMessage("Nhiệm vụ của người chơi là tìm hai số có tổng là bội số của chục, bội số của trăm hoặc bội số của nghìn");
                alert.setCancelable(false);

                alert.setNegativeButton("Không hiện lại", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        findOperatorGuideButton.setVisibility(View.GONE);
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
}