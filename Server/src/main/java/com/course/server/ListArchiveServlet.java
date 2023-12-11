package com.course.server;

import com.course.server.database.Database;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.UUID;

public class ListArchiveServlet extends HttpServlet
{
    private final Database database;

    public ListArchiveServlet(Database database)
    {
        this.database = database;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        UUID listId = UUID.fromString(req.getParameter("id"));
        database.archiveList(listId);
    }
}

