package com.rodini.kennel;

import com.rodini.kennel.controller.KennelAccounting;
import com.rodini.kennel.storage.KennelStorage;
import com.rodini.kennel.view.ConsoleView;
import com.rodini.kennel.view.View;

public class App {
    public static void run() {
        KennelAccounting kennelAccounting = new KennelAccounting(new KennelStorage());
        View view = new ConsoleView(kennelAccounting);

        while (true) {
            view.showKennelRegistry();
            View.MainMenuCommand code = view.showMainMenuWithResult();
            switch (code) {
                case ADD_ANIMAL -> {
                    boolean result = view.showAddAnimalDialog();
                    String resMessage = result ? "Animal added" : "Failed to add animal";
                    System.out.println(resMessage);
                }
                case SHOW_SKILLS -> {
                    view.showDetailInfoAnimal();
                }
                case REMOVE_ANIMAL -> {
                    int removeId = view.showRemoveAnimalDialog();
                    String resMessage = removeId > -1  ? "Remove animal " + removeId : "Failed removing animal";
                    System.out.println(resMessage);
                }
                case EXIT -> {
                    System.out.println("See you... )");
                    return;
                }
            }
        }
    }
}
