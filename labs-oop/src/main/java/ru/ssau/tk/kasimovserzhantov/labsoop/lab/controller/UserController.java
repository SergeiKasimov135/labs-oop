package ru.ssau.tk.kasimovserzhantov.labsoop.lab.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import ru.ssau.tk.kasimovserzhantov.labsoop.lab.dto.LabUserDTO;
import ru.ssau.tk.kasimovserzhantov.labsoop.lab.entity.LabUser;
import ru.ssau.tk.kasimovserzhantov.labsoop.lab.entity.UserRole;
import ru.ssau.tk.kasimovserzhantov.labsoop.lab.repository.LabUserRepository;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final AuthenticationManager authenticationManager;

    private final LabUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @PostMapping("/login")
    public String loginSubmit(@RequestParam("username") String username,
                              @RequestParam("password") String password,
                              Model model,
                              HttpServletRequest request) {
        try {
            // Создаем объект аутентификации
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );

            // Устанавливаем аутентификацию в SecurityContext
            SecurityContextHolder.getContext().setAuthentication(authentication);

            return "redirect:/home";
        } catch (AuthenticationException e) {
            // Если аутентификация не удалась, показываем ошибку
            model.addAttribute("error", "Неверное имя пользователя или пароль");
            return "login"; // Отображаем страницу логина с ошибкой
        }
    }

    @PostMapping("/reg")
    public ResponseEntity<String> registerUser(@RequestBody LabUserDTO userDto) {
        if (userRepository.findByUsername(userDto.getUsername()).isPresent()) {
            return ResponseEntity.badRequest().body("Пользователь с таким именем уже существует");
        }

        LabUser newUser = new LabUser();
        newUser.setUsername(userDto.getUsername());
        newUser.setPassword(passwordEncoder.encode(userDto.getPassword()));
        newUser.setRole(userDto.getRole());

        userRepository.save(newUser);
        return ResponseEntity.ok("Пользователь успешно зарегистрирован");
    }

    @GetMapping("/register")
    public String register(Model model) {
        return "registration";
    }

    @PostMapping("/registere")
    public String registerUser(@RequestParam String username,
                               @RequestParam String password,
                               Model model) {
        if (userRepository.findByUsername(username).isPresent()) {
            model.addAttribute("message", "Пользователь с таким именем уже существует");
            return "registration"; // возвращаем страницу регистрации с ошибкой
        }

        LabUser newUser = new LabUser();
        newUser.setUsername(username);
        newUser.setPassword(passwordEncoder.encode(password));
        newUser.setRole(UserRole.ADMIN);

        userRepository.save(newUser);
        model.addAttribute("message", "Пользователь успешно зарегистрирован");
        return "registration"; // возвращаем страницу регистрации с успешным сообщением
    }

}
