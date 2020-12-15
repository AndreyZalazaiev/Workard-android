package andrew.projects.workard.Domain;

import lombok.Data;

@Data
public class Company extends BaseEntity {
    private Integer idUser;
    private String Name;

}