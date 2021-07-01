package br.com.sistema.service.implementation;

import br.com.sistema.dto.UserRegistrationDto;
import br.com.sistema.model.Role;
import br.com.sistema.model.User;
import br.com.sistema.repository.UserRepository;
import br.com.sistema.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User save(UserRegistrationDto registrationDto) {
        User user = new User (registrationDto.getEmail().toLowerCase(),
                passwordEncoder.encode(registrationDto.getPassword()), Arrays.asList(new Role("USER")));

        return userRepository.save(user);
    }

    @Override
    public Boolean findUserByEmail (String email) {
        return (userRepository.findByEmail(email) == null);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException{
            User user = userRepository.findByEmail(email);

            //TODO refatorar o erro.
            if (user == null){
                throw new UsernameNotFoundException("");
            }

            return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }

}
