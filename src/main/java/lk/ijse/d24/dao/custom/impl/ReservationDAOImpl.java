package lk.ijse.d24.dao.custom.impl;

import lk.ijse.d24.dao.custom.ReservationDAO;
import lk.ijse.d24.entity.Reservation;
import lk.ijse.d24.utill.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class ReservationDAOImpl implements ReservationDAO {
    @Override
    public List<Reservation> getAll() throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("from Reservation");
        List<Reservation> list = query.list();

        transaction.commit();
        session.close();
        return list;
    }

    @Override
    public boolean add(Reservation entity) throws Exception {
        return false;
    }

    @Override
    public boolean update(Reservation entity) throws Exception {
        Session session =FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.update(entity);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean exist(String id) throws Exception {
        Session session =FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        boolean isExist = session.get(Reservation.class, id) !=null;
        transaction.commit();
        session.close();

        return isExist;
    }

    @Override
    public boolean delete(String id) throws Exception {
        return false;
    }

    @Override
    public Reservation search(String id) throws Exception {
        return null;
    }

    @Override
    public String getNextID() throws Exception {
        String res_id = "R001";
        if (exist(res_id)){
            Session session = FactoryConfiguration.getInstance().getSession();
            Transaction transaction= session.beginTransaction();
            Query query = session.createQuery("from Reservation order by res_id desc limit 1");
            List<Reservation> list = query.list();
            res_id=list.get(0).getRes_id();

            transaction.commit();
            session.close();
            return getNewID(res_id);
        }
        return res_id;
    }

    @Override
    public String getNewID(String lastId) throws Exception {
        String prefix = lastId.replaceAll("[0-9]", "");
        int number = Integer.parseInt(lastId.replaceAll("\\D", ""));
        number++;
        String newNumber = String.format("%0" + (lastId.length() - prefix.length()) + "d", number);
        return prefix + newNumber;
    }
}
