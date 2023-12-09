package com.course.server.database;

import com.course.server.domain.InventoryObject;
import com.course.server.domain.InventoryObjectsList;
import com.course.server.domain.Role;
import com.course.server.domain.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Database
{
    private static final String DB_URL = "jdbc:mysql://localhost/inventory";
    private static final String USER = "root";
    private static final String PASS = "+WHLm0WsP^j6hr7mfD58";

    public void createUser(User user)
    {

    }

    public User getUser(UUID id)
    {
        User user = new User();
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM users WHERE id = ?;"))
        {
            pstmt.setString(1, id.toString());
            ResultSet rs = pstmt.executeQuery();

            if (rs.next())
            {
                user.setId(UUID.fromString(rs.getString("id")));
                user.setLogin(rs.getString("login"));
                user.setPasswordHash(rs.getString("password_hash"));
                user.setRole(Role.valueOf(rs.getString("role")));
                user.setRegistrationDate(Date.valueOf(rs.getString("registration_date")));
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        }

        return user;
    }

    public List<User> getAllUsers(UUID id)
    {
        return new ArrayList<User>();
    }

    public boolean isValidLoginData(String login, String passwordHash)
    {
        return false;
    }

    public void updateUser(User user)
    {

    }

    public void deleteUser(UUID id)
    {

    }

    public void createObject(InventoryObject object)
    {

    }

    public InventoryObject getObject(String inventoryNumber)
    {
        return new InventoryObject();
    }

    public List<InventoryObject> getAllObjectsFromList(UUID listId)
    {
        return new ArrayList<InventoryObject>();
    }

    public void updateObject(InventoryObject object)
    {

    }

    public void deleteObject(UUID id)
    {

    }

    public void createList(InventoryObjectsList list)
    {

    }

    public InventoryObjectsList getList(UUID id)
    {
        return new InventoryObjectsList();
    }

    public void updateList(InventoryObjectsList list)
    {

    }

    public void deleteList(UUID id)
    {

    }
}
