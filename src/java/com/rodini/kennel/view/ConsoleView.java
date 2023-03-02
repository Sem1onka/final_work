package com.rodini.kennel.view;

import com.rodini.kennel.controller.KennelAccounting;
import com.rodini.kennel.model.AbstractAnimal;
import com.rodini.kennel.model.AnimalGenius;
import com.rodini.kennel.model.Skill;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class ConsoleView implements View {
    public static final int SIZE_LINE = 80;
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ROW_FORMAT = "%-6d%-16s%-20s%-20d%-20s";
    public static final String COLUMN_HEADER_FORMAT = "%-6s%-16s%-20s%-20s%-20s";
    public static final String RED_COLOR = "\u001B[31m";
    private final KennelAccounting kennelAccounting;
    private Scanner scanner;

    public ConsoleView(KennelAccounting kennelAccounting) {
        this.kennelAccounting = kennelAccounting;
        scanner = new Scanner(System.in);
    }

    @Override
    public void showKennelRegistry() {
        clearConsole();
        printLineWithSymbol("=", SIZE_LINE);
        printCaption("Питомник животных", " ");
        printLineWithSymbol("=", SIZE_LINE);
        printRegistryHeader();
        printLineWithSymbol("-", SIZE_LINE);

        List<AbstractAnimal> animals = kennelAccounting.getAnimals();
        for (int i = 0; i < animals.size(); i++) {
            var item = animals.get(i);
            String row = String.format(ROW_FORMAT, i + 1, item.getName(), item.getBurthDateAsString(), item.getAge(),
                    item.getAnimalGenius().getTitle());
            printLine(row);
        }
        printLineWithSymbol("-", SIZE_LINE);
    }

    @Override
    public MainMenuCommand showMainMenuWithResult() {
        String menu = String.format(
                "%d-[Добавить]\t%d-[Показать]\t%d-[Удалить]\t%d-[Выйти]\n",
                MainMenuCommand.ADD_ANIMAL.ordinal(), MainMenuCommand.SHOW_ANIMAL.ordinal(),
                MainMenuCommand.REMOVE_ANIMAL.ordinal(), MainMenuCommand.EXIT.ordinal());
        printLineWithSymbol("=", SIZE_LINE);
        System.out.println("Доступные действия:");
        System.out.print(menu);
        while (true) {
            try {
                System.out.printf("%s (%d - %d): ", "Выберите действие: ", MainMenuCommand.ADD_ANIMAL.ordinal(),
                        MainMenuCommand.EXIT.ordinal());
                scanner = new Scanner(System.in);
                return MainMenuCommand.values()[scanner.nextInt()];
            } catch (ArrayIndexOutOfBoundsException | InputMismatchException e) {
                printColorLine("Некоректное действие!", RED_COLOR);
            }
        }
    }

    @Override
    public boolean showAddAnimalDialog() {
        String infoMessage = "Введите параметры животного в виде строки: \"имя день_рождения род_животного\"\n" +
                "день_рождения имеет вид: dd-mm-yyyy (12-03-2022): \n" +
                "род_животного может принимать значения: " + Arrays.asList(AnimalGenius.values());
        System.out.println(infoMessage);
        while (true) {
            try {
                System.out.print("Ввод: ");
                scanner = new Scanner(System.in);
                String[] animalData = scanner.nextLine().split(" ");
                if (animalData.length < 3) {
                    throw new IllegalArgumentException("Недостаточное количество данных");
                }
                if (animalData.length > 3) {
                    throw new IllegalArgumentException("Слишком много данных");
                }
                String animalName = animalData[0];
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                LocalDate birthDay = LocalDate.parse(animalData[1], formatter);
                AnimalGenius genius = AnimalGenius.valueOf(animalData[2].toUpperCase());

                return kennelAccounting.createAnimal(animalName, birthDay, genius);
            } catch (DateTimeParseException e) {
                System.out.println("Неправильный формат даты рождения");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }

        }
    }

    @Override
    public void showDetailInfoAnimal() {
        String infoMessage = String.format("Введите номер животного (1 - %d)", kennelAccounting.getAnimals().size());
        System.out.println(infoMessage);
        while (true) {
            try {
                scanner = new Scanner(System.in);
                int animalNumber = scanner.nextInt();
                AbstractAnimal animal = kennelAccounting.getAnimals().get(animalNumber - 1);
                showAnimalInfo(animal);

                break;
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Запись с таким номером отсутствует");
            }
        }
    }

    @Override
    public void showAnimalInfo(AbstractAnimal animal) {
        printAnimalInfo(animal);
        while (true) {
            AddSkillMenuCommand code = showAddSkillMenu(animal);
            switch (code) {
                case ADD_SKILL -> {
                    boolean result = showAddSkillDialog(animal);
                    String resMessage = result ? "Skill is learned" : "Failed to learn skill";
                    System.out.println(resMessage);
                    if (!result) return;
                }
                case EXIT -> {
                    return;
                }
            }
        }

    }

    @Override
    public AddSkillMenuCommand showAddSkillMenu(AbstractAnimal animal) {
        String menu = String.format(
                "%d-[Обучить команде]\t%d-[Выйти]\n",
                AddSkillMenuCommand.ADD_SKILL.ordinal(), AddSkillMenuCommand.EXIT.ordinal());
        printLineWithSymbol("=", SIZE_LINE);
        System.out.println("Доступные действия:");
        System.out.print(menu);
        while (true) {
            try {
                System.out.printf("%s (%d - %d): ", "Выберите действие: ", AddSkillMenuCommand.ADD_SKILL.ordinal(),
                        AddSkillMenuCommand.EXIT.ordinal());
                scanner = new Scanner(System.in);
                return AddSkillMenuCommand.values()[scanner.nextInt()];
            } catch (ArrayIndexOutOfBoundsException | InputMismatchException e) {
                printColorLine("Некоректное действие!", RED_COLOR);
            }
        }
    }

    @Override
    public boolean showAddSkillDialog(AbstractAnimal animal) {
        System.out.println("Введите данные в виде \"команда <описание>\"");
        scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        if (input.isBlank()) return false;
        String[] skillData = input.split(" ");
        if (skillData.length == 1) {
            animal.learnSkill(new Skill(skillData[0]));
        } else if (skillData.length == 2) {
            animal.learnSkill(new Skill(skillData[0], skillData[1]));
        } else {
            System.out.println("Слишком много параметров");
            return  false;
        }
        return true;
    }

    @Override
    public int showRemoveAnimalDialog() {
        String infoMessage = String.format("Введите номер животного (1 - %d)", kennelAccounting.getAnimals().size());
        System.out.println(infoMessage);
        while (true) {
            try {
                scanner = new Scanner(System.in);
                int animalNumber = scanner.nextInt();
                AbstractAnimal animal = kennelAccounting.getAnimals().get(animalNumber - 1);

                return kennelAccounting.removeAnimal(animal);
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Запись с таким номером отсутствует");
            }
        }
    }

    private void clearConsole() {
        System.out.print("\033[H\033[J");
    }

    private void printLineWithSymbol(String symbol, int sizeLine) {
        System.out.println(symbol.repeat(sizeLine));
    }

    private void printCaption(String caption, String padSymb) {
        int spaceSize = (SIZE_LINE - caption.length()) / 2;
        String captionLine = padSymb.repeat(spaceSize) + caption + padSymb.repeat(spaceSize);
        System.out.println(captionLine);
    }

    private void printRegistryHeader() {
        String header = String.format(COLUMN_HEADER_FORMAT, "№", "Имя", "Дата рождения", "Возраст(в месяцах)",
                "Род животного");
        System.out.println(header);
    }

    private void printLine(String row) {
        System.out.printf("%s\n", row);
    }

    private void printColorLine(String row, String displayColor) {
        System.out.printf("%s%s%s\n", displayColor, row, ANSI_RESET);
    }

    private void printAnimalInfo(AbstractAnimal animal) {
        clearConsole();
        printCaption("Detail info", "~");
        System.out.printf("Род животного: %s\n", animal.getAnimalGenius().getTitle());
        System.out.printf("Имя: %s\n", animal.getName());
        System.out.printf("Дата рождения: %s\n", animal.getBurthDateAsString());
        System.out.printf("Возраст (в месяцах): %d\n", animal.getAge());
        System.out.printf("Умения: %s\n", animal.getAnimalSkills());
        printLineWithSymbol("~", SIZE_LINE);
    }
}
