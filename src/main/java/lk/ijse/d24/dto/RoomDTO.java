package lk.ijse.d24.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class RoomDTO {
    private String room_type_id;
    private String type;
    private String key_money;
    private Integer qty;
    private List<ReservationDTO> reservations;

    public RoomDTO() {

    }
    public RoomDTO(String room_type_id, String type, String key_money, Integer qty) {
        this.room_type_id = room_type_id;
        this.type = type;
        this.key_money=key_money;
        this.qty= qty;
    }
}
