package com.course.server.endpoint;

import com.course.server.ApplicationServiceProvider;
import com.course.server.database.Database;
import com.course.server.domain.InventoryObject;
import com.course.server.domain.Role;
import com.course.server.service.JsonStream;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.UUID;

public class ObjectServlet extends HttpServlet
{
    private final ApplicationServiceProvider applicationServiceProvider;

    public ObjectServlet(ApplicationServiceProvider applicationServiceProvider)
    {
        this.applicationServiceProvider = applicationServiceProvider;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        UUID issuedById = UUID.fromString(req.getParameter("by"));

        if (applicationServiceProvider.authenticator.isValidUser(issuedById))
        {
            String inventoryNumber = req.getParameter("id");
            InventoryObject object = applicationServiceProvider.database.getObject(inventoryNumber);

            resp.setContentType("text/json");
            PrintWriter printWriter = resp.getWriter();
            JsonStream stream = new JsonStream(printWriter);
            stream.write(object);
            printWriter.close();
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
            InventoryObject object = stream.readObject();
            applicationServiceProvider.database.createObject(object);
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        UUID issuedById = UUID.fromString(req.getParameter("by"));

        if (applicationServiceProvider.authenticator.isValidUser(issuedById) &&
                applicationServiceProvider.database.getUser(issuedById).getRole().compareTo(Role.INVENTORY_OFFICER) >= 0)
        {
            String inventoryNumber = req.getParameter("id");
            BufferedReader reader = req.getReader();
            JsonStream stream = new JsonStream(reader);
            InventoryObject object = stream.readObject();

            if (inventoryNumber.equals(object.getInventoryNumber()))
            {
                applicationServiceProvider.database.updateObject(object);
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
            String inventoryNumber = req.getParameter("id");
            applicationServiceProvider.database.deleteObject(inventoryNumber);
        }
    }
}
