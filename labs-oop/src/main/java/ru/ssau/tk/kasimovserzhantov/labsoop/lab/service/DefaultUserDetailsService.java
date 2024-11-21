package ru.ssau.tk.kasimovserzhantov.labsoop.lab.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.ssau.tk.kasimovserzhantov.labsoop.lab.entity.LabUser;
import ru.ssau.tk.kasimovserzhantov.labsoop.lab.repository.LabUserRepository;
import ru.ssau.tk.kasimovserzhantov.labsoop.lab.security.DefaultUserDetails;

@Service
@RequiredArgsConstructor
public class DefaultUserDetailsService implements UserDetailsService {

    private final LabUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LabUser user = this.userRepository
                .findByUsername(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User with username: " + username + " not found"));

        return DefaultUserDetails.build(user);
    }
}
