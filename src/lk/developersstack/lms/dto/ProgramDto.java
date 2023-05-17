package lk.developersstack.lms.dto;

public class ProgramDto {
    private long programId;
    private String title;
    private int credit;

    public ProgramDto() {
    }

    public ProgramDto(long programId, String title, int credit) {
        this.programId = programId;
        this.title = title;
        this.credit = credit;
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
}
