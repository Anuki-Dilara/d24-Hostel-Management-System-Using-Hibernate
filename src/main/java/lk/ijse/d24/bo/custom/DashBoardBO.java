package lk.ijse.d24.bo.custom;

import lk.ijse.d24.bo.SuperBO;
import lk.ijse.d24.dto.UserDTO;

public interface DashBoardBO extends SuperBO {
    int getAllReservationCount() throws Exception;

    int getTotOfAvailableRoomsCount() throws Exception;

    int getREGStuCount()throws Exception;

    public UserDTO getUser(UserDTO dto) throws Exception;
    public boolean updateUser(UserDTO dto) throws Exception;
}
