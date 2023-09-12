package lk.ijse.d24.dto.tm;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class StudentTM {
    private String student_id;
    private String name;
    private String address;
    private String contact;
    private LocalDate dob;
    private String gender;
    public StudentTM(){}
}
