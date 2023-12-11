package com.course.server;

import com.course.server.database.Database;
import com.course.server.domain.InventoryObjectsList;
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
import java.util.UUID;

public class ListServlet extends HttpServlet
{
    private final Database database;

    public ListServlet(Database database)
    {
        this.database = database;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        UUID listId = UUID.fromString(req.getParameter("id"));
        InventoryObjectsList list = database.getList(listId);

        resp.setContentType("text/json");
        PrintWriter printWriter = resp.getWriter();
        JsonStream stream = new JsonStream(printWriter);
        stream.write(list);
        printWriter.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        BufferedReader reader = req.getReader();
        JsonStream stream = new JsonStream(reader);
        InventoryObjectsList list = stream.readList();
        database.createList(list);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        UUID listId = UUID.fromString(req.getParameter("id"));
        BufferedReader reader = req.getReader();
        JsonStream stream = new JsonStream(reader);
        InventoryObjectsList list = stream.readList();

        if (listId.equals(list.getId()))
        {
            database.updateList(list);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        UUID listId = UUID.fromString(req.getParameter("id"));
        database.deleteList(listId);
    }
}

