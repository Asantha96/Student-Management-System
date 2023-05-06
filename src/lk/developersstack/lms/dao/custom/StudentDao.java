package lk.developersstack.lms.dao.custom;

import lk.developersstack.lms.dao.CrudDao;
import lk.developersstack.lms.entity.Student;


//why long can't apply here====> In Java, generic type parameters cannot be primitive types like long. Instead, you should use the corresponding wrapper class, which is Long.
public interface StudentDao extends CrudDao<Student, Long> {

}
