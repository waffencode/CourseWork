package com.course.client.service;

import com.course.client.domain.InventoryObject;

import java.io.File;
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

    }
}
