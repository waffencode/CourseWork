package com.course.server.domain;

import java.sql.Timestamp;
import java.util.UUID;

public class User
{
    private UUID id = UUID.randomUUID();
    private String login;
    private String passwordHash;
    private Role role;
    private Timestamp registrationDate = new Timestamp(System.currentTimeMillis());

    public UUID getId()
    {
        return id;
    }

    public void setId(UUID id)
    {
        this.id = id;
    }

    public String getLogin()
    {
        return login;
    }

    public void setLogin(String login)
    {
        this.login = login;
    }

    public String getPasswordHash()
    {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash)
    {
        this.passwordHash = passwordHash;
    }

    public Role getRole()
    {
        return role;
    }

    public void setRole(Role role)
    {
        this.role = role;
    }

    public Timestamp getRegistrationDate()
    {
        return registrationDate;
    }

    public void setRegistrationDate(Timestamp registrationDate)
    {
        this.registrationDate = registrationDate;
    }

    @Override
    public String toString()
    {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", passwordHash='" + passwordHash + '\'' +
                ", role=" + role +
                ", registrationDate=" + registrationDate +
                '}';
    }
}
