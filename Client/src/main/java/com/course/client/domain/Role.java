package com.course.client.domain;

public enum Role {
    USER("Пользователь"),
    INVENTORY_OFFICER("Ответственный за инвентаризацию"),
    ADMINISTRATOR("Администратор");

    private final String value;

    Role(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }

    @Override
    public String toString()
    {
        return value;
    }
}
