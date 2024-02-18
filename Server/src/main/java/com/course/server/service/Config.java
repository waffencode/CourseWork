package com.course.server.service;

import java.io.IOException;
import java.util.Properties;

public class Config
{
    private String dbUrl;
    private String dbLogin;
    private String dbPassword;

    public Config()
    {
        try
        {
            Properties appProps = new Properties();
            appProps.load(Config.class.getClassLoader().getResourceAsStream("application.properties"));

            dbUrl = appProps.getProperty("db_url");
            dbLogin = appProps.getProperty("db_login");
            dbPassword = appProps.getProperty("db_password");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public String getDbUrl()
    {
        return dbUrl;
    }

    public String getDbLogin()
    {
        return dbLogin;
    }

    public String getDbPassword()
    {
        return dbPassword;
    }
}

