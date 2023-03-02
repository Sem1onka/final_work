package com.rodini.kennel.storage;

import com.rodini.kennel.model.AbstractAnimal;

import java.util.List;

public interface Storage {
    List<AbstractAnimal> getAllAnimals();
    AbstractAnimal getAnimalById(int animalId);
    boolean addAnimal(AbstractAnimal animal);
    int removeAnimal(AbstractAnimal animal);
}
