package br.com.sistema.controller;

import br.com.sistema.dto.UserRegistrationDto;
import br.com.sistema.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserRegistrationController {

    private UserService userService;

    public UserRegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/registration")
    public String showRegistrationForm () {
        return "registration";
    }

    @PostMapping("/registration")
    public ResponseEntity<?> registerUserAccount(@ModelAttribute("user") UserRegistrationDto registrationDto) {
        if (userService.findUserByEmail(registrationDto.getEmail())) {
            try {
                userService.save(registrationDto);
            } catch (Exception e){
                e.printStackTrace();
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao cadastrar o usuário!");
            }
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email já cadastrado no sistema!");
        }
        return ResponseEntity.ok("Usuário cadastrado com sucesso!");
    }
}
