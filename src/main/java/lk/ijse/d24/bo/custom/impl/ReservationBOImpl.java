package lk.ijse.d24.bo.custom.impl;

import lk.ijse.d24.bo.custom.ReservationBO;
import lk.ijse.d24.dao.DAOFactory;
import lk.ijse.d24.dao.custom.ReservationDAO;
import lk.ijse.d24.dao.custom.RoomDAO;
import lk.ijse.d24.dao.custom.StudentDAO;
import lk.ijse.d24.dto.ReservationDTO;
import lk.ijse.d24.dto.RoomDTO;
import lk.ijse.d24.dto.StudentDTO;
import lk.ijse.d24.entity.Reservation;
import lk.ijse.d24.entity.Room;
import lk.ijse.d24.entity.Student;

import java.util.ArrayList;
import java.util.List;

public class ReservationBOImpl implements ReservationBO {
    private RoomDAO roomDAO = (RoomDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ROOM);
    private ReservationDAO reservationDAO = (ReservationDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.RESERVATION);
    private StudentDAO studentDAO = (StudentDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.STUDENT);

    @Override
    public String setReservationID() throws Exception {
        return reservationDAO.getNextID();
    }

    @Override
    public RoomDTO getRooms(String value) throws Exception {
        Room room = roomDAO.getRoom(value);

        return new RoomDTO(room.getRoom_type_id(),room.getType(),room.getKey_money(),room.getQty());
    }

    @Override
    public List<StudentDTO> getStudents() throws Exception {
        List<Student> all = studentDAO.getAll();
        List<StudentDTO> studentList = new ArrayList<>();
        for (Student student : all){
            studentList.add(new StudentDTO(student.getStudent_id(), student.getName(), student.getAddress(),student.getContact(),student.getDob(),student.getGender()));

        }
        return studentList;
    }

    @Override
    public boolean registerStudent(ReservationDTO reservation) throws Exception {
        StudentDTO studentDTO = reservation.getStudent();
        Student student = new Student(
                studentDTO.getStudent_id(),
                studentDTO.getName(),
                studentDTO.getAddress(),
                studentDTO.getContact(),
                studentDTO.getDob(),
                studentDTO.getGender(),
                new ArrayList<>()
        );

        RoomDTO roomDTO = reservation.getRoom();
        Room room = roomDAO.search(roomDTO.getRoom_type_id());

        if (room != null) { // Check if room is not null
            Reservation reservations = new Reservation(
                    reservation.getRes_id(),
                    reservation.getDate(),
                    reservation.getStatus(),
                    student,
                    room
            );

            student.getReservations().add(reservations);
            room.getReservations().add(reservations);
            room.setQty(room.getQty() - 1);

            boolean isRegistered = studentDAO.add(student);
            roomDAO.update(room);

            return isRegistered;
        } else {
            // Handle the case where the room is not found
            throw new Exception("Room with room_type_id " + roomDTO.getRoom_type_id() + " not found.");
        }
    }


    @Override
    public List<RoomDTO> getAllRooms() throws Exception {
        List<Room> all = roomDAO.getAll();
        List<RoomDTO> roomList = new ArrayList<>();
        for (Room room : all) {

            roomList.add(new RoomDTO(room.getRoom_type_id(), room.getType(), room.getKey_money(), room.getQty(), null));
        }
        return roomList;
    }

    @Override
    public List<ReservationDTO> getAllDetails() throws Exception {
        List<Reservation> all = reservationDAO.getAll();
        List<ReservationDTO> reservationDTOS = new ArrayList<>();
        for (Reservation reservation : all) {
            ReservationDTO reservationDTO = new ReservationDTO(
                    reservation.getRes_id(),
                    reservation.getDate(),
                    reservation.getStatus(),
                    null,
                    null
            );

            Student student = reservation.getStudent();
            reservationDTO.setStudent(new StudentDTO(
                    student.getStudent_id(),
                    student.getName(),
                    student.getAddress(),
                    student.getContact(),
                    student.getDob(),
                    student.getGender(),
                    null
            ));

            Room room = reservation.getRoom();
            reservationDTO.setRoom(new RoomDTO(
                    room.getRoom_type_id(),
                    room.getType(),
                    room.getKey_money(),
                    room.getQty(),
                    null
            ));

            reservationDTOS.add(reservationDTO);
        }

        return reservationDTOS;
    }

    @Override
    public ReservationDTO setFields(String text) throws Exception {
     //need to imlement
        return null;
    }

    @Override
    public boolean UpdateStudent(ReservationDTO reservation) throws Exception {
        StudentDTO studentDTO = reservation.getStudent();
        Student student = new Student(
                studentDTO.getStudent_id(),
                studentDTO.getName(),
                studentDTO.getAddress(),
                studentDTO.getContact(),
                studentDTO.getDob(),
                studentDTO.getGender(),
                new ArrayList<>()
        );

        RoomDTO roomDTO = reservation.getRoom();
        Room room = roomDAO.search(roomDTO.getRoom_type_id());

        if (room != null) {
            Reservation reservations = new Reservation(
                    reservation.getRes_id(),
                    reservation.getDate(),
                    reservation.getStatus(),
                    student,
                    room
            );

            student.getReservations().add(reservations);
            room.getReservations().add(reservations);
            room.setQty(room.getQty() - 1);

            boolean isRegistered = studentDAO.update(student);
            roomDAO.update(room);
            reservationDAO.update(reservations);

            return isRegistered;
        } else {

            throw new Exception("Room with room_type_id " + roomDTO.getRoom_type_id() + " not found.");
        }
    }


}
