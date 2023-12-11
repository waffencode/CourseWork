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
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement statement = connection.prepareStatement("INSERT INTO user VALUES (?, ?, ?, ?, ?);"))
        {
            statement.setString(1, user.getId().toString());
            statement.setString(2, user.getLogin());
            statement.setString(3, user.getPasswordHash());
            statement.setInt(4, user.getRole().ordinal());
            statement.setTimestamp(5, user.getRegistrationDate());

            statement.execute();
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
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

    public List<User> getAllUsers()
    {
        List<User> users = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM user;"))
        {
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next())
            {
                User user = new User();

                user.setId(UUID.fromString(resultSet.getString("id")));
                user.setLogin(resultSet.getString("login"));
                user.setPasswordHash(resultSet.getString("password_hash"));
                user.setRole(Role.values()[Integer.parseInt(resultSet.getString("role"))]);
                user.setRegistrationDate(Timestamp.valueOf(resultSet.getString("registration_date")));

                users.add(user);
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        }

        return users;
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
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement statement = connection.prepareStatement("DELETE FROM user WHERE id = ?;"))
        {
            statement.setString(1, id.toString());

            statement.execute();
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void createObject(InventoryObject object)
    {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement statement = connection.prepareStatement("INSERT INTO inventory_objects VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);"))
        {
            statement.setString(1, object.getInventoryNumber());
            statement.setString(2, object.getName());
            statement.setBoolean(3, object.getInPlace());
            statement.setInt(4, object.getCategory().ordinal());
            statement.setString(5, object.getListId().toString());
            statement.setBoolean(6, object.getDecommissioned());
            statement.setString(7, object.getAddedById().toString());
            statement.setTimestamp(8, object.getAdditionDate());

            if (object.getDecommissionedById() != null)
            {
                statement.setString(9, object.getDecommissionedById().toString());
                statement.setTimestamp(10, object.getDecommissionDate());
            }
            else
            {
                statement.setNull(9, JDBCType.VARCHAR.ordinal());
                statement.setNull(10, JDBCType.TIMESTAMP.ordinal());
            }

            statement.execute();
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
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
        List<InventoryObject> objects = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM inventory_objects WHERE list_id = ?;"))
        {
            statement.setString(1, listId.toString());
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next())
            {
                InventoryObject object = new InventoryObject();

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

                objects.add(object);
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        }

        return objects;
    }

    public void updateObject(InventoryObject object)
    {

    }

    public void deleteObject(String inventoryNumber)
    {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement statement = connection.prepareStatement("DELETE FROM inventory_objects WHERE inventory_number = ?;"))
        {
            statement.setString(1, inventoryNumber);

            statement.execute();
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void createList(InventoryObjectsList list)
    {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement statement = connection.prepareStatement("INSERT INTO lists VALUES (?, ?, ?, ?, ?, ?, ?);"))
        {
            statement.setString(1, list.getId().toString());
            statement.setString(2, list.getName());
            statement.setBoolean(3, list.getArchived());
            statement.setString(4, list.getCreatedById().toString());
            statement.setTimestamp(5, list.getCreationDate());

            if (list.getArchivedBy() != null)
            {
                statement.setString(6, list.getArchivedBy().toString());
                statement.setTimestamp(7, list.getArchivationDate());
            }
            else
            {
                statement.setNull(6, JDBCType.VARCHAR.ordinal());
                statement.setNull(7, JDBCType.TIMESTAMP.ordinal());
            }

            statement.execute();
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
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

    public List<InventoryObjectsList> getAllLists()
    {
        List<InventoryObjectsList> lists = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM lists;"))
        {
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next())
            {
                InventoryObjectsList list = new InventoryObjectsList();

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

                lists.add(list);
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        }

        return lists;
    }

    public void updateList(InventoryObjectsList list)
    {

    }

    public void deleteList(UUID id)
    {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement statement = connection.prepareStatement("DELETE FROM lists WHERE id = ?;"))
        {
            statement.setString(1, id.toString());

            statement.execute();
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}
