package com.rodini.kennel.model.implement;

import com.rodini.kennel.model.AbstractPackAnimal;
import com.rodini.kennel.model.AnimalGenius;

import java.time.LocalDate;

public class Camel extends AbstractPackAnimal {
    public Camel(String name, LocalDate birthDate) {
        super(name, birthDate);
        setAnimalGenius(AnimalGenius.CAMEL);
    }
}
