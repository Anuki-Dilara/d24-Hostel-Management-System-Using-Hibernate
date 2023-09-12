package lk.ijse.d24.bo;


import lk.ijse.d24.bo.custom.impl.*;

public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory(){}

    public static BOFactory getBoFactory(){
        return boFactory == null ? (boFactory = new BOFactory()) : boFactory;
    }

    public enum BOTypes {
        STUDENT, ROOM, RESERVATION, FIND, DASHBOARD, LOGIN
    }

    public SuperBO getBO(BOTypes type){
        switch (type){
            case STUDENT: return new StudentBOImpl();
            case ROOM: return new RoomBOImpl();
            case RESERVATION: return new ReservationBOImpl();
            case FIND: return new FindBOImpl();
            case DASHBOARD: return new DashBoardBoImpl();
            case LOGIN: return new LoginBOImpl();
            default: return null;
        }
    }
}
