package br.com.sistema.service.implementation;

import br.com.sistema.dto.ProfileRegistrationDto;
import br.com.sistema.model.Role;
import br.com.sistema.model.Profile;
import br.com.sistema.repository.ProfileRepository;
import br.com.sistema.service.ProfileService;
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
public class ProfileServiceImpl implements ProfileService {

    private ProfileRepository profileRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public ProfileServiceImpl(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    @Override
    public Profile save(ProfileRegistrationDto registrationDto) {
        Profile profile = new Profile(registrationDto.getEmail().toLowerCase(),
                passwordEncoder.encode(registrationDto.getPassword()), Arrays.asList(new Role("USER")));

        return profileRepository.save(profile);
    }

    @Override
    public Boolean findUserByEmail (String email) {
        return (profileRepository.findByEmail(email) == null);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException{
            Profile profile = profileRepository.findByEmail(email);

            if (profile == null){
                throw new UsernameNotFoundException("");
            }

            return new org.springframework.security.core.userdetails.User(profile.getEmail(), profile.getPassword(), mapRolesToAuthorities(profile.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }

}
