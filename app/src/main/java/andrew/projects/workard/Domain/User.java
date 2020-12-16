package andrew.projects.workard.Domain;

import java.util.List;

import lombok.Data;

@Data
public class User extends BaseEntity {
    private String username;
    private String password;
    private String email;
    private String emailConfirmation;

    private List<Role> authorities ;

    private List<Company> companies ;

    public User(String login, String pass) {
        this.username=login;
        this.password=pass;
    }
}