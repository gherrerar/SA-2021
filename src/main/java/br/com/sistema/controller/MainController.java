package br.com.sistema.controller;

import br.com.sistema.model.Project;
import br.com.sistema.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Controller
public class MainController {
    @Autowired
    ProjectService projectService;

    @GetMapping("/")
    public ModelAndView index() {
        ModelAndView my = new ModelAndView("index");

        Random rand = new Random();
        List<Project> allProjects = projectService.findAll();
        List<Project> randomProjects = new ArrayList<>();

        if (allProjects.size() == 3) {
            my.addObject("projects", allProjects);
        } else if (allProjects.size() > 3){
            int numberOfElements = 3;
            while (randomProjects.size() < numberOfElements) {
                int randomIndex = rand.nextInt(allProjects.size());
                Project randomElement = allProjects.get(randomIndex);
                allProjects.remove(randomIndex);
                randomProjects.add(randomElement);
            }
            my.addObject("projects", randomProjects);
        }
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
