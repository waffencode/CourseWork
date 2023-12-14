package com.course.server.service;

import java.io.FileInputStream;
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
            String rootPath = "C:/Users/waffencode/";
            String appConfigPath = rootPath + "application.properties";
            System.out.println(appConfigPath);
            Properties appProps = new Properties();
            appProps.load(new FileInputStream(appConfigPath));

            dbUrl = appProps.getProperty("db_url", "jdbc:mysql://localhost/inventory");
            dbLogin = appProps.getProperty("db_login", "root");
            dbPassword = appProps.getProperty("db_password", "+WHLm0WsP^j6hr7mfD58");
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

