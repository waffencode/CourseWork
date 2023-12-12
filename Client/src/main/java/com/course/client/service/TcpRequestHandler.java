package com.course.client.service;

import com.course.client.domain.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TcpRequestHandler
{
    public void createUser(User user)
    {

    }

    public User getUser(UUID id)
    {
        return new User();
    }

    public User getUserByLogin(String login)
    {
        return new User();
    }

    public List<User> getAllUsers()
    {
        return new ArrayList<User>();
    }

    public UUID tryAuthorizeLoginData(String login, String passwordHash)
    {
        return new UUID(0,0);
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

    public void deleteObject(String inventoryNumber)
    {

    }

    public void createList(InventoryObjectsList list)
    {

    }

    public InventoryObjectsList getList(UUID id)
    {
        return new InventoryObjectsList();
    }

    public List<InventoryObjectsList> getAllLists()
    {
        return new ArrayList<InventoryObjectsList>();
    }

    public void updateList(InventoryObjectsList list)
    {

    }

    public void archiveList(UUID listId, UUID userId)
    {

    }

    public void restoreList(UUID listId)
    {

    }

    public void deleteList(UUID id)
    {

    }
}
