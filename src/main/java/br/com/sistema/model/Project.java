package br.com.sistema.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.*;

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

    @OneToMany(cascade = CascadeType.ALL)
    public List<Image> images = new ArrayList<>();

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

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
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

    public void clearImages() {
        images.removeAll(images);
    }
}
