package mvpsample.sample.com.mvpsample.Login;

import android.service.voice.VoiceInteractionService;

import mvpsample.sample.com.mvpsample.BasePresenter;
import mvpsample.sample.com.mvpsample.BaseView;

public interface LoginContracter {
    interface View extends BaseView {

        void ShowHomeActivity();

        void EmailEmptyError();

        void NotValidaEmail();

        void showPasswordEmptyError();


    }

    interface Presenter extends BasePresenter{
        void LoginCheck(String email, String passwor);
    }
}
