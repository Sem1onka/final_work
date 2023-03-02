package com.rodini.kennel.view;

import com.rodini.kennel.model.AbstractAnimal;

public interface View {
    enum MainMenuCommand {
        ADD_ANIMAL,
        SHOW_ANIMAL,
        REMOVE_ANIMAL,
        EXIT
    }

    enum AddSkillMenuCommand {
        ADD_SKILL,
        EXIT
    }
    void showKennelRegistry();

    MainMenuCommand showMainMenuWithResult();

    boolean showAddAnimalDialog();
    int showRemoveAnimalDialog();
    void showDetailInfoAnimal();
    void showAnimalInfo(AbstractAnimal animal);
    AddSkillMenuCommand showAddSkillMenu(AbstractAnimal animal);
    boolean showAddSkillDialog(AbstractAnimal animal);
}
