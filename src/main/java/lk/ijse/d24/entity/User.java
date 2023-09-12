package lk.ijse.d24.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@Entity
@AllArgsConstructor
public class User {
    @Id
    private String id;
    private String username;
    private String password;

    public User(){}
}
