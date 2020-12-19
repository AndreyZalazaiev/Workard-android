package andrew.projects.workard.presenter;

import andrew.projects.workard.Domain.User;
import andrew.projects.workard.Service.LoginService;
import andrew.projects.workard.view.NavView;
import moxy.InjectViewState;
import moxy.MvpPresenter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@InjectViewState
public class NavPresenter extends MvpPresenter<NavView> {
    LoginService loginService =  new LoginService();
    String authToken;
    public NavPresenter(String authToken) {
        this.authToken=authToken;
        loadProfileData();
    }
    public void loadProfileData(){
        loginService.getProfile(authToken).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                getViewState().getProfileData(response.body());
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                getViewState().getProfileData(new User("Unkown","111","no-data"));
            }

        });
    }
}
