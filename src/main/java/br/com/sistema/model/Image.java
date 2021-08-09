package br.com.sistema.model;

import javax.persistence.*;

@Entity
@Table(name = "image")
public class Image {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    public Long id;

    private String link;

    @ManyToOne(cascade=CascadeType.PERSIST)
    @JoinColumn(name="project_id")
    private Project project;

    public Image() {
    }

    public Image(String link) {
        this.link = link;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getLink() {
        return link;
    }
}
