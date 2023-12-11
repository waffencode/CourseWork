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

    public JsonStream(PrintWriter printWriter)
    {
        writer = printWriter;
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

//
//    public ArrayList<Thermostat> read()
//    {
//        ArrayList<Thermostat> list = new ArrayList<>();
//        try
//        {
//            list = objectMapper.readValue(new File(filePath), objectMapper.getTypeFactory().constructCollectionType(ArrayList.class, Thermostat.class));
//        } catch (IOException e)
//        {
//            e.printStackTrace();
//        }
//        return list;
//    }

}
