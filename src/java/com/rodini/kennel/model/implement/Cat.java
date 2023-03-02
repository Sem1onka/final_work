package com.rodini.kennel.model.implement;

import com.rodini.kennel.model.AbstractPet;
import com.rodini.kennel.model.AnimalGenius;

import java.time.LocalDate;

public class Cat extends AbstractPet {
    public Cat(String name, LocalDate birthDate) {
        super(name, birthDate);
        setAnimalGenius(AnimalGenius.CAT);
    }
}
