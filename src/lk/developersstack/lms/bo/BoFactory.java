package lk.developersstack.lms.bo;

import lk.developersstack.lms.bo.custom.impl.StudentBoImpl;

public class BoFactory {
    private static BoFactory boFactory;
    private BoFactory(){}
    public enum BoType{
        BOOK, STUDENT, LAPTOP, PROGRAM
    }
    public static BoFactory getInstance(){
        return (null==boFactory)?boFactory= new BoFactory():boFactory;
    }
    public <T> T getBo(BoFactory.BoType boType){
        switch (boType){
            case STUDENT:
                return (T) new StudentBoImpl();
            case BOOK:
                return null;
            case LAPTOP:
                return null;
            case PROGRAM:
                return null;
            default:
                return null;
        }
    }
}
