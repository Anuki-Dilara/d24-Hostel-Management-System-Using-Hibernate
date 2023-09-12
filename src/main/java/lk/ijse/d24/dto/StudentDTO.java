package lk.ijse.d24.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
public class StudentDTO {

    private String student_id;
    private String name;
    private String address;
    private String contact;
    private LocalDate dob;
    private String gender;
    private List<ReservationDTO> reservations;

    public StudentDTO() {}

    public StudentDTO(String id, String name, String address, String contactNo, LocalDate dob, String gender) {
        this.student_id = id;
        this.name = name;
        this.address = address;
        this.contact = contactNo;
        this.dob = dob;
        this.gender = gender;
    }
}
