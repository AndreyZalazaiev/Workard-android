package andrew.projects.workard.Domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
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
    @JsonIgnore
    private List<Role> authorities ;
    @SerializedName("companies")
    @JsonIgnore
    private List<Company> companies ;

    public User(String login, String pass) {
        this.username=login;
        this.password=pass;
    }

    public User(String unkown, String s, String email) {
        User u = new User();
        u.setUsername(unkown);
        u.setPassword(s);
        u.setEmail(email);
    }
}