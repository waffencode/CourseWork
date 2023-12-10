package com.course.server.database;

import com.course.server.domain.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class Database
{
    private static final String DB_URL = "jdbc:mysql://localhost/inventory";
    private static final String USER = "root";
    private static final String PASS = "+WHLm0WsP^j6hr7mfD58";


    public Database()
    {
        try
        {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void createUser(User user)
    {

    }

    public User getUser(UUID id)
    {
        User user = new User();

        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM user WHERE id = ?;"))
        {
            statement.setString(1, id.toString());
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next())
            {
                user.setId(UUID.fromString(resultSet.getString("id")));
                user.setLogin(resultSet.getString("login"));
                user.setPasswordHash(resultSet.getString("password_hash"));
                user.setRole(Role.values()[Integer.parseInt(resultSet.getString("role"))]);
                user.setRegistrationDate(Timestamp.valueOf(resultSet.getString("registration_date")));

                return user;
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        }

        return null;
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
        InventoryObject object = new InventoryObject();

        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM inventory_objects WHERE inventory_number = ?;"))
        {
            statement.setString(1, inventoryNumber);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next())
            {
                object.setInventoryNumber(resultSet.getString("inventory_number"));
                object.setName(resultSet.getString("name"));
                object.setInPlace(Objects.equals(resultSet.getString("is_in_place"), "1"));
                object.setCategory(Category.values()[Integer.parseInt(resultSet.getString("category"))]);
                object.setListId(UUID.fromString(resultSet.getString("list_id")));
                object.setDecommissioned(Objects.equals(resultSet.getString("is_decommissioned"), "1"));
                object.setAddedById(UUID.fromString(resultSet.getString("added_by_id")));
                object.setAdditionDate(Timestamp.valueOf(resultSet.getString("addition_date")));

                if (resultSet.getString("decommissioned_by_id") != null)
                {
                    object.setDecommissionedById(UUID.fromString(resultSet.getString("decommissioned_by_id")));
                    object.setDecommissionDate(Timestamp.valueOf(resultSet.getString("decommission_date")));
                }

                return object;
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        }

        return null;
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
        InventoryObjectsList list = new InventoryObjectsList();

        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM lists WHERE id = ?;"))
        {
            statement.setString(1, id.toString());
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next())
            {
                list.setId(UUID.fromString(resultSet.getString("id")));
                list.setName(resultSet.getString("name"));
                list.setArchived(Objects.equals(resultSet.getString("is_archived"), "1"));
                list.setCreatedById(UUID.fromString(resultSet.getString("created_by_id")));
                list.setCreationDate(Timestamp.valueOf(resultSet.getString("creation_date")));

                if (resultSet.getString("archived_by_id") != null)
                {
                    list.setArchivedBy(UUID.fromString(resultSet.getString("archived_by_id")));
                    list.setArchivationDate(Timestamp.valueOf(resultSet.getString("archivation_date")));
                }

                return list;
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        }

        return null;
    }

    public void updateList(InventoryObjectsList list)
    {

    }

    public void deleteList(UUID id)
    {

    }
}
