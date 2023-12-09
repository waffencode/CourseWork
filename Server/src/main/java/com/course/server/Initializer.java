package com.course.server;

import com.course.server.database.Database;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class Initializer implements ServletContextListener
{
    @Override
    public void contextInitialized(ServletContextEvent sce)
    {
        Database database = new Database();
        ServletContext context = sce.getServletContext();
        context.addServlet("MainServlet", new MainServlet(database)).addMapping("/hello");
        context.addServlet("ListServlet", new ListServlet(database)).addMapping("/list");
        context.addServlet("ObjectServlet", new ObjectServlet(database)).addMapping("/object");
        context.addServlet("ObjectViewListServlet", new ObjectViewListServlet(database)).addMapping("/object/view_list");
        context.addServlet("UserServlet", new UserServlet(database)).addMapping("/user");
        context.addServlet("UserLoginServlet", new UserLoginServlet(database)).addMapping("/user/login");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce)
    {

    }
}