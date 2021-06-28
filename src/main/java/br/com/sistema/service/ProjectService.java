
package br.com.sistema.service;

import br.com.sistema.model.Project;

import java.util.List;

public interface ProjectService {
    List<Project> findAll();
    Project findById(long id);
    Project save(Project project);
}