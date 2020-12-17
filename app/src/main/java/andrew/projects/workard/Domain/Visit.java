package andrew.projects.workard.Domain;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.gson.annotations.SerializedName;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import lombok.Data;

@Data
public class Visit extends BaseEntity {
    @SerializedName("idRoom")
    private Integer idRoom;
    @SerializedName("idEmployee")
    private Integer idEmployee;
    @SerializedName("entryTime")

    private LocalDateTime entryTime;
    @SerializedName("exitTime")
    private LocalDateTime exitTime;
}
