package lk.developersstack.lms.dao.custom;

import lk.developersstack.lms.dao.CrudDao;
import lk.developersstack.lms.entity.Program;
import lk.developersstack.lms.entity.Registration;

import java.util.Date;
import java.util.List;

public interface ProgramDao extends CrudDao<Program, Long> {

    void register(long studentId, long programId);
    List<Registration> findAllRegistrations();
}
