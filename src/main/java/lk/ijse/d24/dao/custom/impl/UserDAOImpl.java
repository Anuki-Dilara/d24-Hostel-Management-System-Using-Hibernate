package lk.ijse.d24.dao.custom.impl;

import lk.ijse.d24.dao.custom.UserDAO;
import lk.ijse.d24.entity.User;
import lk.ijse.d24.utill.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO {
    @Override
    public ArrayList<User> getAll() throws Exception {
        return null;
    }

    @Override
    public boolean add(User entity) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.persist(entity);

        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(User entity) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.update(entity);

        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean exist(String id) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        boolean isExist = session.get(User.class, id) != null;

        transaction.commit();
        session.close();
        return isExist;
    }

    @Override
    public String generateNewID() throws Exception {
        String id = "U001";

        if (exist(id)) {
            Session session = FactoryConfiguration.getInstance().getSession();
            Transaction transaction = session.beginTransaction();

            Query query = session.createQuery("from User order by id desc limit 1");
            List<User> list = query.list();
            id = list.get(0).getId();

            transaction.commit();
            session.close();
            return getNewId(id);
        }

        return id;
    }

    private String getNewId(String id) {
        String prefix = id.replaceAll("[0-9]", "");
        int number = Integer.parseInt(id.replaceAll("\\D", ""));
        number++;
        String newNumber = String.format("%0" + (id.length() - prefix.length()) + "d", number);
        return prefix + newNumber;
    }

    @Override
    public boolean delete(String id) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.remove(session.get(User.class, id));

        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public User search(String id) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        User user = session.get(User.class, id);

        transaction.commit();
        session.close();

        return user;
    }

    @Override
    public boolean verifyUser(String username, String password) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("from User where username =?1 and password =?2");
        query.setParameter(1, username);
        query.setParameter(2, password);
        List list = query.list();

        transaction.commit();
        session.close();

        for (Object o : list) {return true;}
        return false;
    }

    @Override
    public User search(String username, String password) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("from User where username =?1 and password =?2");
        query.setParameter(1, username);
        query.setParameter(2, password);
        List<User> list = query.list();

        transaction.commit();
        session.close();

        return list.get(0);
    }
}
