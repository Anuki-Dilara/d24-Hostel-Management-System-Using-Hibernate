package lk.ijse.d24.bo.custom;

import lk.ijse.d24.bo.SuperBO;

import java.util.List;

public interface FindBO extends SuperBO {
    List<Object[]> getNonPayedStudents() throws Exception;
}
