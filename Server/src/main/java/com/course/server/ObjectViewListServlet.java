package com.course.server;

import com.course.server.database.Database;
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

public class ObjectViewListServlet  extends HttpServlet
{
    private final Database database;

    public ObjectViewListServlet(Database database)
    {
        this.database = database;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        UUID listId = UUID.fromString(req.getParameter("list_id"));
        List<InventoryObject> objects = database.getAllObjectsFromList(listId);

        resp.setContentType("text/json");
        PrintWriter printWriter = resp.getWriter();
        JsonStream stream = new JsonStream(printWriter);
        stream.write(objects);
        printWriter.close();
    }
}
