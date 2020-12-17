package andrew.projects.workard.Domain;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.gson.annotations.SerializedName;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class Recommendation extends BaseEntity {
    @SerializedName("idCompany")
    Integer idCompany;
    @SerializedName("text")
    String text;
    @SerializedName("date")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    LocalDateTime date;
}
