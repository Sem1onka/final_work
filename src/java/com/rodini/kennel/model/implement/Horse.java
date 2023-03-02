package com.rodini.kennel.model.implement;

import com.rodini.kennel.model.AbstractPackAnimal;
import com.rodini.kennel.model.AnimalGenius;

import java.time.LocalDate;

public class Horse extends AbstractPackAnimal {
    public Horse(String name, LocalDate birthDate) {
        super(name, birthDate);
        setAnimalGenius(AnimalGenius.HORSE);
    }
}
