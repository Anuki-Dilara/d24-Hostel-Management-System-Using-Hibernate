package lk.ijse.d24.dao.custom.impl;

import lk.ijse.d24.dao.custom.StudentDAO;
import lk.ijse.d24.entity.Student;
import lk.ijse.d24.utill.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class StudentDAOImpl implements StudentDAO {
    @Override
    public List<Student> getAll() throws Exception {
        Session session= FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Query query =session.createQuery("from Student");
        List<Student> list = query.list();

        transaction.commit();
        session.close();
        return list;
    }

    @Override
    public boolean add(Student entity) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.persist(entity);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(Student entity) throws Exception {
        Session session =FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.update(entity);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean exist(String id) throws Exception {
        return false;
    }

    @Override
    public boolean delete(String id) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.remove(session.get(Student.class, id));
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public Student search(String id) throws Exception {
        return null;
    }


    @Override
    public Student setFields(String text) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Student student = session.get(Student.class, text);

        transaction.commit();
        session.close();

        return student;
    }
}
