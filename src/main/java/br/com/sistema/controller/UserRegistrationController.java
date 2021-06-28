package br.com.sistema.controller;

import br.com.sistema.dto.UserRegistrationDto;
import br.com.sistema.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/registration")
public class UserRegistrationController {

    private UserService userService;

    public UserRegistrationController(UserService userService) {
        this.userService = userService;
    }


    @ModelAttribute("user")
    public UserRegistrationDto userRegistrationDto () {
        return new UserRegistrationDto();
    }

    @GetMapping
    public String showRegistrationForm () {
        return "registration";
    }

    @PostMapping
    public String registerUserAccount(@ModelAttribute("user") UserRegistrationDto registrationDto, @RequestParam("email") String email, @RequestParam("password") String password, RedirectAttributes attributes) {
        if (userService.findUserByEmail(email)) {
            userService.save(registrationDto);
            attributes.addFlashAttribute("mensagem", "O registro foi realizado com sucesso!");
        } else {
            attributes.addFlashAttribute("mensagem", "Este email já está cadastrado no sistema!");
            attributes.addFlashAttribute("email", email);
            attributes.addFlashAttribute("password", password);
        }
        return "redirect:/registration";

    }
}
