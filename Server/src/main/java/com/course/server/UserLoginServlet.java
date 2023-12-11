package com.course.server;

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
    private final Database database;

    public UserLoginServlet(Database database)
    {
        this.database = database;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        String login = req.getParameter("login");
        String passwordHash = req.getParameter("password_hash");

        if (database.isValidLoginData(login, passwordHash))
        {
            UUID userId = database.getUserByLogin(login).getId();
            resp.setContentType("text/plain");
            PrintWriter printWriter = resp.getWriter();
            printWriter.write(userId.toString());
            printWriter.close();
        }
    }
}
