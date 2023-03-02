package com.rodini.kennel.model;

/**
 * Перечисление, описывающее Род животного
 */
public enum AnimalGenius {
    DOG ("Собака"),
    CAT ("Кошка"),
    HAMSTER ("Хомяк"),
    HORSE ("Лошадь"),
    CAMEL ("Верблюд"),
    DONKEY ("Осёл");

    private String title;
    AnimalGenius(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
