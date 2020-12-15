package andrew.projects.workard.Repos;

import java.util.List;

import andrew.projects.workard.Domain.Company;
import andrew.projects.workard.Domain.User;
import lombok.Getter;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface CompanyRepos {
    @GET("/company")
    Call<List<Company>> getCompanies(@Header("Authorization") String token);
}
