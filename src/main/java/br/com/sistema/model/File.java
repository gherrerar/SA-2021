package br.com.sistema.model;

import javax.persistence.*;

@Entity
@Table(name = "file")
public class File {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String path;

    @ManyToOne(fetch = FetchType.EAGER,cascade=CascadeType.ALL)
    @JoinColumn(name="project_id", nullable=false)
    private Project project;

    public File() {
    }

    public File(String name, String path) {
        this.name = name;
        this.path = path;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
