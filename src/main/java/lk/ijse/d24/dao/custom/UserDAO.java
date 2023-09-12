package lk.ijse.d24.dao.custom;


import lk.ijse.d24.dao.CrudDAO;
import lk.ijse.d24.entity.User;

public interface UserDAO extends CrudDAO<User> {
    public boolean verifyUser(String username, String password) throws Exception;
    public User search(String username, String password) throws Exception;

    String generateNewID() throws Exception;
}
