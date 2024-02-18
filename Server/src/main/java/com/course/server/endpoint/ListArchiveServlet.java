package com.course.server.endpoint;

import com.course.server.ApplicationServiceProvider;
import com.course.server.domain.Role;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.UUID;

public class ListArchiveServlet extends HttpServlet
{
    private final ApplicationServiceProvider applicationServiceProvider;

    public ListArchiveServlet(ApplicationServiceProvider applicationServiceProvider)
    {
        this.applicationServiceProvider = applicationServiceProvider;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
//        applicationServiceProvider.logger.info("GET at ListArchive with: " + req.toString());

        UUID listId = UUID.fromString(req.getParameter("id"));
        int action = Integer.parseInt(req.getParameter("action"));
        UUID userId = UUID.fromString(req.getParameter("by"));

        if (applicationServiceProvider.authenticator.isValidUser(userId) &&
            applicationServiceProvider.database.getUser(userId).getRole().compareTo(Role.INVENTORY_OFFICER) >= 0)
        {
            if (action == 0)
            {
                applicationServiceProvider.database.archiveList(listId, userId);
            }
            else if (action == 1)
            {
                applicationServiceProvider.database.restoreList(listId);
            }
        }
    }
}

