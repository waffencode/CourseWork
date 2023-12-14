package com.course.server.endpoint;

import com.course.server.ApplicationServiceProvider;
import com.course.server.domain.InventoryObject;
import com.course.server.service.JsonStream;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.UUID;

public class SearchServlet extends HttpServlet
{
    private final ApplicationServiceProvider applicationServiceProvider;

    public SearchServlet(ApplicationServiceProvider applicationServiceProvider)
    {
        this.applicationServiceProvider = applicationServiceProvider;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        UUID issuedById = UUID.fromString(req.getParameter("by"));

        if (applicationServiceProvider.authenticator.isValidUser(issuedById))
        {
            String name = req.getParameter("name");
            String id = req.getParameter("id");

            if (name != null)
            {
                List<InventoryObject> objects = applicationServiceProvider.database.searchObjectsByName(name);

                resp.setContentType("application/json");
                PrintWriter printWriter = resp.getWriter();
                JsonStream stream = new JsonStream(printWriter);
                stream.write(objects);
                printWriter.close();
            }
            else if (id != null)
            {
                List<InventoryObject> objects = applicationServiceProvider.database.searchObjectsByNumber(id);

                resp.setContentType("application/json");
                PrintWriter printWriter = resp.getWriter();
                JsonStream stream = new JsonStream(printWriter);
                stream.write(objects);
                printWriter.close();
            }
        }
    }
}
