package com.course.client.service;

import com.course.client.domain.*;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.util.List;

public class JsonStream
{
    private final ObjectMapper objectMapper = new ObjectMapper();

    public String writeAsString(User user)
    {
        try
        {
            return objectMapper.writerFor(new TypeReference<User>()
            {
            }).writeValueAsString(user);
        } catch (IOException e)
        {
            e.printStackTrace();
        }

        return null;
    }

    public String writeAsString(InventoryObjectsList list)
    {
        try
        {
            return objectMapper.writerFor(new TypeReference<InventoryObjectsList>()
            {
            }).writeValueAsString(list);
        } catch (IOException e)
        {
            e.printStackTrace();
        }

        return null;
    }

    public String writeAsString(InventoryObject object)
    {
        try
        {
            return objectMapper.writerFor(new TypeReference<InventoryObject>()
            {
            }).writeValueAsString(object);
        } catch (IOException e)
        {
            e.printStackTrace();
        }

        return null;
    }

    public String writeAsString(List<InventoryObject> objects)
    {
        try
        {
            return objectMapper.writerFor(new TypeReference<List<InventoryObject>>()
            {
            }).writeValueAsString(objects);
        } catch (IOException e)
        {
            e.printStackTrace();
        }

        return null;
    }


    public User readUser(String json)
    {
        User user = new User();

        try
        {
            user = objectMapper.readValue(json, objectMapper.getTypeFactory().constructType(User.class));
        } catch (IOException e)
        {
            e.printStackTrace();
        }

        return user;
    }

    public InventoryObjectsList readList(String json)
    {
        InventoryObjectsList list = new InventoryObjectsList();

        try
        {
            list = objectMapper.readValue(json, objectMapper.getTypeFactory().constructType(InventoryObjectsList.class));
        } catch (IOException e)
        {
            e.printStackTrace();
        }

        return list;
    }

    public InventoryObject readObject(String json)
    {
        InventoryObject object = new InventoryObject();

        try
        {
            object = objectMapper.readValue(json, objectMapper.getTypeFactory().constructType(InventoryObject.class));
        } catch (IOException e)
        {
            e.printStackTrace();
        }

        return object;
    }
}
