package com.ddq.braintrain;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.ddq.braintrain.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private String userName;
    SignInActivity signInActivity;
    BrainTrainDatabase brainTrainDatabase;
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        brainTrainDatabase = new BrainTrainDatabase(this);
        copyDatabase();

        setContentView(binding.getRoot());
        replaceFragment(new HomeFragment());

        binding.navBar.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.home:
                    replaceFragment(new HomeFragment());
                    break;
                case R.id.profile:
                        replaceFragment(new ProfileFragment());
                    break;
                case R.id.setting:
                    replaceFragment(new SettingFragment());
                    break;
                default:
            }
            return true;
        });
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }

    public void copyDatabase() {
        try {
            DatabaseCopyHelper databaseCopyHelper = new DatabaseCopyHelper(MainActivity.this);
            databaseCopyHelper.createDataBase();
            databaseCopyHelper.openDataBase();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}