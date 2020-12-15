package andrew.projects.workard.Domain;

import lombok.Data;

@Data
public class User extends BaseEntity {
    private String username;
    private String password;
    private String email;
    private String emailConfirmation;

    public User(String login, String pass) {
        this.username=login;
        this.password=pass;
    }
}