package com.course.client.service.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Config
{
    private String hostname;
    private int port;

    public Config()
    {
        try
        {
            String appConfigPath = "application.properties";
            Properties appProps = new Properties();
            appProps.load(new FileInputStream(appConfigPath));

            hostname = appProps.getProperty("hostname", "localhost");
            port = Integer.parseInt(appProps.getProperty("port", "8080"));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public String getHostname()
    {
        return hostname;
    }

    public void setHostname(String hostname)
    {
        this.hostname = hostname;
    }

    public int getPort()
    {
        return port;
    }

    public void setPort(int port)
    {
        this.port = port;
    }
}
