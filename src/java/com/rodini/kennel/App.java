package com.rodini.kennel;

import com.rodini.kennel.module.AbstractPet;
import com.rodini.kennel.module.Cat;
import com.rodini.kennel.module.Command;

import java.time.LocalDate;

public class App {
    public static void main(String[] args) {
        AbstractPet pet_1 = new Cat("qqq", LocalDate.now());
        pet_1.learnSkill(new Command("Mur mur"));
        pet_1.learnSkill(new Command("Sleep"));
        System.out.println(pet_1.getAnimalCommands());
        System.out.println(pet_1.getId()+ " " + pet_1.getName());
        AbstractPet pet_2 = new Cat("aaa", LocalDate.now());
        System.out.println(pet_2.getId()+ " " + pet_2.getName());
        AbstractPet pet_3 = new Cat("ddd", LocalDate.now());
        System.out.println(pet_3.getId() + " " + pet_3.getName());
    }
}
