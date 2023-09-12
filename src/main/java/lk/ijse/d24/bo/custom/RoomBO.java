package lk.ijse.d24.bo.custom;


import lk.ijse.d24.bo.SuperBO;
import lk.ijse.d24.dto.RoomDTO;

import java.util.List;

public interface RoomBO extends SuperBO {
    public boolean add(RoomDTO room)throws Exception;

    boolean update(RoomDTO room)throws Exception;

    boolean delete(String text)throws Exception;

    RoomDTO setFields(String text)throws Exception;

    List<RoomDTO> getAll() throws Exception;
}
