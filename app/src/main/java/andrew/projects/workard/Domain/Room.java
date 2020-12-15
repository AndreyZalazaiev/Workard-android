package andrew.projects.workard.Domain;

import lombok.Data;

@Data
public class Room extends BaseEntity {
    private Integer idCompany;
    private String name;
    private Integer recommendedValue;
    private String extra;

}
