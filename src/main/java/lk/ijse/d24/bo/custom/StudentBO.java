package lk.ijse.d24.bo.custom;


import lk.ijse.d24.bo.SuperBO;
import lk.ijse.d24.dto.StudentDTO;

import java.util.List;

public interface StudentBO extends SuperBO {
    public boolean add(StudentDTO entity) throws Exception;

    boolean update(StudentDTO entity)throws Exception;

    boolean delete(String sId)throws Exception;

    StudentDTO setFields(String text)throws Exception;

    List<StudentDTO> getAll() throws Exception;

}
