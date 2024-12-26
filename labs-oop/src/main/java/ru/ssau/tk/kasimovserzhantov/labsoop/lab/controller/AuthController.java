package ru.ssau.tk.kasimovserzhantov.labsoop.lab.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ru.ssau.tk.kasimovserzhantov.labsoop.lab.entity.LabUser;
import ru.ssau.tk.kasimovserzhantov.labsoop.lab.repository.LabUserRepository;
import ru.ssau.tk.kasimovserzhantov.labsoop.lab.security.JwtResponse;
import ru.ssau.tk.kasimovserzhantov.labsoop.lab.security.JwtUtils;
import ru.ssau.tk.kasimovserzhantov.labsoop.lab.security.LoginRequest;

@Deprecated
@RestController
@RequiredArgsConstructor
@RequestMapping("lab/auth")
public class AuthController {

    private final AuthenticationManager authManager;

    private final JwtUtils jwtUtils;

    private final PasswordEncoder passwordEncoder;

    private final UserDetailsService userDetailsService;

    private final LabUserRepository userRepository;

    @PostMapping("register")
    public ResponseEntity<?> registerUser(@RequestBody LabUser user) {
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            return ResponseEntity.badRequest().body("Error: Username is already taken!");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        userRepository.save(user);

        return ResponseEntity.ok("User registered successfully!");
    }

    @PostMapping("login")
    public ResponseEntity<JwtResponse> authenticateUser(@RequestBody LoginRequest loginRequest) {
        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getUsername());
        String jwt = jwtUtils.generateJwtToken(userDetails);

        return ResponseEntity.ok(new JwtResponse(jwt));
    }

    @DeleteMapping("delete/{username}")
    public ResponseEntity<?> deleteUser(@PathVariable String username, @AuthenticationPrincipal UserDetails currentUser) {
        if (!currentUser.getUsername().equals(username)) {
            return ResponseEntity.badRequest().body("Error: You can only delete your own account.");
        }

        return userRepository.findByUsername(username).map(user -> {
            userRepository.delete(user);
            return ResponseEntity.ok("User deleted successfully!");
        }).orElse(ResponseEntity.badRequest().body("Error: User not found"));
    }

}
