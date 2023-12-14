package com.course.server.endpoint;

import com.course.server.ApplicationServiceProvider;
import com.course.server.domain.Role;
import com.course.server.domain.User;
import com.course.server.service.JsonStream;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.UUID;

public class UserServlet extends HttpServlet
{
    private final ApplicationServiceProvider applicationServiceProvider;

    public UserServlet(ApplicationServiceProvider applicationServiceProvider)
    {
        this.applicationServiceProvider = applicationServiceProvider;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        applicationServiceProvider.logger.info("GET at User with: " + req.toString());

        if (req.getParameter("id") == null && req.getParameter("login") != null)
        {
            String login = req.getParameter("login");
            User user = applicationServiceProvider.database.getUserByLogin(login);

            resp.setContentType("application/json");
            PrintWriter printWriter = resp.getWriter();
            JsonStream stream = new JsonStream(printWriter);
            stream.write(user);
            printWriter.close();
        }
        else if (req.getParameter("id") != null && req.getParameter("login") == null)
        {
            UUID userId = UUID.fromString(req.getParameter("id"));
            UUID issuedById = UUID.fromString(req.getParameter("by"));

            if (applicationServiceProvider.authenticator.isValidUser(issuedById))
            {
                User user = applicationServiceProvider.database.getUser(userId);

                resp.setContentType("application/json");
                PrintWriter printWriter = resp.getWriter();
                JsonStream stream = new JsonStream(printWriter);
                stream.write(user);
                printWriter.close();
            }
        }
        else
        {
            UUID issuedById = UUID.fromString(req.getParameter("by"));

            if (applicationServiceProvider.authenticator.isValidUser(issuedById) &&
                    applicationServiceProvider.database.getUser(issuedById).getRole().compareTo(Role.ADMINISTRATOR) >= 0)
            {
                List<User> users = applicationServiceProvider.database.getAllUsers();

                resp.setContentType("application/json");
                PrintWriter printWriter = resp.getWriter();
                JsonStream stream = new JsonStream(printWriter);
                stream.writeUserList(users);
                printWriter.close();
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        applicationServiceProvider.logger.info("POST at User with: " + req.toString());

        BufferedReader reader = req.getReader();
        JsonStream stream = new JsonStream(reader);
        User user = stream.readUser();
        applicationServiceProvider.database.createUser(user);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        applicationServiceProvider.logger.info("PUT at User with: " + req.toString());

        UUID issuedById = UUID.fromString(req.getParameter("by"));

        if (applicationServiceProvider.authenticator.isValidUser(issuedById))
        {
            UUID userId = UUID.fromString(req.getParameter("id"));
            BufferedReader reader = req.getReader();
            JsonStream stream = new JsonStream(reader);
            User user = stream.readUser();

            if (userId.equals(user.getId()))
            {
                applicationServiceProvider.database.updateUser(user);
            }
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        applicationServiceProvider.logger.info("DELETE at User with: " + req.toString());

        UUID userId = UUID.fromString(req.getParameter("id"));
        UUID issuedById = UUID.fromString(req.getParameter("by"));

        if (applicationServiceProvider.authenticator.isValidUser(issuedById) &&
                applicationServiceProvider.database.getUser(issuedById).getRole().compareTo(Role.ADMINISTRATOR) >= 0)
        {
            applicationServiceProvider.database.deleteUser(userId);
        }
    }
}
