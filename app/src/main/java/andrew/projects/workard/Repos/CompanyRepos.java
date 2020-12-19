package andrew.projects.workard.Repos;

import java.util.List;

import andrew.projects.workard.Domain.Company;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface CompanyRepos {
    @GET("/company")
    Call<List<Company>> getCompanies(@Header("Authorization") String token);
}
