package lk.developersstack.lms.view.tm;

import java.util.Date;

public class RegistrationTM {
    private Date registeredDate;
    private long studentId;
    private String studentName;
    private long programId;
    private String programTitle;

    public RegistrationTM() {
    }

    public RegistrationTM(Date registeredDate, long studentId, String studentName, long programId, String programTitle) {
        this.registeredDate = registeredDate;
        this.studentId = studentId;
        this.studentName = studentName;
        this.programId = programId;
        this.programTitle = programTitle;
    }

    public Date getRegisteredDate() {
        return registeredDate;
    }

    public void setRegisteredDate(Date registeredDate) {
        this.registeredDate = registeredDate;
    }

    public long getStudentId() {
        return studentId;
    }

    public void setStudentId(long studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public long getProgramId() {
        return programId;
    }

    public void setProgramId(long programId) {
        this.programId = programId;
    }

    public String getProgramTitle() {
        return programTitle;
    }

    public void setProgramTitle(String programTitle) {
        this.programTitle = programTitle;
    }
}
