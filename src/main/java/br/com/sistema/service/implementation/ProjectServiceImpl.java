package br.com.sistema.service.implementation;

import br.com.sistema.dto.ProjectDto;
import br.com.sistema.model.Image;
import br.com.sistema.model.Profile;
import br.com.sistema.repository.ProjectRepository;
import br.com.sistema.model.Project;
import br.com.sistema.repository.ProfileRepository;
import br.com.sistema.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;


@Service
public class ProjectServiceImpl implements ProjectService {
    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    ProfileRepository profileRepository;

    @Autowired
    ProjectService projectService;

    @Override
    public List<Project> findAll() {
        return projectRepository.findAll();
    }

    @Override
    public Project findById(long id) {
        return projectRepository.findById(id).get();
    }

    @Override
    public void deleteById (long id) { projectRepository.deleteById(id); }

    @Override
    public Boolean save(ProjectDto projectDto, List<String> linkList) {
        ArrayList<Image> images = new ArrayList<>();
        linkList.forEach(link -> {
            images.add(new Image(link));
        });

        Profile profile = findLoggedUser();
        LocalDate date = LocalDate.now();
        String formattedDate = formatDate(date);

        Project project = new Project(projectDto.getTitle(), profile, date,projectDto.getText(), formattedDate, images);
        projectRepository.save(project);

        return true;
    }

    @Override
    public Boolean saveEdit(ProjectDto projectDto, long id) {
        Project project = projectService.findById(id);
        if (project != null){
            project.setText(projectDto.getText());
            project.setTitle(projectDto.getTitle());
        } else {
            return false;
        }
        projectRepository.save(project);
        return true;
    }

    public Profile findLoggedUser () {
        Object loggedUser = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email = ((UserDetails)loggedUser).getUsername();
        return profileRepository.findByEmail(email);
    }

    public String formatDate (LocalDate date) {
        DateTimeFormatter  dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formattedDate = date.format(dateFormat);
        String[] slicedDate = formattedDate.split("/");
        String monthNumber = slicedDate[1];
        String month = switch (monthNumber) {
            case "01" -> "Janeiro";
            case "02" -> "Fevereiro";
            case "03" -> "Março";
            case "04" -> "Abril";
            case "05" -> "Maio";
            case "06" -> "Junho";
            case "07" -> "Julho";
            case "08" -> "Agosto";
            case "09" -> "Setembro";
            case "10" -> "Outubro";
            case "11" -> "Novembro";
            case "12" -> "Dezembro";
            default -> "?";
        };
        return slicedDate[0] + " de " + month + " de " + slicedDate[2];
    }
}
