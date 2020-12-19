package andrew.projects.workard.Domain;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Data;

@Data
public class Employee extends BaseEntity {
    @SerializedName("name")
    private String name;
    @SerializedName("occupation")
    private String occupation;
    @SerializedName("rfidtag")
    private String RFIDtag;
    @SerializedName("idCompany")
    private Integer idCompany;
    @SerializedName("visits")
    private List<Visit> visits;
}
