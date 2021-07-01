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
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
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

        LocalDate date = LocalDate.now();
        DateTimeFormatter  dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formattedDate = date.format(dateFormat);
        String[] array = formattedDate.split("/");
        String monthNumber = array[1];
        String month = switch (monthNumber) {
            case "01" -> month = "Janeiro";
            case "02" -> month = "Fevereiro";
            case "03" -> month = "Março";
            case "04" -> month = "Abril";
            case "05" -> month = "Maio";
            case "06" -> month = "Junho";
            case "07" -> month = "Julho";
            case "08" -> month = "Agosto";
            case "09" -> month = "Setembro";
            case "10" -> month = "Outubro";
            case "11" -> month = "Novembro";
            case "12" -> month = "Dezembro";
            default -> "?";
        };
        formattedDate = array[0] + " de " + month + " de " + array[2];

        Project project = new Project(projectDto.getTitle(), user, date,projectDto.getText(), formattedDate);
        for (File file : files){
            file.setProject(project);
        }
        return projectRepository.save(project);
    }

    public Project save(ProjectDto projectDto) {
        Object loggedUser = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email = ((UserDetails)loggedUser).getUsername();
        User user = userRepository.findByEmail(email);

        LocalDate date = LocalDate.now();
        DateTimeFormatter  dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formattedDate = date.format(dateFormat);
        String[] array = formattedDate.split("/");
        String monthNumber = array[1];
        String month = switch (monthNumber) {
            case "01" -> month = "Janeiro";
            case "02" -> month = "Fevereiro";
            case "03" -> month = "Março";
            case "04" -> month = "Abril";
            case "05" -> month = "Maio";
            case "06" -> month = "Junho";
            case "07" -> month = "Julho";
            case "08" -> month = "Agosto";
            case "09" -> month = "Setembro";
            case "10" -> month = "Outubro";
            case "11" -> month = "Novembro";
            case "12" -> month = "Dezembro";
            default -> "?";
        };
        formattedDate = array[0] + " de " + month + " de " + array[2];

        Project project = new Project(projectDto.getTitle(), user, date,projectDto.getText(), formattedDate);
        return projectRepository.save(project);
    }
}
