package andrew.projects.workard.Domain;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class Device {
    @SerializedName("deviceCode")
    String deviceCode;
    @SerializedName("idRoom")
    Integer idRoom;
}
