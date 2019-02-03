package mvpsample.sample.com.mvpsample.Login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

import mvpsample.sample.com.mvpsample.Appdata.Model.AppRepositiry;
import mvpsample.sample.com.mvpsample.Appdata.Model.AppdataSource;
import mvpsample.sample.com.mvpsample.Appdata.Model.SharedPrefrence.AppPrefrenceDataSource;
import mvpsample.sample.com.mvpsample.Home.HomeActivity;
import mvpsample.sample.com.mvpsample.R;

public class MainActivity extends AppCompatActivity implements LoginContracter.View {

    private EditText email;
    private EditText password;
    private Button Login;
    LoginPresenter loginPresenter;


    private InterstitialAd mInterstitialAd;
    AppRepositiry appRepositiry;
    AppdataSource appdataSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        email = (EditText) findViewById(R.id.editText);
        password = (EditText) findViewById(R.id.editText2);
        Login = (Button) findViewById(R.id.button);
        appdataSource=new AppPrefrenceDataSource(this);
        appRepositiry=new AppRepositiry(appdataSource);
        loginPresenter=new LoginPresenter(this,appRepositiry);

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginPresenter.LoginCheck(email.getText().toString(),password.getText().toString());
                showInterstitial();
            }
        });
    }
    private void showInterstitial() {
        // Show the ad if it's ready. Otherwise toast and reload the ad.
        if (mInterstitialAd != null && mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        } else {
            Toast.makeText(this, "Ad did not load", Toast.LENGTH_SHORT).show();
            goToNextLevel();
        }
    }


    @Override
    public void ShowHomeActivity() {
        startActivity(new Intent(this, HomeActivity.class));
        finish();
    }

    @Override
    public void EmailEmptyError() {
        Toast.makeText(this, "EmailEmptyError", Toast.LENGTH_LONG).show();
    }

    @Override
    public void NotValidaEmail() {
        Toast.makeText(this, "NotValidaEmail", Toast.LENGTH_LONG).show();
    }

    @Override
    public void showPasswordEmptyError() {
        Toast.makeText(this, "showPasswordEmptyError", Toast.LENGTH_LONG).show();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }
    private InterstitialAd newInterstitialAd() {
        InterstitialAd interstitialAd = new InterstitialAd(this);
        interstitialAd.setAdUnitId("ca-app-pub-7400522618229994/955056907");
        interstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                Login.setEnabled(true);
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                Login.setEnabled(true);
            }

            @Override
            public void onAdClosed() {
                // Proceed to the next level.
                goToNextLevel();
            }
        });
        return interstitialAd;
    }


    private void loadInterstitial() {
        // Disable the next level button and load the ad.
        Login.setEnabled(false);
        AdRequest adRequest = new AdRequest.Builder()
                .setRequestAgent("android_studio:ad_template").build();
        mInterstitialAd.loadAd(adRequest);
    }

    private void goToNextLevel() {

        mInterstitialAd = newInterstitialAd();
        loadInterstitial();
    }
}
