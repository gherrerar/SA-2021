package br.com.sistema.service.implementation;

import br.com.sistema.dto.ProjectDto;
import br.com.sistema.model.File;
import br.com.sistema.model.User;
import br.com.sistema.repository.ProjectRepository;
import br.com.sistema.model.Project;
import br.com.sistema.repository.UserRepository;
import br.com.sistema.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {
    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    UserRepository userRepository;


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
        ArrayList<File> files = new ArrayList<>();
        Arrays.stream(mpFiles).forEach(file -> {
            try {
                files.add(new File(file.getBytes(), file.getOriginalFilename()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        Object loggedUser = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email = ((UserDetails)loggedUser).getUsername();
        User user = userRepository.findByEmail(email);

        Project project = new Project(projectDto.getTitle(), user, projectDto.getDate(),projectDto.getText(), files);
        for (File file : files){
            file.setProject(project);
        }
        return projectRepository.save(project);
    }

    public Project save(ProjectDto projectDto) {
        Object loggedUser = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email = ((UserDetails)loggedUser).getUsername();
        User user = userRepository.findByEmail(email);
        Project project = new Project(projectDto.getTitle(), user, projectDto.getDate(),projectDto.getText());
        return projectRepository.save(project);
    }
}
