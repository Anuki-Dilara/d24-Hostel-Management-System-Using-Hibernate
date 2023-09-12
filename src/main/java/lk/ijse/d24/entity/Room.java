package lk.ijse.d24.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
public class Room {
    @Id
    private String room_type_id;
    private String type;
    private String key_money;
    private Integer qty;

    @OneToMany(mappedBy = "room", fetch = FetchType.EAGER)
    private List<Reservation> reservations;

    public Room() {

    }

    public Room(String room_type_id, String type, String key_money, Integer qty) {
        this.room_type_id = room_type_id;
        this.type = type;
        this.key_money = key_money;
        this.qty = qty;
        this.reservations = new ArrayList<>(); // Initialize the list
    }

}
