package com.course.server.endpoint;

import com.course.server.ApplicationServiceProvider;
import com.course.server.database.Database;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.UUID;

public class UserLoginServlet extends HttpServlet
{
    private final ApplicationServiceProvider applicationServiceProvider;

    public UserLoginServlet(ApplicationServiceProvider applicationServiceProvider)
    {
        this.applicationServiceProvider = applicationServiceProvider;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        String login = req.getParameter("login");
        String passwordHash = req.getParameter("password_hash");

        if (applicationServiceProvider.authenticator.isValidLoginData(login, passwordHash))
        {
            UUID userId = applicationServiceProvider.database.getUserByLogin(login).getId();
            resp.setContentType("text/plain");
            PrintWriter printWriter = resp.getWriter();
            printWriter.write(userId.toString());
            printWriter.close();
        }
    }
}
