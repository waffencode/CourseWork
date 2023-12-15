package com.course.client.service.file;

import com.course.client.domain.InventoryObject;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonFile
{
    private final ObjectMapper objectMapper = new ObjectMapper();
    File file;

    public JsonFile(File file)
    {
        this.file = file;
    }

    public void writeList(List<InventoryObject> list)
    {
        try
        {
            objectMapper.writerWithType(new TypeReference<ArrayList<InventoryObject>>()
            {
            }).writeValue(file, list);
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public List<InventoryObject> readList()
    {
        List<InventoryObject> list = new ArrayList<>();

        try
        {
            list = objectMapper.readValue(file, objectMapper.getTypeFactory().constructCollectionType(ArrayList.class, InventoryObject.class));
        } catch (IOException e)
        {
            e.printStackTrace();
        }

        return list;
    }
}
