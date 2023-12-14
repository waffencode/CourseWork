package com.course.client.domain;

import java.sql.Timestamp;
import java.util.UUID;

public class InventoryObjectsList
{
    private UUID id = UUID.randomUUID();
    private String name;
    private Boolean isArchived = false;
    private UUID createdById;
    private Timestamp creationDate = new Timestamp(System.currentTimeMillis());
    private UUID archivedBy;
    private Timestamp archivationDate;

    public UUID getId()
    {
        return id;
    }

    public void setId(UUID id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public Boolean getArchived()
    {
        return isArchived;
    }

    public void setArchived(Boolean archived)
    {
        isArchived = archived;
    }

    public UUID getCreatedById()
    {
        return createdById;
    }

    public void setCreatedById(UUID createdById)
    {
        this.createdById = createdById;
    }

    public Timestamp getCreationDate()
    {
        return creationDate;
    }

    public void setCreationDate(Timestamp creationDate)
    {
        this.creationDate = creationDate;
    }

    public UUID getArchivedBy()
    {
        return archivedBy;
    }

    public void setArchivedBy(UUID archivedBy)
    {
        this.archivedBy = archivedBy;
    }

    public Timestamp getArchivationDate()
    {
        return archivationDate;
    }

    public void setArchivationDate(Timestamp archivationDate)
    {
        this.archivationDate = archivationDate;
    }

    @Override
    public String toString()
    {
        return name + (isArchived ? " (Архивирован)" : "") + " (ID: " + id.toString() + ")";
    }
}
