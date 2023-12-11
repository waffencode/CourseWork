package com.course.server.service;

import com.course.server.database.Database;

import java.util.UUID;

public class Authenticator
{
    private final Database database;

    public Authenticator(Database database)
    {
        this.database = database;
    }

    public boolean isValidLoginData(String login, String passwordHash)
    {
        return database.isValidLoginData(login, passwordHash);
    }

    public boolean isValidUser(UUID id)
    {
        return database.getUser(id) != null;
    }
}
