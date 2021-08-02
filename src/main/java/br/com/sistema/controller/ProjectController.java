package br.com.sistema.controller;

import br.com.sistema.dto.ProjectDto;;
import br.com.sistema.model.Image;
import br.com.sistema.model.Project;
import br.com.sistema.service.FileService;
import br.com.sistema.service.ProjectService;
import org.dom4j.rule.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import java.util.List;

@Controller
public class ProjectController {
    @Autowired
    ProjectService projectService;

    @Autowired
    FileService fileService;

    @GetMapping("/projects")
    public ModelAndView getProjects () {
        ModelAndView my = new ModelAndView("projects");
        List<Project> projects = projectService.findAll();
        my.addObject("projects", projects);
        return my;
    }

    @GetMapping("/projects/{id}")
    public ModelAndView getProjectDetails (@PathVariable ("id") long id) {
        ModelAndView my = new ModelAndView("projectDetails");
        Project project = projectService.findById(id);
        List<Image> images = fileService.findAllByProjectId(id);
        System.out.println(images.size());

        String username = getLoggedUsername();
        boolean hasAdminRole = checkIfHasAdmRole();

        if (username.equals(project.getUserName()) || hasAdminRole) {
            my.addObject("deleteAndEditAuthorized", true);
        } else {
            my.addObject("deleteAndEditAuthorized", false);
        }

        my.addObject("project", project);
        my.addObject("images", images);
        return my;
    }

    @ModelAttribute("user")
    public ProjectDto projectDto () {
        return new ProjectDto();
    }

    @GetMapping("/newproject")
    public String getProjectForm(){
        return "projectForm";
    }

    @PostMapping("/newproject")
    public ResponseEntity<?> saveProject(@ModelAttribute("project") ProjectDto projectDto, @RequestParam("files") MultipartFile[] files) {
        if (projectService.save(projectDto, files)){
            return ResponseEntity.ok("Projeto cadastrado com êxito");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao salvar o projeto!");
    }

    //TODO delete e edit

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteProject (@PathVariable ("id") long id) {
        String username = getLoggedUsername();
        Project project = projectService.findById(id);
        boolean hasAdminRole = checkIfHasAdmRole();
        if (project != null) {
            if (username.equals(project.getUserName()) || hasAdminRole) {
                if(fileService.deleteFilesInFolder(id)) {
                    projectService.deleteById(id);
                    return ResponseEntity.ok("Projeto deletado com êxito");
                } else {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao salvar o projeto!");
                }
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Você não tem permissão de deletar este projeto!");
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Este projeto não foi encontrado!");
        }
    }

    @GetMapping(value = "/editForm/{id}")
    public ModelAndView editForm (@PathVariable("id") long id) {
        ModelAndView my = new ModelAndView("editForm");
        Project project = projectService.findById(id);
        my.addObject("project", project);
        return my;
    }

    @PostMapping(value="/edit/{id}")
    public String update(@PathVariable("id") long id, @ModelAttribute("project") ProjectDto projectDto, @RequestParam("files") MultipartFile[] files) {

        if(projectService.saveEdit(projectDto, files, id)){
            return "redirect:/projects/" + id;
        } else {
            return "redirect:/projects";
        }
    }

    private String getLoggedUsername () {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }
        return username;
    }

    private Boolean checkIfHasAdmRole () {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getAuthorities().stream().anyMatch(role -> role.getAuthority().equals("ADMIN"));
    }
}
