package lk.developersstack.lms.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Registration {
    @EmbeddedId
    private RegistrationIds registrationIds = new RegistrationIds();// this should be initialized

    @ManyToOne
    @MapsId("studentId")
    private Student student;


    @ManyToOne
    @MapsId("programId")
    private Program program;

    private Date regData;

    public Registration(RegistrationIds registrationIds, Student student, Program program, Date regData) {
        this.registrationIds = registrationIds;
        this.student = student;
        this.program = program;
        this.regData = regData;
    }

    public Registration() {
    }

    public RegistrationIds getRegistrationIds() {
        return registrationIds;
    }

    public void setRegistrationIds(RegistrationIds registrationIds) {
        this.registrationIds = registrationIds;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Program getProgram() {
        return program;
    }

    public void setProgram(Program program) {
        this.program = program;
    }

    public Date getRegData() {
        return regData;
    }

    public void setRegData(Date regData) {
        this.regData = regData;
    }
}
