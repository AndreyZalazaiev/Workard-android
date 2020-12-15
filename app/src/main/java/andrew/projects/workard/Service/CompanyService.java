package andrew.projects.workard.Service;

import java.util.List;

import andrew.projects.workard.Domain.Company;
import andrew.projects.workard.Domain.User;
import andrew.projects.workard.Repos.CompanyRepos;
import andrew.projects.workard.Repos.UserRepos;
import andrew.projects.workard.Util.Constants;
import lombok.val;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class CompanyService {
    Retrofit retrofit;

    public CompanyService() {
        retrofit = new Retrofit.Builder()
                .baseUrl(Constants.baseUrl)
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
    }
    public Call<List<Company>> getCompanies(String authToken) {

        val companyApi = retrofit.create(CompanyRepos.class);

        return  companyApi.getCompanies("Bearer "+authToken);
    }
}
