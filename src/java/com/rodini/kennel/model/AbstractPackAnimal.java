package com.rodini.kennel.model;

import java.time.LocalDate;


/**
 * Описывает абстрактное вьючное животное
 */
public abstract class AbstractPackAnimal extends AbstractAnimal {
    public static final int DEFAULT_LOAD_CAPACITY = 100;

    /**
     * Грузоподъемность животного, кг
     */
    private  int loadCapacity;


    public AbstractPackAnimal(String name, LocalDate birthDate) {
        super(name, birthDate);
        this.loadCapacity = DEFAULT_LOAD_CAPACITY;
    }

    public int getLoadCapacity() {
        return loadCapacity;
    }

    public void setLoadCapacity(int loadCapacity) {
        this.loadCapacity = loadCapacity;
    }
}
