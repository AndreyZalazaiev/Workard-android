package andrew.projects.workard.Repos;

import java.util.ArrayList;

import andrew.projects.workard.Domain.DTO.HotSpotsDTO;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface VisitRepos {
    @GET("/visit/hotspot")
    Call<ArrayList<HotSpotsDTO>> getHotSpots(@Header("Authorization") String token, @Query("idCompany") int idCompany);
}
