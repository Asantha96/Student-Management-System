package lk.developersstack.lms.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.developersstack.lms.bo.BoFactory;
import lk.developersstack.lms.bo.custom.LaptopBo;
import lk.developersstack.lms.bo.custom.ProgramBo;
import lk.developersstack.lms.bo.custom.StudentBo;
import lk.developersstack.lms.dto.*;
import lk.developersstack.lms.view.tm.LaptopTM;
import lk.developersstack.lms.view.tm.ProgramTM;
import lk.developersstack.lms.view.tm.RegistrationTM;
import lk.developersstack.lms.view.tm.StudentTM;

import java.sql.SQLException;
import java.util.Optional;

public class MainFormController {
    public AnchorPane studentManagementAnchorPane;
    public JFXTextField txtStudentName;
    public JFXTextField txtContact;
    public JFXTextField txtSearch;
    public TableView<StudentTM> tblStdView;

    public TableColumn colStdId;
    public TableColumn colStdName;
    public TableColumn colStdContact;
    public TableColumn colSeeMore;
    public TableColumn colDelete;
    public JFXButton btnSave;
    public JFXButton addNewStudentOnAction;
    public AnchorPane laptopManagementAnchorPane;
    public JFXButton btnAddNewLaptop;
    public JFXTextField txtLaptopBrand;
    public JFXTextField txtLapSearch;
    public TableView tblStdView1;
    public TableColumn colLaptopId;
    public TableColumn colLaptopBrand;
    public TableColumn colLaptopDelete;
    public JFXComboBox<Long> cmbStudent;
    public JFXButton btnSaveLaptop;

    public TableView tblLaptopView;
    public AnchorPane programManagementAnchorPane;
    public JFXButton btnAddNewProgram;
    public JFXTextField txtProgramTitle;
    public JFXTextField txtCredit;
    public JFXButton btnSaveProgram;
    public JFXTextField txtSearchProgram;
    public TableColumn colProgramId;
    public TableColumn colProgramTitle;
    public TableColumn colProgramCredit;
    public TableColumn colProgramDelete;
    public TableView tblProgramView;

    private final StudentBo studentBo = BoFactory.getInstance().getBo(BoFactory.BoType.STUDENT);
    private final LaptopBo laptopBo = BoFactory.getInstance().getBo(BoFactory.BoType.LAPTOP);
    private final ProgramBo programBo = BoFactory.getInstance().getBo(BoFactory.BoType.PROGRAM);
    public JFXTextField txtStudentProgramSearch;
    public TableView tblStudentProgramView;
    public JFXComboBox<Long> cmbStudentForProgram;
    public JFXButton btnRegister;
    public JFXComboBox<Long> cmbProgramForStudent;
    public AnchorPane registerForProgramAnchorPane;
    public TableColumn colRegisteredDate;
    public TableColumn colRegisteredStudentName;
    public TableColumn colRegisteredProgramTitle;
    public TableColumn colRegisteredStudentId;
    public TableColumn colRegisteredProgramId;
    private StudentTM selectedStudentTm = null;

    public void initialize() throws SQLException, ClassNotFoundException {
        //////////For Student initialization///////////////////////////////////////
        colStdId.setCellValueFactory(new PropertyValueFactory<>("id")); //from the StudentTm field names
        colStdName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colStdContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        colSeeMore.setCellValueFactory(new PropertyValueFactory<>("seeMoreBtn"));
        colDelete.setCellValueFactory(new PropertyValueFactory<>("deleteBtn"));

        loadAllStudentsForTableView();


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

        ///////////////////////////////////////////////////////////////////////////////


        /////////////////////For Laptop initialization //////////////////////////////////////

        //set cellValue factory
        colLaptopId.setCellValueFactory(new PropertyValueFactory<>("lapId"));
        colLaptopBrand.setCellValueFactory(new PropertyValueFactory<>("brand"));
        colLaptopDelete.setCellValueFactory(new PropertyValueFactory<>("deleteButton"));
        loadAllStudentsForLaptopSection();//loading for registration pane(student loading for combo box)
        loadAllLaptopsForTableView();


        ///////////////////////////////////////////////////////////////////////////////////////

        //////////for Program initialization////////
        colProgramId.setCellValueFactory(new PropertyValueFactory<>("programId"));
        colProgramTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        colProgramCredit.setCellValueFactory(new PropertyValueFactory<>("credit"));
        colProgramDelete.setCellValueFactory(new PropertyValueFactory<>("deleteBtn"));

        ///////////////////////////////////////////////////////////////////////////////////////

        ///////////for student register for program initialization////////////////////////////
        loadProgramsForStudentRegisterForPrograms();
        loadAllProgramsForTableView();

        //////////////////////////////////////////////////////////////////////////////////////

        /////////////////for Program registrations///////////////////////////////////////////
        colRegisteredDate.setCellValueFactory(new PropertyValueFactory<>("registeredDate"));
        colRegisteredProgramId.setCellValueFactory(new PropertyValueFactory<>("programId"));
        colRegisteredStudentId.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        colRegisteredStudentName.setCellValueFactory(new PropertyValueFactory<>("studentName"));
        colRegisteredProgramTitle.setCellValueFactory(new PropertyValueFactory<>("programTitle"));
        loadAllProgramRegistrationsForTableView();

        //////////////////////////////////////////////////////////////////////////////////////


    }

