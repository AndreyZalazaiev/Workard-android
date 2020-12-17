package andrew.projects.workard.Domain;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Data;

@Data
public class User extends BaseEntity {
    @SerializedName("username")
    private String username;
    @SerializedName("password")
    private String password;
    @SerializedName("email")
    private String email;
    @SerializedName("emailConfirmation")
    private String emailConfirmation;
    @SerializedName("authorities")
    private List<Role> authorities ;
    @SerializedName("companies")
    private List<Company> companies ;

    public User(String login, String pass) {
        this.username=login;
        this.password=pass;
    }
}