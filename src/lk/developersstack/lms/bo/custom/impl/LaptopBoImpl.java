package lk.developersstack.lms.bo.custom.impl;

import lk.developersstack.lms.bo.custom.LaptopBo;
import lk.developersstack.lms.dao.DaoFactory;
import lk.developersstack.lms.dao.custom.LaptopDao;
import lk.developersstack.lms.dto.CreateLaptopDto;
import lk.developersstack.lms.dto.LaptopDto;
import lk.developersstack.lms.entity.Laptop;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LaptopBoImpl implements LaptopBo {
    LaptopDao laptopDao = DaoFactory.getInstance().getDao(DaoFactory.DaoType.LAPTOP);
    @Override
    public void saveLaptop(CreateLaptopDto dto) throws SQLException, ClassNotFoundException {
        Laptop laptop = new Laptop();
        laptop.setBrand(dto.getBrand());
        laptopDao.saveLaptopWithStudentId(dto.getStudentId(),laptop);
    }

    @Override
    public List<LaptopDto> findAllLaptops() throws SQLException, ClassNotFoundException {
        ArrayList<LaptopDto> dtoList = new ArrayList<>();
        for (Laptop l :
                laptopDao.findAll()) {
            LaptopDto dto = new LaptopDto(l.getId(),l.getBrand());
            dtoList.add(dto);
        }
        return dtoList;
    }

    @Override
    public void deleteLaptopById(long lapId) throws SQLException, ClassNotFoundException {
        laptopDao.delete(lapId);
    }
}
