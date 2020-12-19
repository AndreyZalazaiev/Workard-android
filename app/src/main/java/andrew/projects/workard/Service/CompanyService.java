package andrew.projects.workard.Service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import andrew.projects.workard.Domain.Company;
import andrew.projects.workard.Domain.DTO.HotSpotsDTO;
import andrew.projects.workard.Repos.CompanyRepos;
import andrew.projects.workard.Repos.VisitRepos;
import andrew.projects.workard.Util.Constants;
import lombok.val;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CompanyService {
    Retrofit retrofit;

    public CompanyService() {
        retrofit = new Retrofit.Builder()
                .baseUrl(Constants.baseUrl)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }
    public Call<List<Company>> getCompanies(String authToken) {

        val companyApi = retrofit.create(CompanyRepos.class);

        return  companyApi.getCompanies("Bearer "+authToken);
    }
    public Call<ArrayList<HotSpotsDTO>> getHotSpots(String authToken, int idCompany){
        val companyApi = retrofit.create(VisitRepos.class);

        return  companyApi.getHotSpots("Bearer "+authToken,idCompany);
    }

    Gson gson = new GsonBuilder()
            .registerTypeAdapter(LocalDateTime.class,new DateTypeDeserializer())
            .create();

}
