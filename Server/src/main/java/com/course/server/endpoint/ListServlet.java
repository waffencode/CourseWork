package com.course.server.endpoint;

import com.course.server.ApplicationServiceProvider;
import com.course.server.database.Database;
import com.course.server.domain.InventoryObjectsList;
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
    private final ApplicationServiceProvider applicationServiceProvider;

    public ListServlet(ApplicationServiceProvider applicationServiceProvider)
    {
        this.applicationServiceProvider = applicationServiceProvider;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        UUID listId = UUID.fromString(req.getParameter("id"));
        InventoryObjectsList list = applicationServiceProvider.database.getList(listId);

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
        applicationServiceProvider.database.createList(list);
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
            applicationServiceProvider.database.updateList(list);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        UUID listId = UUID.fromString(req.getParameter("id"));
        applicationServiceProvider.database.deleteList(listId);
    }
}
