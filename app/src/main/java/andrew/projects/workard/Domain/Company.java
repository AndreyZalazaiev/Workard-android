package andrew.projects.workard.Domain;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class Company extends BaseEntity {
    private Integer idUser;
    private String Name;
    private List<Room> rooms ;
    private List<Employee> employees;
    private List<Recommendation> recommendations;

}