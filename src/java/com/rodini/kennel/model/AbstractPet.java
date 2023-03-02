package com.rodini.kennel.module;

import java.time.LocalDate;

public abstract class AbstractPet extends AbstractAnimal {
    public AbstractPet(String name, LocalDate birthDate) {
        super(name, birthDate);
    }
}
