package andrew.projects.workard.Domain;


import java.time.LocalDateTime;

import lombok.Data;

@Data
public class Visit extends BaseEntity {

    private Integer idRoom;
    private Integer idEmployee;
    private LocalDateTime entryTime;
    private LocalDateTime exitTime;
}
