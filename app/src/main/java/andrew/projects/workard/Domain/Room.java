package andrew.projects.workard.Domain;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Data;

@Data
public class Room extends BaseEntity {
    @SerializedName("idCompany")
    private Integer idCompany;
    @SerializedName("name")
    private String name;
    @SerializedName("recommendedValue")
    private Integer recommendedValue;
    @SerializedName("extra")
    private String extra;
    @SerializedName("visits")
    private List<Visit> visits;
    @SerializedName("devices")
    private List<Device> devices ;
}
