package lk.developersstack.lms.dao.custom.impl;

import lk.developersstack.lms.dao.custom.ProgramDao;
import lk.developersstack.lms.entity.Program;
import lk.developersstack.lms.entity.Registration;
import lk.developersstack.lms.entity.Student;
import lk.developersstack.lms.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class ProgramDaoImpl implements ProgramDao {//ENTITY THROUGH DATA TRANSFER TO BO LAYER
    @Override
    public void save(Program program) throws SQLException, ClassNotFoundException {
        try(Session session = HibernateUtil.getInstance().openSession()){
            Transaction transaction = session.beginTransaction();

            session.save(program);


            transaction.commit();
        }
    }

    @Override
    public void update(Program program) throws SQLException, ClassNotFoundException {

    }

    @Override
    public Program find(Long aLong) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public void delete(Long aLong) throws SQLException, ClassNotFoundException {

    }

    @Override
    public List<Program> findAll() throws SQLException, ClassNotFoundException {
        List<Program> programList;
        try (Session session = HibernateUtil.getInstance().openSession()) {
            Transaction transaction = session.beginTransaction();
            programList = session.createQuery("FROM Program", Program.class).list();

            transaction.commit();

        }
        return programList;
    }

    @Override
    public void register(long studentId, long programId) {
        //first find is there is a student
        try(Session session = HibernateUtil.getInstance().openSession()) {
            Query<Student> sQuery = session.createQuery("FROM Student WHERE student_id=:sId", Student.class);
            sQuery.setParameter("sId", studentId);
            Student selectedStudent = sQuery.uniqueResult();
            if (selectedStudent == null) throw new RuntimeException("Student Not Found!");



            //second find, is there is a program
            Query pQuery = session.createQuery("FROM Program WHERE program_id=:pId", Program.class);
            pQuery.setParameter("pId", programId);
            Program selectedProgram = (Program) pQuery.uniqueResult();
            if (selectedProgram == null) throw new RuntimeException("Program Not Found!");

            //
            session.beginTransaction();
            Registration registration = new Registration();
            registration.setProgram(selectedProgram);
            registration.setStudent(selectedStudent);
            registration.setRegDate(new Date());
            session.save(registration);
            session.getTransaction().commit();
        }


    }

    @Override
    public List<Registration> findAllRegistrations() {
        try (Session session = HibernateUtil.getInstance().openSession()){
            Query<Registration> query = session.createQuery("FROM Registration ", Registration.class);
            return query.list();
        }
    }
}
