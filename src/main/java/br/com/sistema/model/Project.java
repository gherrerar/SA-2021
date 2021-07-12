package br.com.sistema.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="project")
public class Project {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    public long id;

    @NotBlank
    private String title;

    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private User user;

    public LocalDate date;

    public String formattedDate;

    private String mainFileName;

    @NotBlank
    @Lob
    private String text;

    @OneToMany(mappedBy="project", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Image> images = new HashSet<>();

    public Project() {
    }

    public Project(String title, User user, LocalDate date, String text, String formattedDate, String mainFileName) {
        this.title = title;
        this.user = user;
        this.date = date;
        this.text = text;
        this.formattedDate = formattedDate;
        this.mainFileName = mainFileName;
    }

    public Set<Image> getImages() {
        return images;
    }

    public void setImages(Set<Image> images) {
        this.images = images;
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

    public String getUserName() {return user.getEmail();}

    public String getFormattedDate() {
        return formattedDate;
    }

    public void setFormattedDate(String formattedDate) {
        this.formattedDate = formattedDate;
    }

    public String getMainFileName() {
        return mainFileName;
    }

    public void setMainFileName(String mainFileName) {
        this.mainFileName = mainFileName;
    }
}
