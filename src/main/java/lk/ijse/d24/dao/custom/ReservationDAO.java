package lk.ijse.d24.dao.custom;


import lk.ijse.d24.dao.CrudDAO;
import lk.ijse.d24.entity.Reservation;

public interface ReservationDAO extends CrudDAO<Reservation> {
    String getNextID()throws Exception;
    String getNewID(String lastId)throws Exception;
}
