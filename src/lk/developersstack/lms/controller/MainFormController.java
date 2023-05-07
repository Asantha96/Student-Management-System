package lk.developersstack.lms.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.developersstack.lms.bo.BoFactory;
import lk.developersstack.lms.bo.custom.StudentBo;
import lk.developersstack.lms.dto.StudentDto;
import lk.developersstack.lms.view.tm.StudentTM;

import java.sql.SQLException;
import java.util.Optional;

public class MainFormController {
    public AnchorPane studentManagementAnchorPane;
    public JFXTextField txtStudentName;
    public JFXTextField txtContact;
    public JFXTextField txtSearch;
    public TableView<StudentTM> tblStdView;

    private final StudentBo studentBo = BoFactory.getInstance().getBo(BoFactory.BoType.STUDENT);
    public TableColumn colStdId;
    public TableColumn colStdName;
    public TableColumn colStdContact;
    public TableColumn colSeeMore;
    public TableColumn colDelete;
    public JFXButton btnSave;
    public JFXButton addNewStudentOnAction;
    private StudentTM selectedStudentTm = null;

    public void initialize() throws SQLException, ClassNotFoundException {
        colStdId.setCellValueFactory(new PropertyValueFactory<>("id")); //from the StudentTm field names
        colStdName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colStdContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        colSeeMore.setCellValueFactory(new PropertyValueFactory<>("seeMoreBtn"));
        colDelete.setCellValueFactory(new PropertyValueFactory<>("deleteBtn"));

        loadAllStudents();

        ////////Listener to get data in row of the student table////////////////

        tblStdView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (null!=newValue){
                selectedStudentTm = newValue;
                txtStudentName.setText(newValue.getName());
                txtContact.setText(newValue.getContact());
                btnSave.setText("Update Student");
            }


        });

        ////////Listener////////////////
    }

    private void loadAllStudents() throws SQLException, ClassNotFoundException {
        ObservableList<StudentTM> tmList = FXCollections.observableArrayList();

        for (StudentDto dto :
                studentBo.findAllStudents()) {
            Button deleteButton = new Button("Delete");
            deleteButton.setStyle("-fx-background-color: #EA2027");
            Button seeMoreButton = new Button("See More");
            seeMoreButton.setStyle("-fx-background-color: #0652DD");

            StudentTM studentTM = new StudentTM(dto.getId(), dto.getName(), dto.getContact(),deleteButton,seeMoreButton);
            tmList.add(studentTM);

            deleteButton.setOnAction(e->{

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Are you sure", ButtonType.YES, ButtonType.NO);
                Optional<ButtonType> buttonType = alert.showAndWait();
                if (buttonType.get()==ButtonType.YES){
                    try {
                        studentBo.deleteStudentById(studentTM.getId());
                        new Alert(Alert.AlertType.INFORMATION,"Student deleted").show();
                        loadAllStudents();
                    }catch (Exception ex){
                        new Alert(Alert.AlertType.ERROR,"Try again").show();
                    }
                }
            });



        }
        tblStdView.setItems(tmList);
        tblStdView.refresh();

    }

    public void addNewStudentOnAction(ActionEvent actionEvent) {
        btnSave.setText("Save Student");
        selectedStudentTm=null;
    }

    public void saveStudentOnAction(ActionEvent actionEvent) {
        StudentDto studentDto = new StudentDto();
        studentDto.setName(txtStudentName.getText());
        studentDto.setContact(txtContact.getText());

        if (btnSave.getText().equals("Update Student")){///update student
            if (null==selectedStudentTm){
                new Alert(Alert.AlertType.ERROR, "Try again").show();
                return;
            }
            try{
                studentDto.setId(selectedStudentTm.getId());
                studentBo.updateStudent(studentDto);
                new Alert(Alert.AlertType.INFORMATION, "Student updated").show();
                btnSave.setText("Save Student");
                loadAllStudents();
                clearStudentData();
            }catch (Exception e){
                new Alert(Alert.AlertType.ERROR, "Try again").show();
            }
        }else{
            try{
                studentBo.saveStudent(studentDto);
                new Alert(Alert.AlertType.INFORMATION, "Student saved").show();
                loadAllStudents();
                clearStudentData();
            }catch (Exception e){
                new Alert(Alert.AlertType.ERROR, "Try again").show();
            }
        }


    }

    private void clearStudentData() {
        txtStudentName.clear();
        txtContact.clear();
    }
}
