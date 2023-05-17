package lk.developersstack.lms.dao;

import lk.developersstack.lms.dao.custom.ProgramDao;
import lk.developersstack.lms.dao.custom.impl.LaptopDaoImpl;
import lk.developersstack.lms.dao.custom.impl.ProgramDaoImpl;
import lk.developersstack.lms.dao.custom.impl.StudentDaoImpl;

public class DaoFactory {
    private static DaoFactory daoFactory;
    private DaoFactory(){}
    public enum DaoType{
        BOOK, STUDENT, LAPTOP, PROGRAM
    }
    public static DaoFactory getInstance(){
        return (null==daoFactory)?daoFactory= new DaoFactory():daoFactory;
    }
    public <T> T getDao(DaoType daoType){
        switch (daoType){
            case STUDENT:
                return (T) new StudentDaoImpl();
            case BOOK:
                return null;
            case LAPTOP:
                return (T) new LaptopDaoImpl();
            case PROGRAM:
                return (T)new ProgramDaoImpl();
            default:
                return null;
        }
    }
}
