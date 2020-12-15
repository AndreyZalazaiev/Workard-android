package andrew.projects.workard.Repos;


import andrew.projects.workard.Domain.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserRepos {
    @POST("/login")
    Call<String> logIn(@Body User user);
}
