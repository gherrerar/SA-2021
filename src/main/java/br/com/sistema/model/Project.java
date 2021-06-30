package br.com.sistema.model;


import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.Collection;

@Entity
@Table(name="project")
public class Project {

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private long id;

    @NotBlank
    private String title;

    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private User user;

    @JsonFormat (shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate date;

    @NotBlank
    @Lob
    private String text;

    @OneToMany(mappedBy="project", cascade=CascadeType.ALL, fetch = FetchType.LAZY)
    private Collection<File> files;

    public Project(String title, User user, LocalDate date, String text, Collection<File> files) {
        this.title = title;
        this.user = user;
        this.date = date;
        this.text = text;
    }

    public Project(String title, User user, LocalDate date, String text) {
        this.title = title;
        this.user = user;
        this.date = date;
        this.text = text;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getText() {
        return text;
    }

    public void setText (String text) {
        this.text = text;
    }

    public Collection<File> getFiles() {
        return files;
    }

    public void setFiles(Collection<File> files) {
        this.files = files;
    }
}
