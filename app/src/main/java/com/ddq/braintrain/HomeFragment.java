package com.ddq.braintrain;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.viewmodel.CreationExtras;

import com.ddq.braintrain.models.ProgressModel;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment implements View.OnClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private static int memory, attention, language, math;

    private static CardView attentionCardView, memoryCardView, mathCardView, languageCardView;
    private ProgressBar memoryProgress, attentionProgress, mathProgress, languageProgress;

    private BrainTrainDatabase brainTrainDatabase;
    ProgressModel progressModel;


    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        memoryCardView = view.findViewById(R.id.memoryCardView);
        attentionCardView = view.findViewById(R.id.attentionCardView);
        mathCardView = view.findViewById(R.id.mathCardView);
        languageCardView = view.findViewById(R.id.languageCardView);

        memoryCardView.setOnClickListener(this);
        attentionCardView.setOnClickListener(this);
        mathCardView.setOnClickListener(this);
        languageCardView.setOnClickListener(this);

        memoryProgress = view.findViewById(R.id.memoryProgress);
        attentionProgress = view.findViewById(R.id.attentionProgress);
        mathProgress = view.findViewById(R.id.mathProgress);
        languageProgress = view.findViewById(R.id.languageProgress);

        brainTrainDatabase = new BrainTrainDatabase(getActivity());
        int memoryUserScore = (new BrainTrainDAO().getProgressStatus(brainTrainDatabase, 11)).getUserScore()
                + (new BrainTrainDAO().getProgressStatus(brainTrainDatabase, 12)).getUserScore() +
                (new BrainTrainDAO().getProgressStatus(brainTrainDatabase, 13)).getUserScore() ;
        int memoryMaxScore = (new BrainTrainDAO().getProgressStatus(brainTrainDatabase, 11)).getMaxScore()
                + (new BrainTrainDAO().getProgressStatus(brainTrainDatabase, 12)).getMaxScore() +
                (new BrainTrainDAO().getProgressStatus(brainTrainDatabase, 13)).getMaxScore() ;
        memoryProgress.setProgress((100*memoryUserScore)/memoryMaxScore);
        memory = memoryProgress.getProgress();

        int attentionUserScore = (new BrainTrainDAO().getProgressStatus(brainTrainDatabase, 21)).getUserScore()
                + (new BrainTrainDAO().getProgressStatus(brainTrainDatabase, 221)).getUserScore() +
                 (new BrainTrainDAO().getProgressStatus(brainTrainDatabase, 222)).getUserScore() +
                 (new BrainTrainDAO().getProgressStatus(brainTrainDatabase, 223)).getUserScore() +
                (new BrainTrainDAO().getProgressStatus(brainTrainDatabase, 23)).getUserScore() ;
        int attentionMaxScore = (new BrainTrainDAO().getProgressStatus(brainTrainDatabase, 21)).getMaxScore()
                + (new BrainTrainDAO().getProgressStatus(brainTrainDatabase, 221)).getMaxScore() +
                 (new BrainTrainDAO().getProgressStatus(brainTrainDatabase, 222)).getMaxScore() +
                 (new BrainTrainDAO().getProgressStatus(brainTrainDatabase, 223)).getMaxScore() +
                (new BrainTrainDAO().getProgressStatus(brainTrainDatabase, 23)).getMaxScore() ;
        attentionProgress.setProgress((100*attentionUserScore)/attentionMaxScore);
        attention = attentionProgress.getProgress();

        int languageUserScore = (new BrainTrainDAO().getProgressStatus(brainTrainDatabase, 31)).getUserScore() > 0 ? 1:0
                + (new BrainTrainDAO().getProgressStatus(brainTrainDatabase, 32)).getUserScore() > 0 ? 1:0+
                + (new BrainTrainDAO().getProgressStatus(brainTrainDatabase, 33)).getUserScore() > 0 ? 1:0+
                (new BrainTrainDAO().getProgressStatus(brainTrainDatabase, 34)).getUserScore() > 0 ? 1:0;
        Log.d(TAG, "onCreateView: language user score: "+ languageUserScore);
        languageProgress.setProgress((100*languageUserScore)/4);
        language = languageProgress.getProgress();

        int mathUserScore = (new BrainTrainDAO().getProgressStatus(brainTrainDatabase, 41)).getUserScore()
                + (new BrainTrainDAO().getProgressStatus(brainTrainDatabase, 421)).getUserScore() +
                + (new BrainTrainDAO().getProgressStatus(brainTrainDatabase, 422)).getUserScore() +
                (new BrainTrainDAO().getProgressStatus(brainTrainDatabase, 423)).getUserScore() ;
        int mathMaxScore = (new BrainTrainDAO().getProgressStatus(brainTrainDatabase, 41)).getMaxScore()
                + (new BrainTrainDAO().getProgressStatus(brainTrainDatabase, 421)).getMaxScore() +
                + (new BrainTrainDAO().getProgressStatus(brainTrainDatabase, 422)).getMaxScore() +
                (new BrainTrainDAO().getProgressStatus(brainTrainDatabase, 423)).getMaxScore() ;
        mathProgress.setProgress((100*mathUserScore)/mathMaxScore);
        math = mathProgress.getProgress();
/*

/*
        memoryCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MemoryActivity.class);
                startActivity(intent);
            }
        });
        attentionCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AttentionActivity.class);
                startActivity(intent);
            }
        });
        mathCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MathActivity.class);
                startActivity(intent);
            }
        });
        languageCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), LanguageActivity.class);
                startActivity(intent);
            }
        });
*/
        return view;
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {

            case R.id.memoryCardView:
                intent = new Intent(getActivity(), MemoryActivity.class);
                break;
            case R.id.attentionCardView:
                intent = new Intent(getActivity(), AttentionActivity.class);
                break;
            case R.id.languageCardView:
                intent = new Intent(getActivity(), LanguageActivity.class);
                break;
            default:
                intent = new Intent(getActivity(), MathActivity.class);
                break;
        }
        startActivity(intent);
    }

    @NonNull
    @Override
    public CreationExtras getDefaultViewModelCreationExtras() {
        return super.getDefaultViewModelCreationExtras();
    }

    public static int getTotalProgress(){
        return (memory + attention + language + math) / 4;
    }
}