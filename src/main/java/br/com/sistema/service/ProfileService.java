package br.com.sistema.service;

import br.com.sistema.dto.ProfileRegistrationDto;
import br.com.sistema.model.Profile;
import org.springframework.security.core.userdetails.UserDetailsService;


public interface ProfileService extends UserDetailsService {
    Profile save(ProfileRegistrationDto registrationDto);
    Boolean findUserByEmail(String email);
}
