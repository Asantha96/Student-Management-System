package lk.developersstack.lms.dto;

import java.util.Date;

public class CustomRegistrationData {
    private Date date;
    private long studentId;
    private String studentName;
    private long programId;
    private String programTitle;

    public CustomRegistrationData() {
    }

    public CustomRegistrationData(Date date, long studentId, String studentName, long programId, String programTitle) {
        this.date = date;
        this.studentId = studentId;
        this.studentName = studentName;
        this.programId = programId;
        this.programTitle = programTitle;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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
