package com.course.server.domain;

import java.util.Date;
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
    private Date additionDate;
    private UUID decommissionedById;
    private Date decommissionDate;
}
