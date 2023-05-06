package lk.developersstack.lms.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id")
    private long id;
    private String name;
    private String contact;

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public List<Program> getPrograms() {
        return programs;
    }

    public void setPrograms(List<Program> programs) {
        this.programs = programs;
    }



    /////////mapping laptop////////////////////
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "student", fetch = FetchType.EAGER)
    private Laptop laptop;
    /////////mapping laptop////////////////////



    ////////mapping book//////////////////////
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "student",fetch = FetchType.EAGER) //what is mean by "mapped by student" here? carry the student primary key to the book table as a foreign key to that table
    private List<Book> books;
    ////////mapping book//////////////////////




    /////////mapping program//////////////////
    @ManyToMany(mappedBy = "students")

    private List<Program> programs;

    /////////mapping program//////////////////




    public Student() {
    }

    public Student(long id, String name, String contact) {
        this.setId(id);
        this.setName(name);
        this.setContact(contact);
    }

    public Laptop getLaptop() {
        return laptop;
    }

    public void setLaptop(Laptop laptop) {
        this.laptop = laptop;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + getId() +
                ", name='" + getName() + '\'' +
                ", contact='" + getContact() + '\'' +
                '}';
    }
}
