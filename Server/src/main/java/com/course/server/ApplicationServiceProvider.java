package com.course.server;

import com.course.server.database.Database;
import com.course.server.service.Authenticator;

public class ApplicationServiceProvider
{
    public final Database database = new Database();
    public final Authenticator authenticator = new Authenticator(database);
}
