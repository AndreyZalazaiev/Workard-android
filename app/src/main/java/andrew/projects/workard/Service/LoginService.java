package andrew.projects.workard.Service;

import android.content.SharedPreferences;

import andrew.projects.workard.Domain.User;
import andrew.projects.workard.Repos.UserRepos;
import andrew.projects.workard.Util.Constants;
import lombok.val;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;


public class LoginService {
    Retrofit retrofit;

    public LoginService() {
        retrofit = new Retrofit.Builder()
                .baseUrl(Constants.baseUrl)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
    }

    public Call<String> login(String login, String pass) {

        val userApi = retrofit.create(UserRepos.class);

        return  userApi.logIn(new User(login, pass));
    }


    public User obtainProfile() {
        return null;
    }
}
