package br.com.sistema.service;

import br.com.sistema.dto.UserRegistrationDto;
import br.com.sistema.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;


public interface UserService extends UserDetailsService {
    User save(UserRegistrationDto registrationDto);
    Boolean findUserByEmail(String email);
}
