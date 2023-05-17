package lk.developersstack.lms.view.tm;

import javafx.scene.control.Button;

public class ProgramTM {
    private long programId;
    private String title;
    private int credit;
    private Button deleteBtn;

    public ProgramTM() {
    }

    public ProgramTM(long programId, String title, int credit, Button deleteBtn) {
        this.programId = programId;
        this.title = title;
        this.credit = credit;
        this.deleteBtn = deleteBtn;
    }

    public long getProgramId() {
        return programId;
    }

    public void setProgramId(long programId) {
        this.programId = programId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public Button getDeleteBtn() {
        return deleteBtn;
    }

    public void setDeleteBtn(Button deleteBtn) {
        this.deleteBtn = deleteBtn;
    }
}
