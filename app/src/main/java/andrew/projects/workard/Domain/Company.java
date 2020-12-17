package andrew.projects.workard.Domain;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class Company extends BaseEntity implements Serializable {
    @SerializedName("idUser")
    private Integer idUser;
    @SerializedName("name")
    private String Name;
    @SerializedName("rooms")
    private List<Room> rooms ;
    @SerializedName("employees")
    private List<Employee> employees;
    @SerializedName("recommendations")
    private List<Recommendation> recommendations;

}