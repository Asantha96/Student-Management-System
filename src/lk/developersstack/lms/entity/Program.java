package lk.developersstack.lms.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Program {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "program_id")
    private long id;
    private String title;
    private int credit;

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    ////mapping///////////////////////////////
    @ManyToMany
    @JoinTable(name = "registration", joinColumns = @JoinColumn(name = "program_id"), inverseJoinColumns = @JoinColumn(name = "student_id"))//first one is table name(registration table), second one is the primary key of the table, that is the program_id, third one is the column that is coming from the student table set here it as student_id
    private List<Student> students;

    ////mapping///////////////////////////////

    public Program() {
    }

    public Program(long id, String title, int credit) {
        this.id = id;
        this.title = title;
        this.credit = credit;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    @Override
    public String  toString() {
        return "Program{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", credit=" + credit +
                '}';
    }
}
