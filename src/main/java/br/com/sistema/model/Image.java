package br.com.sistema.model;

import javax.persistence.*;

@Entity
@Table(name = "image")
public class Image {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    public Long id;

    private String name;

    private String path;

    @ManyToOne(cascade=CascadeType.PERSIST)
    @JoinColumn(name="project_id", nullable = true)
    private Project project;

    public Image() {
    }

    public Image(String name, String path) {
        this.name = name;
        this.path = path;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
