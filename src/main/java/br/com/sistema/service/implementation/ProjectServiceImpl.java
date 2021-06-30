package br.com.sistema.service.implementation;

import br.com.sistema.dto.ProjectDto;
import br.com.sistema.model.File;
import br.com.sistema.model.Role;
import br.com.sistema.repository.ProjectRepository;
import br.com.sistema.model.Project;
import br.com.sistema.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {
    @Autowired
    ProjectRepository projectRepository;

    @Override
    public List<Project> findAll() {
        return projectRepository.findAll();
    }

    @Override
    public Project findById(long id) {
        return projectRepository.findById(id).get();
    }

    @Override
    public Project save(ProjectDto projectDto, MultipartFile[] mpFiles) {
        ArrayList<File> files = new ArrayList();
        Arrays.asList(mpFiles).stream().forEach(file -> {
            try {
                files.add(new File(file.getBytes(), file.getName()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        Project project = new Project(projectDto.getTitle(), projectDto.getAuthor(), projectDto.getDate(),projectDto.getText(), files);
        for (File file : files){
            file.setProject(project);
        }
        return projectRepository.save(project);
    }
}
