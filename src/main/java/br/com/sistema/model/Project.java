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
    private long project_id;

    @NotBlank
    private String title;

    @NotBlank
    private String author;

    @JsonFormat (shape = JsonFormat.Shape.STRING,pattern = "dd-MM-yyyy")
    private LocalDate date;

    @NotBlank
    @Lob
    private String text;

    @OneToMany(mappedBy="project", cascade=CascadeType.ALL, fetch = FetchType.LAZY)
    private Collection<File> files;

    public Project() {
    }

    public Project(String title, String author, LocalDate date, String text, Collection<File> files) {
        this.title = title;
        this.author = author;
        this.date = date;
        this.text = text;
        this.files = files;
    }

    public long getId() {
        return project_id;
    }

    public void setId(long id) {
        this.project_id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
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