    private void loadProgramsForStudentRegisterForPrograms() throws SQLException, ClassNotFoundException{
        ObservableList<Long> obList = FXCollections.observableArrayList();
        for (ProgramDto dto :
                programBo.findAllPrograms()) {
            obList.add(dto.getProgramId());
        }
        cmbProgramForStudent.setItems(obList);
    }

    private void loadAllStudentsForLaptopSection() throws SQLException, ClassNotFoundException {
        ObservableList<Long> obList = FXCollections.observableArrayList();
        for (StudentDto dto:
             studentBo.findAllStudents()) {
            obList.add(dto.getId());
        }
        cmbStudent.setItems(obList);
        cmbStudentForProgram.setItems(obList);
    }

    private void loadAllStudentsForTableView() throws SQLException, ClassNotFoundException {
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
                        loadAllStudentsForTableView();
                        loadAllStudentsForLaptopSection();
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
                loadAllStudentsForTableView();
                loadAllStudentsForLaptopSection();
                clearStudentData();
            }catch (Exception e){
                new Alert(Alert.AlertType.ERROR, "Try again").show();
            }
        }else{
            try{
                studentBo.saveStudent(studentDto);
                new Alert(Alert.AlertType.INFORMATION, "Student saved").show();
                loadAllStudentsForTableView();
                loadAllStudentsForLaptopSection();
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

    public void addNewLaptopOnAction(ActionEvent actionEvent) {

    }

    public void saveLaptopOnAction(ActionEvent actionEvent) {
        try {
            laptopBo.saveLaptop(new CreateLaptopDto(cmbStudent.getValue(),txtLaptopBrand.getText()));
            new Alert(Alert.AlertType.INFORMATION,"Laptop saved!").show();
            loadAllLaptopsForTableView();
            clearLaptopData();
        }catch (Exception ex){
            new Alert(Alert.AlertType.ERROR,"Try again!").show();
        }

    }

    private void clearLaptopData() {
        txtLaptopBrand.clear();
        cmbStudent.getSelectionModel().clearSelection();
    }

    private void loadAllLaptopsForTableView() throws SQLException, ClassNotFoundException {
        ObservableList<LaptopTM> tmList = FXCollections.observableArrayList();
        for (LaptopDto dto: laptopBo.findAllLaptops()
             ) {
            Button deleteButton = new Button("Delete");
            deleteButton.setStyle("-fx-background-color: #EA2027");
            LaptopTM laptopTM = new LaptopTM(dto.getLapId(),dto.getBrand(), deleteButton);
            tmList.add(laptopTM);

            deleteButton.setOnAction(event -> {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure?",ButtonType.YES,ButtonType.NO);
                Optional<ButtonType> buttonType = alert.showAndWait();
                if (buttonType.get() == ButtonType.YES){
                    try{
                        laptopBo.deleteLaptopById(laptopTM.getLapId());
                        new Alert(Alert.AlertType.INFORMATION,"Laptop deleted!");
                        loadAllLaptopsForTableView();
                    }catch (Exception ex){
                        new Alert(Alert.AlertType.ERROR,"Try Again!").show();
                    }
                }
            });
        }
        tblLaptopView.setItems(tmList);
        tblLaptopView.refresh();
    }

    public void addNewProgramOnAction(ActionEvent actionEvent) {
    }

    public void saveProgramOnAction(ActionEvent actionEvent) {
        try{
            programBo.saveProgram(new CreateProgramDto(txtProgramTitle.getText(),Integer.parseInt(txtCredit.getText())));
            new Alert(Alert.AlertType.INFORMATION,"Program Saved!").show();
            loadAllProgramsForTableView();
            loadProgramsForStudentRegisterForPrograms();
        }catch (Exception ex){
            new Alert(Alert.AlertType.ERROR, "Try again!").show();
        }
    }

    private void loadAllProgramsForTableView() throws SQLException, ClassNotFoundException{
        ObservableList<ProgramTM> tmList = FXCollections.observableArrayList();
        for (ProgramDto dto :
                programBo.findAllPrograms()) {
            Button deleteButton = new Button("Delete");
            deleteButton.setStyle("-fx-background-color: #EA2027");
            ProgramTM programTM = new ProgramTM(dto.getProgramId(),dto.getTitle(),dto.getCredit(),deleteButton);
            tmList.add(programTM);
        }
        tblProgramView.setItems(tmList);
        tblProgramView.refresh();
    }

    public void btnRegisterStudentWithProgramOnAction(ActionEvent actionEvent) {
        try{
            programBo.register(cmbStudentForProgram.getValue(),cmbProgramForStudent.getValue());
            new Alert(Alert.AlertType.INFORMATION, "Registered!").show();
            loadAllProgramRegistrationsForTableView();
        }catch (Exception ex){
            System.out.println(ex);
            new Alert(Alert.AlertType.ERROR, "Try again").show();
        }
    }

    private void loadAllProgramRegistrationsForTableView() throws SQLException, ClassNotFoundException {
        ObservableList<RegistrationTM> obList = FXCollections.observableArrayList();
        for (CustomRegistrationData customData:programBo.findAllRegistrations()
             ) {
            RegistrationTM registrationTM = new RegistrationTM(customData.getDate(),customData.getStudentId(),customData.getStudentName(),customData.getProgramId(),customData.getProgramTitle());
            obList.add(registrationTM);
        }
        tblStudentProgramView.setItems(obList);
    }
}
