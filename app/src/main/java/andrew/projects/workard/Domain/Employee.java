package andrew.projects.workard.Domain;

import java.util.List;

import lombok.Data;

@Data
public class Employee extends BaseEntity {
    private String name;
    private String occupation;
    private String RFIDtag;
    private Integer idCompany;
    private List<Visit> visits;
}
