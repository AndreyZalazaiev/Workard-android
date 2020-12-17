package andrew.projects.workard.Domain;


import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class BaseEntity {
    @SerializedName("id")
    private Integer id;
}
