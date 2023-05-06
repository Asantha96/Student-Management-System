package lk.developersstack.lms.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import lk.developersstack.lms.bo.BoFactory;
import lk.developersstack.lms.bo.custom.StudentBo;
import lk.developersstack.lms.dto.StudentDto;

import java.sql.SQLException;

public class MainFormController {
    public AnchorPane studentManagementAnchorPane;
    public JFXTextField txtStudentName;
    public JFXTextField txtContact;
    public JFXTextField txtSearch;
    public TableView tableview;

    private final StudentBo studentBo = BoFactory.getInstance().getBo(BoFactory.BoType.STUDENT);

    public void initialize() throws SQLException, ClassNotFoundException {
        loadAllStudents();
    }

    private void loadAllStudents() throws SQLException, ClassNotFoundException {
        System.out.println(studentBo.findAllStudents());
    }

    public void addNewStudentOnAction(ActionEvent actionEvent) {
    }

    public void saveStudentOnAction(ActionEvent actionEvent) {
        StudentDto studentDto = new StudentDto();
        studentDto.setName(txtStudentName.getText());
        studentDto.setContact(txtContact.getText());
        try{
            studentBo.saveStudent(studentDto);
            new Alert(Alert.AlertType.INFORMATION, "Student saved").show();
        }catch (Exception e){
            new Alert(Alert.AlertType.ERROR, "Try again").show();
        }
    }
}
