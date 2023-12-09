package com.course.server.database;

import com.course.server.domain.InventoryObject;
import com.course.server.domain.InventoryObjectsList;
import com.course.server.domain.User;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Database
{
    public void createUser(User user)
    {

    }

    public User getUser(UUID id)
    {
        return new User();
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
