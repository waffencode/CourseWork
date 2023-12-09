package com.course.server;

import com.course.server.database.Database;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import java.io.*;

public class MainServlet extends HttpServlet
{
    private final Database database;

    public MainServlet(Database database)
    {
        this.database = database;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/json");
        PrintWriter printWriter = resp.getWriter();
        printWriter.write("0.1.0");
        printWriter.close();
    }
}
