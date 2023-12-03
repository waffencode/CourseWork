package com.course.server.domain;

import java.util.Date;
import java.util.UUID;

public class InventoryObjectsList
{
    private UUID id;
    private String name;
    private Boolean isArchived;
    private UUID createdById;
    private Date creationDate;
    private UUID archivedBy;
    private Date archivationDate;
}
