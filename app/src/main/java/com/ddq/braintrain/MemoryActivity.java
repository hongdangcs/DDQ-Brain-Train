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

import com.ddq.braintrain.levelmenu.GridsHighlightLevelMenuActivity;
import com.ddq.braintrain.levelmenu.MissingObjectLevelMenuActivity;
import com.ddq.braintrain.levelmenu.NotInPreviousLevelMenuActivity;
import com.ddq.braintrain.models.ProgressModel;

public class MemoryActivity extends AppCompatActivity {

    CardView notInPreviousCardView, missingObjectCardView, gridsHighlightCardView;
    TextView notInPreviousScore, missingObjectScore, gridsHighlightScore, notInPreviousProgress, missingObjectProgress, gridsHighlightProgress;
    ImageView gridsHighlightComplete, missingObjectComplete, notInPreviousComplete;

    private BrainTrainDatabase brainTrainDatabase;
    private ProgressModel notInPreviousModel;
    private ProgressModel gridsHighlightModel;

    private static int missingObjectCurrentScore;

    public static int getMissingObjectCurrentScore() {
        return missingObjectCurrentScore;
    }

    private  ProgressModel missingObjectModel;

    AppCompatButton gridsHighlightGuideButton, missingObjectGuideButton, notInPreviousGuideButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memory);

        notInPreviousScore = findViewById(R.id.notInPreviousScore);
        notInPreviousCardView = findViewById(R.id.notInPreviousCardView);
        missingObjectCardView = findViewById(R.id.missingObjectCardView);
        gridsHighlightCardView = findViewById(R.id.gridsHighlightCardView);
        gridsHighlightScore = findViewById(R.id.gridsHighlightScore);
        notInPreviousProgress = findViewById(R.id.notInPreviousProgress);
        missingObjectProgress = findViewById(R.id.missingObjectProgress);
        gridsHighlightProgress = findViewById(R.id.gridsHighlightProgress);
        gridsHighlightComplete = findViewById(R.id.gridsHighlightComplete);
        missingObjectScore = findViewById(R.id.missingObjectScore);
        missingObjectComplete = findViewById(R.id.missingObjectComplete);
        notInPreviousComplete = findViewById(R.id.notInPreviousComplete);

        gridsHighlightGuideButton = findViewById(R.id.gridsHighlightGuideButton);
        missingObjectGuideButton = findViewById(R.id.missingObjectGuideButton);
        notInPreviousGuideButton = findViewById(R.id.notInPreviousGuideButton);

        brainTrainDatabase = new BrainTrainDatabase(MemoryActivity.this);
        gridsHighlightModel = new BrainTrainDAO().getProgressStatus(brainTrainDatabase, 11);

        gridsHighlightScore.setText("Điểm của bạn: " + gridsHighlightModel.getUserScore());
        gridsHighlightProgress.setText("Đã hoàn thành: " + ((float) gridsHighlightModel.getUserScore() / (float) gridsHighlightModel.getMaxScore()) + "%");
        if (gridsHighlightModel.isCompletedStatus()) {
            gridsHighlightProgress.setVisibility(View.GONE);
            gridsHighlightComplete.setVisibility(View.VISIBLE);
        }

        notInPreviousModel = new BrainTrainDAO().getProgressStatus(brainTrainDatabase, 12);
        notInPreviousScore.setText("Điểm của bạn: " + notInPreviousModel.getUserScore());
        notInPreviousProgress.setText("Đã hoàn thành: " + ((float) notInPreviousModel.getUserScore() / (float) notInPreviousModel.getMaxScore()) + "%");
        if (notInPreviousModel.isCompletedStatus()) {
            notInPreviousComplete.setVisibility(View.VISIBLE);
        }

        missingObjectModel = new BrainTrainDAO().getProgressStatus(brainTrainDatabase, 13);
        missingObjectCurrentScore = missingObjectModel.getUserScore();
        missingObjectScore.setText("Điểm của bạn: " + missingObjectCurrentScore);
        missingObjectProgress.setText("Đã hoàn thành: " + ((float) missingObjectModel.getUserScore() / (float) missingObjectModel.getMaxScore()) + "%");
        if (missingObjectModel.isCompletedStatus()) {
            missingObjectComplete.setVisibility(View.VISIBLE);
        }

        gridsHighlightCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MemoryActivity.this, GridsHighlightLevelMenuActivity.class));
                finish();
            }
        });

        notInPreviousCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MemoryActivity.this, NotInPreviousLevelMenuActivity.class));
                finish();
            }
        });

        missingObjectCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MemoryActivity.this, MissingObjectLevelMenuActivity.class));
                finish();
            }
        });

        gridsHighlightCardView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                gridsHighlightGuideButton.setVisibility(View.VISIBLE);
                return false;
            }
        });

        notInPreviousCardView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                notInPreviousGuideButton.setVisibility(View.VISIBLE);
                return false;
            }
        });

        missingObjectCardView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                missingObjectGuideButton.setVisibility(View.VISIBLE);
                return false;
            }
        });

        gridsHighlightGuideButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alert = new AlertDialog.Builder(MemoryActivity.this);
                alert.setTitle("Hướng Dẫn");
                alert.setMessage("Một ma trận biến đổi sẽ xuất hiện ngẫu nhiên với một mẫu các khối được hiển thị tạm thời (3 giây)\n\n" +
                        "Nhiệm vụ của người chơi là báo cáo vị trí của các khối bằng cách chạm vào vị trí của ma trận nơi các khối được hiển thị.");
                alert.setCancelable(false);

                alert.setNegativeButton("Không hiện lại", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        gridsHighlightGuideButton.setVisibility(View.GONE);
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

        missingObjectGuideButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alert = new AlertDialog.Builder(MemoryActivity.this);
                alert.setTitle("Hướng Dẫn");
                alert.setMessage("Nhiệm vụ của người chơi là tìm đồ vật trên tấm thẻ có dấu chấm hỏi");
                alert.setCancelable(false);

                alert.setNegativeButton("Không hiện lại", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        missingObjectGuideButton.setVisibility(View.GONE);
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

        notInPreviousGuideButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alert = new AlertDialog.Builder(MemoryActivity.this);
                alert.setTitle("Hướng Dẫn");
                alert.setMessage("Người chơi được chọn ngẫu nhiên 1 trong 3 thẻ này và phải ghi nhớ đồ vật trong thẻ đó\n" +
                        "\n" +
                        "Trong mỗi vòng, số lượng thẻ tăng lên 1 và người chơi phải chọn 1 thẻ có hình dạng khác không được chọn trước đó");
                alert.setCancelable(false);

                alert.setNegativeButton("Không hiện lại", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        notInPreviousGuideButton.setVisibility(View.GONE);
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