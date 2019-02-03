package mvpsample.sample.com.mvpsample.Appdata.Model;

public class AppRepositiry implements AppdataSource {
    public AppdataSource mSharedPrefrence;
    public AppRepositiry(AppdataSource appdataSource){
        mSharedPrefrence=appdataSource;
    }

    @Override
    public void SaveIsLogedIn(boolean isLoggedIn) {
        mSharedPrefrence.SaveIsLogedIn(isLoggedIn);
    }

    @Override
    public boolean isLoggedIn() {
        return mSharedPrefrence.isLoggedIn();
    }
}
