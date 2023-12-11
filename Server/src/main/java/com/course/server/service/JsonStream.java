package com.course.server.service;

import com.course.server.domain.*;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.util.List;

public class JsonStream
{
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final PrintWriter writer;
    private final BufferedReader reader;

    public JsonStream(PrintWriter printWriter)
    {
        writer = printWriter;
        reader = null;
    }

    public JsonStream(BufferedReader reader)
    {
        this.reader = reader;
        writer = null;
    }

    public void write(User user)
    {
        try
        {
            objectMapper.writerFor(new TypeReference<User>()
            {
            }).writeValue(writer, user);
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void write(InventoryObjectsList list)
    {
        try
        {
            objectMapper.writerFor(new TypeReference<InventoryObjectsList>()
            {
            }).writeValue(writer, list);
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void write(InventoryObject object)
    {
        try
        {
            objectMapper.writerFor(new TypeReference<InventoryObject>()
            {
            }).writeValue(writer, object);
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void write(List<InventoryObject> objects)
    {
        try
        {
            objectMapper.writerFor(new TypeReference<List<InventoryObject>>()
            {
            }).writeValue(writer, objects);
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }


    public User readUser()
    {
        User user = new User();

        try
        {
            user = objectMapper.readValue(reader, objectMapper.getTypeFactory().constructType(User.class));
        } catch (IOException e)
        {
            e.printStackTrace();
        }

        return user;
    }

    public InventoryObjectsList readList()
    {
        InventoryObjectsList list = new InventoryObjectsList();

        try
        {
            list = objectMapper.readValue(reader, objectMapper.getTypeFactory().constructType(InventoryObjectsList.class));
        } catch (IOException e)
        {
            e.printStackTrace();
        }

        return list;
    }

    public InventoryObject readObject()
    {
        InventoryObject object = new InventoryObject();

        try
        {
            object = objectMapper.readValue(reader, objectMapper.getTypeFactory().constructType(InventoryObject.class));
        } catch (IOException e)
        {
            e.printStackTrace();
        }

        return object;
    }
}
