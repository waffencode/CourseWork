package com.course.server;

import com.course.server.database.Database;
import com.course.server.domain.InventoryObject;
import com.course.server.domain.InventoryObjectsList;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

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
        UUID inventoryNumber = UUID.fromString(req.getParameter("id"));
        InventoryObjectsList object = database.getList(inventoryNumber);
        String responseContent = object.toString();

        resp.setContentType("text/json");
        PrintWriter printWriter = resp.getWriter();
        printWriter.write(responseContent);
        printWriter.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {

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
