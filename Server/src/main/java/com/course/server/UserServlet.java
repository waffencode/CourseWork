package com.course.server;

import com.course.server.database.Database;
import com.course.server.domain.User;
import com.course.server.service.JsonStream;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.UUID;

public class UserServlet extends HttpServlet
{
    private final Database database;

    public UserServlet(Database database)
    {
        this.database = database;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        UUID userId = UUID.fromString(req.getParameter("id"));
        User user = database.getUser(userId);

        resp.setContentType("text/json");
        PrintWriter printWriter = resp.getWriter();
        JsonStream stream = new JsonStream(printWriter);
        stream.write(user);
        printWriter.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        BufferedReader reader = req.getReader();
        JsonStream stream = new JsonStream(reader);
        User user = stream.readUser();
        database.createUser(user);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {

    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {

    }
}
