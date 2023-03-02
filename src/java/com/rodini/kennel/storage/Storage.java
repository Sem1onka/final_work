package com.rodini.kennel.storage;

import com.rodini.kennel.model.AbstractAnimal;

import java.util.List;

public interface Storage {
    /**
     * Возвращает список животных из хранилища
     * @return Список животных
     */
    List<AbstractAnimal> getAllAnimals();

    /**
     * Возвращает животное по его id из хранилища
     * @return Животное или null если животное с таким id не найдено
     */
    AbstractAnimal getAnimalById(int animalId);

    /**
     * Добавляет животное в хранилище
     * @return true в случае успеха или false - в противном
     */
    boolean addAnimal(AbstractAnimal animal);

    /**
     * Удаляет животное из хранилища
     * @return id удаленного животного, или -1 удаляемого животного нет в хранилище
     */
    int removeAnimal(AbstractAnimal animal);
}
