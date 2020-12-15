package andrew.projects.workard.Domain;


import java.time.LocalDateTime;

import lombok.Data;

@Data
public class Recommendation extends BaseEntity {
    Integer idCompany;
    String text;
    LocalDateTime date;
}
