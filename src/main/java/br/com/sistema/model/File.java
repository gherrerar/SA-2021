package br.com.sistema.model;

import javax.persistence.*;

@Entity
@Table(name = "file")
public class File {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Long id;

    @Lob
    private byte[] content;

    private String name;

    @ManyToOne
    @JoinColumn(name="project_id", nullable=false)
    private Project project;


    public File(byte[] content, String name) {
        this.content = content;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
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
}
