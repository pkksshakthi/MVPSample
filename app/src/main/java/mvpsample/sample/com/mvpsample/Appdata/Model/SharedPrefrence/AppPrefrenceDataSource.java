package mvpsample.sample.com.mvpsample.Appdata.Model.SharedPrefrence;

import android.content.Context;
import android.content.SharedPreferences;

import mvpsample.sample.com.mvpsample.Appdata.Model.AppdataSource;

public class AppPrefrenceDataSource implements AppdataSource {
    private SharedPreferences sharedPreferences;

    public AppPrefrenceDataSource(Context context) {
        sharedPreferences = context.getSharedPreferences("Sample", Context.MODE_PRIVATE);
    }

    @Override
    public void SaveIsLogedIn(boolean isLoggedIn) {
        sharedPreferences.edit().putBoolean("isLoggedIn", isLoggedIn).commit();

    }

    @Override
    public boolean isLoggedIn() {
        return sharedPreferences.getBoolean("isLoggedIn", false);
    }
}
