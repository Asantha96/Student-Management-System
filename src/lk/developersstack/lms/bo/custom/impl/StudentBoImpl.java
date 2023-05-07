package lk.developersstack.lms.bo.custom.impl;

import lk.developersstack.lms.bo.custom.StudentBo;
import lk.developersstack.lms.dao.DaoFactory;
import lk.developersstack.lms.dao.custom.StudentDao;
import lk.developersstack.lms.dto.StudentDto;
import lk.developersstack.lms.entity.Student;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentBoImpl implements StudentBo {
    private final StudentDao studentDao = DaoFactory.getInstance().getDao(DaoFactory.DaoType.STUDENT);
    @Override
    public void saveStudent(StudentDto dto) throws SQLException, ClassNotFoundException {
        Student student = new Student();
        student.setName(dto.getName());
        student.setContact(dto.getContact());
        studentDao.save(student);

    }

    @Override
    public List<StudentDto> findAllStudents() throws SQLException, ClassNotFoundException {
        ArrayList<StudentDto> dtoList = new ArrayList<>();
        for (Student s :
                studentDao.findAll()) {
            StudentDto studentDto = new StudentDto(s.getId(),s.getName(),s.getContact());
            studentDto.setLaptop(s.getLaptop());
            studentDto.setBooks(s.getBooks()); //can get sub table data because of fetch type is eager loading
            dtoList.add(studentDto);

        }
        return dtoList;
    }

    @Override
    public void deleteStudentById(long id) throws SQLException, ClassNotFoundException {
        studentDao.delete(id);
    }

    @Override
    public void updateStudent(StudentDto dto) throws SQLException, ClassNotFoundException {

        Student student = new Student();
        student.setId(dto.getId());
        student.setName(dto.getName());
        student.setContact(dto.getContact());
        studentDao.update(student);
    }
}
