package mvpsample.sample.com.mvpsample.Login;

import android.util.Patterns;

import mvpsample.sample.com.mvpsample.Appdata.Model.AppRepositiry;

public class LoginPresenter implements LoginContracter.Presenter {
    LoginContracter.View mLoginView;
    AppRepositiry appRepositiry;

    public LoginPresenter(LoginContracter.View mLoginVie, AppRepositiry mappRepositiry) {
        mLoginView = mLoginVie;
        appRepositiry = mappRepositiry;
    }

    @Override
    public void LoginCheck(String email, String passwor) {
        if (email != null && email.length() == 0) {
            mLoginView.EmailEmptyError();
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            mLoginView.NotValidaEmail();
        } else if (email != null && email.length() == 0) {
            mLoginView.showPasswordEmptyError();
        } else {
            appRepositiry.SaveIsLogedIn(true);
            mLoginView.ShowHomeActivity();

        }
    }

    @Override
    public void start() {

    }
}
