package andrew.projects.workard.Repos;


import andrew.projects.workard.Domain.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface UserRepos {
    @POST("/login")
    Call<String> logIn(@Body User user);
    @GET("/profile")
    Call<User> getProfile(@Header("Authorization") String auth);
}
