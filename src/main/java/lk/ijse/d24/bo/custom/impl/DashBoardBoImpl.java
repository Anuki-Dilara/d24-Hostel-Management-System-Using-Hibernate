package lk.ijse.d24.bo.custom.impl;

import lk.ijse.d24.bo.custom.DashBoardBO;
import lk.ijse.d24.dao.DAOFactory;
import lk.ijse.d24.dao.custom.ReservationDAO;
import lk.ijse.d24.dao.custom.RoomDAO;
import lk.ijse.d24.dao.custom.StudentDAO;
import lk.ijse.d24.dao.custom.UserDAO;
import lk.ijse.d24.dto.UserDTO;
import lk.ijse.d24.entity.Room;
import lk.ijse.d24.entity.User;

import java.util.List;

public class DashBoardBoImpl implements DashBoardBO {
    private ReservationDAO reservationDAO = (ReservationDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.RESERVATION);
    private RoomDAO roomDAO = (RoomDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ROOM);
    private StudentDAO studentDAO = (StudentDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.STUDENT);
    private UserDAO userDAO = (UserDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.USER);
    @Override
    public int getAllReservationCount() throws Exception {
        return reservationDAO.getAll().size();
    }

    @Override
    public int getTotOfAvailableRoomsCount() throws Exception {
        List<Room> all = roomDAO.getAll();

        int totRoomsAvailable = 0;
        for (Room room : all) {
            totRoomsAvailable += room.getQty();
        }

        return totRoomsAvailable;
    }

    @Override
    public int getREGStuCount() throws Exception {
        return studentDAO.getAll().size();
    }

    @Override
    public UserDTO getUser(UserDTO dto) throws Exception {
        User user = userDAO.search(dto.getUsername(), dto.getPassword());
        return new UserDTO(
                user.getId(),
                user.getUsername(),
                user.getPassword()
        );
    }

    @Override
    public boolean updateUser(UserDTO dto) throws Exception {
        return userDAO.update(new User(dto.getId(), dto.getUsername(), dto.getPassword()));
    }
}
