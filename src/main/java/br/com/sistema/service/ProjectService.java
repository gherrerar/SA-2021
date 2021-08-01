
package br.com.sistema.service;

import br.com.sistema.dto.ProjectDto;
import br.com.sistema.model.Project;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


public interface ProjectService {
    List<Project> findAll();
    Project findById(long id);
    Boolean save(ProjectDto project, MultipartFile[] files) throws IOException;
    Boolean saveEdit(ProjectDto project, MultipartFile[] files, long id) throws IOException;
    void deleteById (long id);
}