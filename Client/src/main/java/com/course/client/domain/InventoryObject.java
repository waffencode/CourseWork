package com.course.client.domain;

import java.sql.Timestamp;
import java.util.UUID;

public class InventoryObject
{
    private String inventoryNumber;
    private String name;
    private Boolean isInPlace;
    private Category category;
    private UUID listId;
    private Boolean isDecommissioned;
    private UUID addedById;
    private Timestamp additionDate;
    private UUID decommissionedById;
    private Timestamp decommissionDate;

    public String getInventoryNumber()
    {
        return inventoryNumber;
    }

    public void setInventoryNumber(String inventoryNumber)
    {
        this.inventoryNumber = inventoryNumber;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public Boolean getInPlace()
    {
        return isInPlace;
    }

    public void setInPlace(Boolean inPlace)
    {
        isInPlace = inPlace;
    }

    public Category getCategory()
    {
        return category;
    }

    public void setCategory(Category category)
    {
        this.category = category;
    }

    public UUID getListId()
    {
        return listId;
    }

    public void setListId(UUID listId)
    {
        this.listId = listId;
    }

    public Boolean getDecommissioned()
    {
        return isDecommissioned;
    }

    public void setDecommissioned(Boolean decommissioned)
    {
        isDecommissioned = decommissioned;
    }

    public UUID getAddedById()
    {
        return addedById;
    }

    public void setAddedById(UUID addedById)
    {
        this.addedById = addedById;
    }

    public Timestamp getAdditionDate()
    {
        return additionDate;
    }

    public void setAdditionDate(Timestamp additionDate)
    {
        this.additionDate = additionDate;
    }

    public UUID getDecommissionedById()
    {
        return decommissionedById;
    }

    public void setDecommissionedById(UUID decommissionedById)
    {
        this.decommissionedById = decommissionedById;
    }

    public Timestamp getDecommissionDate()
    {
        return decommissionDate;
    }

    public void setDecommissionDate(Timestamp decommissionDate)
    {
        this.decommissionDate = decommissionDate;
    }

    @Override
    public String toString()
    {
        return "InventoryObject{" +
                "inventoryNumber='" + inventoryNumber + '\'' +
                ", name='" + name + '\'' +
                ", isInPlace=" + isInPlace +
                ", category=" + category +
                ", listId=" + listId +
                ", isDecommissioned=" + isDecommissioned +
                ", addedById=" + addedById +
                ", additionDate=" + additionDate +
                ", decommissionedById=" + decommissionedById +
                ", decommissionDate=" + decommissionDate +
                '}';
    }
}
