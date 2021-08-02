package br.com.sistema.controller;

import br.com.sistema.model.Project;
import br.com.sistema.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class MainController {
    @Autowired
    ProjectService projectService;

    @GetMapping("/")
    public ModelAndView index() {
        ModelAndView my = new ModelAndView("index");
        List<Project> projects = projectService.findAll();

        my.addObject("projects", projects);
        return my;
    }

    @GetMapping("/403")
    public String error403(){
        return "403";
    }


    @GetMapping("/sobre")
    public String sobre() {
        return "sobre";
    }
}
