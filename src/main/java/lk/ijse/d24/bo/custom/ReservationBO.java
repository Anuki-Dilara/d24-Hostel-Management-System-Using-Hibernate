package lk.ijse.d24.bo.custom;


import lk.ijse.d24.bo.SuperBO;
import lk.ijse.d24.dto.ReservationDTO;
import lk.ijse.d24.dto.RoomDTO;
import lk.ijse.d24.dto.StudentDTO;

import java.util.List;

public interface ReservationBO extends SuperBO {
    String setReservationID()throws Exception;

    RoomDTO getRooms(String value)throws Exception;

    List<StudentDTO> getStudents()throws Exception;

    boolean registerStudent(ReservationDTO reservation)throws Exception;

    List<RoomDTO> getAllRooms()throws Exception;

    List<ReservationDTO> getAllDetails()throws Exception;

    ReservationDTO setFields(String text)throws Exception;

    boolean UpdateStudent(ReservationDTO reservation)throws Exception;
}
