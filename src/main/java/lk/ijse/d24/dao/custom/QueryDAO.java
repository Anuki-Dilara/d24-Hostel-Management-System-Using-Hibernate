package lk.ijse.d24.dao.custom;



import lk.ijse.d24.dao.SuperDAO;

import java.util.List;

public interface QueryDAO extends SuperDAO {
    List<Object[]> nonPayedKeyMoneyStd() throws Exception;
}
