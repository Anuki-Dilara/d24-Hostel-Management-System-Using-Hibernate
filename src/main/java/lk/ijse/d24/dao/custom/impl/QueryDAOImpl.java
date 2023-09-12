package lk.ijse.d24.dao.custom.impl;

import lk.ijse.d24.dao.custom.QueryDAO;
import lk.ijse.d24.utill.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class QueryDAOImpl implements QueryDAO {
    @Override
    public List<Object[]> nonPayedKeyMoneyStd() throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery(
                "select s.student_id, s.name, s.address, s.contact, rm.key_money, rs.res_id, rm.room_type_id from Student s " +
                        "inner join s.reservations rs " +
                        "inner join rs.room rm where rs.status = 'Pending'");
        List<Object[]> list = query.list();

        transaction.commit();
        session.close();

        return list;
    }
}
