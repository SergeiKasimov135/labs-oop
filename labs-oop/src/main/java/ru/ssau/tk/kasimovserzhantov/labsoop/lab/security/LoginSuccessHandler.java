package ru.ssau.tk.kasimovserzhantov.labsoop.lab.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import ru.ssau.tk.kasimovserzhantov.labsoop.lab.functions.factory.ArrayTabulatedFunctionFactory;

import java.io.IOException;

public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {

        HttpSession session = request.getSession();

        // Инициализируем фабрику по умолчанию
        if (session.getAttribute("FACTORY_KEY") == null) {
            session.setAttribute("FACTORY_KEY", new ArrayTabulatedFunctionFactory());
        }

        // Продолжаем обработку после успешного логина
        response.sendRedirect("/");
    }
}