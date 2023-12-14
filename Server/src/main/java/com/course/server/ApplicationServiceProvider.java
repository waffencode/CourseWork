package com.course.server;

import com.course.server.database.Database;
import com.course.server.service.Authenticator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ApplicationServiceProvider
{
    public final Database database = new Database();
    public final Authenticator authenticator = new Authenticator(database);
    public final Logger logger;

    public ApplicationServiceProvider()
    {
        logger = LogManager.getLogger(ApplicationServiceProvider.class);
    }
}
