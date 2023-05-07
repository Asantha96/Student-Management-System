package lk.developersstack.lms.dao.custom.impl;

import lk.developersstack.lms.dao.custom.LaptopDao;
import lk.developersstack.lms.entity.Laptop;
import lk.developersstack.lms.entity.Student;
import lk.developersstack.lms.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.List;

public class LaptopDaoImpl implements LaptopDao {
    @Override
    public void save(Laptop laptop) throws SQLException, ClassNotFoundException {

    }

    @Override
    public void update(Laptop laptop) throws SQLException, ClassNotFoundException {

    }

    @Override
    public Laptop find(Long aLong) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public void delete(Long id) throws SQLException, ClassNotFoundException {
        try (Session session = HibernateUtil.getInstance().openSession()){
            Transaction transaction = session.beginTransaction();

            Query query = session.createQuery("DELETE FROM Laptop WHERE id=:selectedId");
            query.setParameter("selectedId", id);
            query.executeUpdate();


            transaction.commit();
        }
    }

    @Override
    public List<Laptop> findAll() throws SQLException, ClassNotFoundException {
        try (Session session = HibernateUtil.getInstance().openSession()){
            Transaction transaction = session.beginTransaction();

            List<Laptop> laptopList = session.createQuery("FROM Laptop ", Laptop.class).list();

            transaction.commit();
            return laptopList;

        }
    }

    @Override
    public void saveLaptopWithStudentId(long studentId, Laptop laptop) {
        try(Session session = HibernateUtil.getInstance().openSession()){
            Transaction transaction = session.beginTransaction();

            Query<Student> query = session.createQuery("FROM Student WHERE student_id=:sId", Student.class);
            query.setParameter("sId",studentId);
            Student selectedStudent = query.uniqueResult();

            if (null==selectedStudent) throw new RuntimeException("Student not found!");
            laptop.setStudent(selectedStudent);
            session.save(laptop);

            transaction.commit();
        }
    }
}
