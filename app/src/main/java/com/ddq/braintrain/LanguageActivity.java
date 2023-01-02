package com.ddq.braintrain;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
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

    TextView completeWordScore, findWordScore, conjunctionScore, sortingCharScore;
    CardView completeWordCardView, findWordCardView, conjunctionCardView, sortingCharCardView;
    ImageView completeWordCompleted, findWordCompleted, conjunctionCompleted, sortingCharCompleted;

    private BrainTrainDatabase brainTrainDatabase;
    private ProgressModel completeWordModel, findWordModel, conjunctionModel, sortingCharModel;


    SharedPreferences sharedPreferences;
    String gameOneGuide, gameTwoGuide, gameThreeGuide, gameFourGuide;

    AppCompatButton completeWordGuideButton, findWordGuideButton, conjunctionGuideButton, sortingCharGuideButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language);

        completeWordScore = findViewById(R.id.completeWordScore);
        findWordScore = findViewById(R.id.findWordScore);
        conjunctionScore = findViewById(R.id.conjunctionScore);
        sortingCharScore = findViewById(R.id.sortingCharScore);
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


        sharedPreferences = getSharedPreferences("guideButton", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        gameOneGuide = sharedPreferences.getString("gameOneGuideLanguage", "");
        gameTwoGuide = sharedPreferences.getString("gameTwoGuideLanguage", "");
        gameThreeGuide = sharedPreferences.getString("gameThreeGuideLanguage", "");
        gameFourGuide = sharedPreferences.getString("gameFourGuideLanguage", "");


        completeWordGuideButton.setVisibility( gameOneGuide.isEmpty() ? View.VISIBLE: View.INVISIBLE);
        findWordGuideButton.setVisibility( gameTwoGuide.isEmpty() ? View.VISIBLE: View.INVISIBLE);
        conjunctionGuideButton.setVisibility( gameThreeGuide.isEmpty() ? View.VISIBLE: View.INVISIBLE);
        sortingCharGuideButton.setVisibility( gameFourGuide.isEmpty() ? View.VISIBLE: View.INVISIBLE);



        brainTrainDatabase = new BrainTrainDatabase(LanguageActivity.this);
        completeWordModel = new BrainTrainDAO().getProgressStatus(brainTrainDatabase, 31);
        completeWordScore.setText("Điểm của bạn: " + completeWordModel.getUserScore());
        if (completeWordModel.isCompletedStatus()) {
            completeWordCompleted.setVisibility(View.VISIBLE);
        }

        findWordModel = new BrainTrainDAO().getProgressStatus(brainTrainDatabase, 32);
        findWordScore.setText("Điểm của bạn: " + findWordModel.getUserScore());
        if (findWordModel.isCompletedStatus()) {
            findWordCompleted.setVisibility(View.VISIBLE);
        }

        conjunctionModel = new BrainTrainDAO().getProgressStatus(brainTrainDatabase, 33);
        conjunctionScore.setText("Điểm của bạn: " + conjunctionModel.getUserScore());
        if (conjunctionModel.isCompletedStatus()) {
            conjunctionCompleted.setVisibility(View.VISIBLE);
        }

        sortingCharModel = new BrainTrainDAO().getProgressStatus(brainTrainDatabase, 34);
        sortingCharScore.setText("Điểm của bạn: " + sortingCharModel.getUserScore());
        if (sortingCharModel.isCompletedStatus()) {
            sortingCharCompleted.setVisibility(View.VISIBLE);
        }
        completeWordCardView.setOnClickListener(LanguageActivity.this);
        findWordCardView.setOnClickListener(LanguageActivity.this);
        conjunctionCardView.setOnClickListener(LanguageActivity.this);
        sortingCharCardView.setOnClickListener(LanguageActivity.this);

        completeWordCardView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                completeWordGuideButton.setVisibility(View.VISIBLE);
                editor.putString("gameOneGuideLanguage", "");
                editor.apply();
                return false;
            }
        });

        findWordCardView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                findWordGuideButton.setVisibility(View.VISIBLE);
                editor.putString("gameTwoGuideLanguage", "");
                editor.apply();
                return false;
            }
        });

        conjunctionCardView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                conjunctionGuideButton.setVisibility(View.VISIBLE);
                editor.putString("gameThreeGuideLanguage", "");editor.apply();
                return false;
            }
        });

        sortingCharCardView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                sortingCharGuideButton.setVisibility(View.VISIBLE);
                editor.putString("gameFourGuideLanguage", "");editor.apply();
                return false;
            }
        });

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

                alert.setNegativeButton("Không hiện lại", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        completeWordGuideButton.setVisibility(View.GONE);
                        editor.putString("gameOneGuideLanguage", "notAppear");
                        editor.apply();
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

        findWordGuideButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alert = new AlertDialog.Builder(LanguageActivity.this);
                alert.setTitle("Hướng Dẫn");
                alert.setMessage("Trong vòng 2 phút, nhiệm vụ là tìm những từ có thể ghép với từ cho sẵn ban đầu thành từ ghép có nghĩa");
                alert.setCancelable(false);

                alert.setNegativeButton("Không hiện lại", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        findWordGuideButton.setVisibility(View.GONE);
                        editor.putString("gameTwoGuideLanguage", "notAppear");
                        editor.apply();
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

        conjunctionGuideButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alert = new AlertDialog.Builder(LanguageActivity.this);
                alert.setTitle("Hướng Dẫn");
                alert.setMessage("Người dùng tiếp tục tìm một từ khác để nối với từ cuối cùng trong từ ghép đã tìm được trước đó để tạo từ ghép có nghĩa mới\n" +
                        "\n" +
                        "Tiếp tục làm điều này cho đến khi không thể tìm thấy nhiều từ hơn để phù hợp");
                alert.setCancelable(false);

                alert.setNegativeButton("Không hiện lại", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        conjunctionGuideButton.setVisibility(View.GONE);
                        editor.putString("gameThreeGuideLanguage", "notAppear");
                        editor.apply();
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

        sortingCharGuideButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alert = new AlertDialog.Builder(LanguageActivity.this);
                alert.setTitle("Hướng Dẫn");
                alert.setMessage("Trò chơi này sẽ cung cấp một cụm từ có các chữ cái được xáo trộn\n" +
                        "\n" +
                        "Nhiệm vụ của người chơi là sắp xếp lại thứ tự các chữ cái để tìm ra từ chính xác");
                alert.setCancelable(false);

                alert.setNegativeButton("Không hiện lại", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        sortingCharGuideButton.setVisibility(View.GONE);
                        editor.putString("gameFourGuideLanguage", "notAppear");
                        editor.apply();
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
        finish();
    }

}