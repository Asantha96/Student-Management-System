package lk.developersstack.lms.util;

import lk.developersstack.lms.entity.Book;
import lk.developersstack.lms.entity.Laptop;
import lk.developersstack.lms.entity.Program;
import lk.developersstack.lms.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.model.naming.ImplicitNamingStrategyJpaCompliantImpl;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtil {
    private static HibernateUtil hibernateUtil;
    private static SessionFactory sessionFactory=buildSessionFactory();
    private HibernateUtil(){}
    public static HibernateUtil getInstance(){
        return (null==hibernateUtil) ? hibernateUtil=new HibernateUtil():hibernateUtil;
    }
    private static SessionFactory buildSessionFactory(){
        StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml")
                .build();

        Metadata metadata = new MetadataSources(standardRegistry)
                .addAnnotatedClass(Student.class).addAnnotatedClass(Book.class).addAnnotatedClass(Laptop.class).addAnnotatedClass(Program.class)

                .getMetadataBuilder()
                .applyImplicitNamingStrategy(ImplicitNamingStrategyJpaCompliantImpl.INSTANCE)
                .build();

        return metadata.getSessionFactoryBuilder().build();
        //all took from the hibernate website, hibernate site, Orm, documentation,user guide, boostrap, native boostrap,building the session factory

    }
    public Session getSession(){
        return sessionFactory.openSession();
    }
}
