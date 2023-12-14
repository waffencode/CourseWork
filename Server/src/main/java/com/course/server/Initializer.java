package com.course.server;

import com.course.server.endpoint.*;
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
        ApplicationServiceProvider applicationServiceProvider = new ApplicationServiceProvider();
        ServletContext context = sce.getServletContext();

        context.addServlet("ListServlet", new ListServlet(applicationServiceProvider)).addMapping("/list");
        context.addServlet("ListArchiveServlet", new ListArchiveServlet(applicationServiceProvider)).addMapping("/list/archive");
        context.addServlet("ObjectServlet", new ObjectServlet(applicationServiceProvider)).addMapping("/object");
        context.addServlet("ObjectViewListServlet", new ObjectViewListServlet(applicationServiceProvider)).addMapping("/object/view_list");
        context.addServlet("UserServlet", new UserServlet(applicationServiceProvider)).addMapping("/user");
        context.addServlet("UserLoginServlet", new UserLoginServlet(applicationServiceProvider)).addMapping("/user/login");
        context.addServlet("SearchServlet", new SearchServlet(applicationServiceProvider)).addMapping("/object/search");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce)
    {

    }
}