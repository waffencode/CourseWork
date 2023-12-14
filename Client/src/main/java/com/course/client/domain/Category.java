package com.course.client.domain;

public enum Category {
    OTHER("Другое"),
    OFFICE_EQUIPMENT("Офисное оборудование"),
    RETAIL_DISPLAYS("Торговые стойки"),
    SECURITY_SYSTEMS("Системы безопасности"),
    IT_INFRASTRUCTURE("IT-инфраструктура"),
    FURNITURE_AND_FIXTURES("Мебель и оборудование"),
    AUDIOVISUAL_EQUIPMENT("Аудиовизуальное оборудование");

    private final String value;

    Category(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString()
    {
        return value;
    }
}
