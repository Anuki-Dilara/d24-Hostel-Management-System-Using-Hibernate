package lk.ijse.d24.bo.custom;

import lk.ijse.d24.bo.SuperBO;
import lk.ijse.d24.dto.UserDTO;

public interface LoginBO extends SuperBO {
    public boolean addUser(UserDTO userDTO) throws Exception;
    public boolean updateUser(UserDTO userDTO) throws Exception;
    public boolean removeUser(String id) throws Exception;
    public boolean isExist(String id) throws Exception;
    public UserDTO search(String id) throws Exception;
    public boolean userVerify(String username, String password) throws Exception;
}
