package com.course.server.endpoint;

import com.course.server.ApplicationServiceProvider;
import com.course.server.domain.InventoryObjectsList;
import com.course.server.domain.Role;
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
        UUID issuedById = UUID.fromString(req.getParameter("by"));

        if (applicationServiceProvider.authenticator.isValidUser(issuedById))
        {
            if (req.getParameter("id") == null)
            {
                List<InventoryObjectsList> list = applicationServiceProvider.database.getAllLists();
                resp.setContentType("application/json");
                PrintWriter printWriter = resp.getWriter();
                JsonStream stream = new JsonStream(printWriter);
                stream.writeObjectsList(list);
                printWriter.close();
            }
            else
            {
                UUID listId = UUID.fromString(req.getParameter("id"));

                InventoryObjectsList list = applicationServiceProvider.database.getList(listId);

                resp.setContentType("application/json");
                PrintWriter printWriter = resp.getWriter();
                JsonStream stream = new JsonStream(printWriter);
                stream.write(list);
                printWriter.close();
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        UUID issuedById = UUID.fromString(req.getParameter("by"));

        if (applicationServiceProvider.authenticator.isValidUser(issuedById) &&
                applicationServiceProvider.database.getUser(issuedById).getRole().compareTo(Role.INVENTORY_OFFICER) >= 0)
        {
            BufferedReader reader = req.getReader();
            JsonStream stream = new JsonStream(reader);
            InventoryObjectsList list = stream.readList();
            applicationServiceProvider.database.createList(list);
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        UUID issuedById = UUID.fromString(req.getParameter("by"));

        if (applicationServiceProvider.authenticator.isValidUser(issuedById) &&
                applicationServiceProvider.database.getUser(issuedById).getRole().compareTo(Role.INVENTORY_OFFICER) >= 0)
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
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        UUID issuedById = UUID.fromString(req.getParameter("by"));

        if (applicationServiceProvider.authenticator.isValidUser(issuedById) &&
                applicationServiceProvider.database.getUser(issuedById).getRole().compareTo(Role.ADMINISTRATOR) >= 0)
        {
            UUID listId = UUID.fromString(req.getParameter("id"));
            applicationServiceProvider.database.deleteList(listId);
        }
    }
}

