package lk.developersstack.lms.bo.custom;

import lk.developersstack.lms.dto.CreateProgramDto;
import lk.developersstack.lms.dto.CustomRegistrationData;
import lk.developersstack.lms.dto.ProgramDto;

import java.sql.SQLException;
import java.util.List;

public interface ProgramBo {
    void saveProgram(CreateProgramDto dto) throws SQLException, ClassNotFoundException;
    List<ProgramDto> findAllPrograms() throws SQLException, ClassNotFoundException;
    void register(long studentId, long programId) throws SQLException, ClassNotFoundException;
    List<CustomRegistrationData> findAllRegistrations() throws SQLException, ClassNotFoundException;
}
