package com.rodini.kennel.model.implement;

import com.rodini.kennel.model.AbstractPet;
import com.rodini.kennel.model.AnimalGenius;

import java.time.LocalDate;

public class Dog extends AbstractPet {
    public Dog(String name, LocalDate birthDate) {
        super(name, birthDate);
        setAnimalGenius(AnimalGenius.DOG);
    }
}
