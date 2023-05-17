package lk.developersstack.lms.bo.custom.impl;

import lk.developersstack.lms.bo.custom.ProgramBo;
import lk.developersstack.lms.dao.DaoFactory;
import lk.developersstack.lms.dao.custom.ProgramDao;
import lk.developersstack.lms.dto.CreateProgramDto;
import lk.developersstack.lms.dto.CustomRegistrationData;
import lk.developersstack.lms.dto.ProgramDto;
import lk.developersstack.lms.entity.Program;
import lk.developersstack.lms.entity.Registration;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProgramBoImpl implements ProgramBo {
    private  final ProgramDao programDao = DaoFactory.getInstance().getDao(DaoFactory.DaoType.PROGRAM);
    @Override
    public void saveProgram(CreateProgramDto dto) throws SQLException, ClassNotFoundException {
        Program program = new Program();
        program.setTitle(dto.getTitle());
        program.setCredit(dto.getCredit());

        programDao.save(program);
    }

    @Override
    public List<ProgramDto> findAllPrograms() throws SQLException, ClassNotFoundException {
        ArrayList<ProgramDto> dtoList = new ArrayList<>();
        for (Program p :
                programDao.findAll()) {
            ProgramDto programDto = new ProgramDto(p.getId(),p.getTitle(),p.getCredit());
            dtoList.add(programDto);
        }
        return dtoList;
    }

    @Override
    public void register(long studentId, long programId) throws SQLException, ClassNotFoundException {
        programDao.register(studentId,programId);
    }

    @Override
    public List<CustomRegistrationData> findAllRegistrations() throws SQLException, ClassNotFoundException {
        List<CustomRegistrationData> dataList = new ArrayList<>();
        for (Registration reg :
                programDao.findAllRegistrations()) {
            dataList.add(new CustomRegistrationData(reg.getRegDate(),reg.getStudent().getId(),reg.getStudent().getName(),reg.getProgram().getId(),reg.getProgram().getTitle()));
        }
        return dataList;
    }
}
