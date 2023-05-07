package lk.developersstack.lms.bo.custom;

import lk.developersstack.lms.dto.CreateLaptopDto;
import lk.developersstack.lms.dto.LaptopDto;
import lk.developersstack.lms.dto.StudentDto;

import java.sql.SQLException;
import java.util.List;

public interface LaptopBo {
    public void saveLaptop(CreateLaptopDto dto) throws SQLException, ClassNotFoundException;
    public List<LaptopDto> findAllLaptops() throws SQLException, ClassNotFoundException;

    public void deleteLaptopById(long lapId) throws SQLException, ClassNotFoundException;
}
