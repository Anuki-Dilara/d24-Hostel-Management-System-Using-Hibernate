package lk.ijse.d24.dao.custom;


import lk.ijse.d24.dao.CrudDAO;
import lk.ijse.d24.entity.Room;

public interface RoomDAO extends CrudDAO<Room> {
    Room setFields(String text)throws Exception;

    Room getRoom(String value) throws Exception;
}
