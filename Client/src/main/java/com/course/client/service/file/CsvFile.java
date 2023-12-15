package com.course.client.service.file;

import com.course.client.domain.InventoryObject;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CsvFile
{
    File file;

    public CsvFile(File file)
    {
        this.file = file;
    }

    public void writeList(List<InventoryObject> list)
    {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file.toString())))
        {
            writer.write("InventoryNumber;Name;IsInPlace;Category;ListId;IsDecommissioned;AddedById;AdditionDate;DecommissionedById;DecommissionDate\n");

            for (InventoryObject inventoryObject : list)
            {
                writer.write(String.format("%s;%s;%s;%s;%s;%s;%s;%s;%s;%s\n",
                        inventoryObject.getInventoryNumber(),
                        inventoryObject.getName(),
                        inventoryObject.getInPlace(),
                        inventoryObject.getCategory(),
                        inventoryObject.getListId(),
                        inventoryObject.getDecommissioned(),
                        inventoryObject.getAddedById(),
                        inventoryObject.getAdditionDate(),
                        inventoryObject.getDecommissionedById(),
                        inventoryObject.getDecommissionDate()));
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
