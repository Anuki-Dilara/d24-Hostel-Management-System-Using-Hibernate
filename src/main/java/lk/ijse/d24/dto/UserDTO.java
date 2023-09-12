package lk.ijse.d24.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDTO {
    private String id;
    private String username;
    private String password;

    public UserDTO(){}
}
