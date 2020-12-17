package andrew.projects.workard.Domain;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class Role extends BaseEntity {
    @SerializedName("role")
    private String role;

}