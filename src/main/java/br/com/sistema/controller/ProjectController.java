package br.com.sistema.controller;

import br.com.sistema.dto.ProjectDto;
import br.com.sistema.dto.UserRegistrationDto;
import br.com.sistema.model.Project;
import br.com.sistema.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Collections;

@Controller
public class ProjectController {
    @Autowired
    ProjectService projectService;

    @GetMapping("/projects")
    public ModelAndView getProjects () {
        ModelAndView my = new ModelAndView("projects");
        List<Project> projects = projectService.findAll();

//        --> reversão no array para sempre renderizar os projetos mais novos.
//        Collections.reverse(projects);
        my.addObject("projects", projects);
        return my;
    }

    @GetMapping("/projects/{id}")
    public ModelAndView getProjectDetails (@PathVariable ("id") long id) {
        ModelAndView my = new ModelAndView("projectDetails");
        Project project = projectService.findById(id);
        my.addObject("project", project);
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
    public String saveProject(@ModelAttribute("project") ProjectDto projectDto, @RequestParam("files") MultipartFile[] files, BindingResult result, RedirectAttributes attributes){
        projectDto.setDate(LocalDate.now());
        if(files.length > 1){
            projectService.save(projectDto, files);
        } else {
            projectService.save(projectDto);
        }

        return "redirect:/";
    }
}
