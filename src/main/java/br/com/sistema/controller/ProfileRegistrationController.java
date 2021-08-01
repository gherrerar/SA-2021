package br.com.sistema.controller;

import br.com.sistema.dto.ProfileRegistrationDto;
import br.com.sistema.service.ProfileService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class ProfileRegistrationController {

    private ProfileService profileService;

    public ProfileRegistrationController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @GetMapping("/registration")
    public String showRegistrationForm () {
        return "registration";
    }

    @PostMapping("/registration")
    public ResponseEntity<?> registerUserAccount(@ModelAttribute("user") ProfileRegistrationDto registrationDto) {
        if (profileService.findUserByEmail(registrationDto.getEmail())) {
            try {
                profileService.save(registrationDto);
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
