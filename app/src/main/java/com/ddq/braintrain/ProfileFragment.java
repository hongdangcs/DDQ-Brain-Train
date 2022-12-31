package com.ddq.braintrain;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.ddq.braintrain.gameactivity.SortingCharGameActivity;
import com.ddq.braintrain.models.AccountModel;
import com.ddq.braintrain.models.SortingCharGameModel;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {
    private String user;
    SignInActivity signInActivity;
    TextView userName, dob, gender, personal_id, password;
    private BrainTrainDatabase brainTrainDatabase;
    private static List<AccountModel> accountModels;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
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
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        userName = view.findViewById(R.id.userName);
        password = view.findViewById(R.id.password);
        dob = view.findViewById(R.id.dob);
        personal_id = view.findViewById(R.id.personal_id);
        gender = view.findViewById(R.id.gender);
        brainTrainDatabase = new BrainTrainDatabase(getActivity());
        accountModels = new BrainTrainDAO().accountModels(brainTrainDatabase);

        user = signInActivity.getUser();

        for (AccountModel accountModel: accountModels) {
            String temp = accountModel.getUserName();
            if (user.equals(temp)){
                userName.setText("" + temp);
                password.setText("" + accountModel.getPassword());
                gender.setText("" + accountModel.getGender());
                dob.setText("" + accountModel.getDob());
                personal_id.setText("" + accountModel.getPersonal_id());
                break;
            }
        }
        // Inflate the layout for this fragment
        return view;
    }

    public void SignOut(View view) {
        Intent intent = new Intent(getActivity(), SignInActivity.class);
        startActivity(intent);
        getActivity().finish();
    }
}