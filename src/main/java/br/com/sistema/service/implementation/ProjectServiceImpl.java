package br.com.sistema.service.implementation;

import br.com.sistema.dto.ProjectDto;
import br.com.sistema.model.File;
import br.com.sistema.model.User;
import br.com.sistema.repository.ProjectRepository;
import br.com.sistema.model.Project;
import br.com.sistema.repository.UserRepository;
import br.com.sistema.service.FileService;
import br.com.sistema.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Service
public class ProjectServiceImpl implements ProjectService {
    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    FileService fileService;

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
        Path currentPath = Paths.get(".");
        Path absolutePath = currentPath.toAbsolutePath();
        ArrayList<File> files = new ArrayList<>();
        Arrays.stream(mpFiles).forEach(file -> {
            files.add(new File(file.getOriginalFilename(), absolutePath + "/src/main/resources/static/photos/"));
            try {
                byte[] bytes = file.getBytes();
                Path path = Paths.get(absolutePath + "/src/main/resources/static/photos/" + file.getOriginalFilename());
                try {
                    Files.write(path, bytes);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        Object loggedUser = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email = ((UserDetails)loggedUser).getUsername();
        User user = userRepository.findByEmail(email);

        LocalDate date = LocalDate.now();
        String formattedDate = formatDate(date);

        Project project = new Project(projectDto.getTitle(), user, date,projectDto.getText(), formattedDate);

        for (File file : files){
            file.setProject(project);
            fileService.save(file);
        }
        return projectRepository.save(project);
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
