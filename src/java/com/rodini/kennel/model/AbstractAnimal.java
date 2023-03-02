package com.rodini.kennel.module;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractAnimal {
    private static int counter;
    private final int id = ++counter;

    private String name;
    private LocalDate birthDate;

    private List<Command> animalCommands;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public List<Command> getAnimalCommands() {
        return animalCommands;
    }

    public AbstractAnimal(String name, LocalDate birthDate) {
        this.name = name;
        this.birthDate = birthDate;
        animalCommands = new ArrayList<>();
    }

    public void learnSkill(Command command) {
        animalCommands.add(command);
    }
}
