package lk.ijse.d24.bo.custom.impl;

import lk.ijse.d24.bo.custom.FindBO;
import lk.ijse.d24.dao.DAOFactory;
import lk.ijse.d24.dao.custom.QueryDAO;

import java.util.List;

public class FindBOImpl implements FindBO {
    private QueryDAO queryDAO = (QueryDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.QUERY);
    @Override
    public List<Object[]> getNonPayedStudents() throws Exception {
        return queryDAO.nonPayedKeyMoneyStd();
    }
}
