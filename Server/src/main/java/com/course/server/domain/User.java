package com.course.server.domain;

import java.util.Date;
import java.util.UUID;

public class User
{
    private UUID id;
    private String login;
    private String passwordHash;
    private Role role;
    private Date registrationDate;
}
