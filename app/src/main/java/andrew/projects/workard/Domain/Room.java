package andrew.projects.workard.Domain;

import java.util.List;

import lombok.Data;

@Data
public class Room extends BaseEntity {
    private Integer idCompany;
    private String name;
    private Integer recommendedValue;
    private String extra;

    private List<Visit> visits;

    private List<Device> devices ;
}
