package cn.knowbox.privacypolicy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import static cn.knowbox.privacypolicy.PrivacyPolicyActivity.KEY_PRIVACY_STATUS;

public class MainActivity extends AppCompatActivity {

    @Override
    public  void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AppPreferences.setBoolean(KEY_PRIVACY_STATUS, true);
    }
}