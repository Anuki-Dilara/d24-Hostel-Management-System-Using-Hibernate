package lk.ijse.d24.dto.tm;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class NonPayedStudentsTM {
    private String studentId;
    private String studentName;
    private String address;
    private String contactNo;
    private Double keyMoney;
    private String resId;
    private String roomId;

    public NonPayedStudentsTM(){}
}
