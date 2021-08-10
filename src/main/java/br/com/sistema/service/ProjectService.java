
package br.com.sistema.service;

import br.com.sistema.dto.ProjectDto;
import br.com.sistema.model.Project;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


public interface ProjectService {
    List<Project> findAll();
    Project findById(long id);
    Boolean save(ProjectDto project, List<String> linkList);
    Boolean saveEdit(ProjectDto project, long id, List<String> linkList);
    void deleteById (long id);
}