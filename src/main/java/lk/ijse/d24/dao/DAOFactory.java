package lk.ijse.d24.dao;


import lk.ijse.d24.dao.custom.impl.*;

public class DAOFactory {
    public static DAOFactory daoFactory;

    private DAOFactory(){}

    public static DAOFactory getDaoFactory(){
        return daoFactory == null ? (daoFactory = new DAOFactory()) : daoFactory;
    }

    public enum DAOTypes{
        STUDENT, ROOM, RESERVATION, QUERY, USER
    }

    public SuperDAO getDAO(DAOTypes type){
        switch (type){
            case STUDENT: return new StudentDAOImpl();
            case ROOM: return new RoomDAOImpl();
            case RESERVATION: return new ReservationDAOImpl();
            case QUERY: return new QueryDAOImpl();
            case USER: return new UserDAOImpl();
            default: return null;
        }
    }
}
