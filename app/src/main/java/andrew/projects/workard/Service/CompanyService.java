package andrew.projects.workard.Service;

import com.fasterxml.jackson.databind.deser.std.DateDeserializers;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import andrew.projects.workard.Domain.Company;
import andrew.projects.workard.Domain.User;
import andrew.projects.workard.Repos.CompanyRepos;
import andrew.projects.workard.Repos.UserRepos;
import andrew.projects.workard.Util.Constants;
import lombok.val;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;

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

    Gson gson = new GsonBuilder()
            .registerTypeAdapter(LocalDateTime.class,new DateTypeDeserializer())
            .create();

}
