package lk.ijse.d24.dao.custom;


import lk.ijse.d24.dao.CrudDAO;
import lk.ijse.d24.entity.Student;

public interface StudentDAO extends CrudDAO<Student> {

    Student setFields(String text)throws Exception;
}
