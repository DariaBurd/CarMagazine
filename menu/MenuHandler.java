package com.carmagazine.menu;

import com.carmagazine.sort.*;
import com.carmagazine.thread.ThreadManager;

import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import static com.carmagazine.menu.MenuManager.*;

public class MenuHandler {
    private MenuState currentState;
    private Scanner scanner;
    private ExecutorService executorService;
    private MenuPrinter printer;
    private ThreadManager threadManager;

    public MenuHandler(MenuState initialState,
                       Scanner scanner, ExecutorService executorService,
                       MenuPrinter printer, ThreadManager threadManager) {
        this.currentState = initialState;
        this.scanner = scanner;
        this.executorService = executorService;
        this.printer = printer;
        this.threadManager = threadManager;
    }

    public MenuState getCurrentState() {
        return currentState;
    }

    public boolean handleChoice(String choice) {
        switch (currentState) {
            case MAIN:
                return handleMainMenuChoice(choice);
            case SOURCE:
                return handleSourceMenuChoice(choice);
            case FILE_LOAD:
                return handleFileLoadChoice();
            case MANUAL_INPUT:
                return handleManualInputChoice();
            case GENERATE:
                return handleGenerateChoice();
            case SEARCH:
                return handleSearchChoice(choice);
            case SORT_MENU:
                return handleSortChoice(choice);
            default:
                return true;
        }
    }

    private boolean handleMainMenuChoice(String choice) {
        switch (choice.trim()) { // <-- убираем пробелы через trim()
            case "1":
                currentState = MenuState.SOURCE;
                break;
            case "2":
                currentState = MenuState.SEARCH;
                break;
            case "3":
                currentState = MenuState.SORT_MENU;
                break;
            case "4":
                // Переключение многопоточности
                threadManager.toggle();
                break;
            case "0":
                return false;
            default:
                System.out.println(ANSI_RED + "Неверный выбор. Попробуйте снова." + ANSI_RESET);
        }
        return true;
    }

    private boolean handleSourceMenuChoice(String choice) {
        switch (choice.trim()) {
            case "1":
                currentState = MenuState.FILE_LOAD;
                return handleFileLoadChoice();
            case "2":
                currentState = MenuState.MANUAL_INPUT;
                return handleManualInputChoice();
            case "3":
                currentState = MenuState.GENERATE;
                return handleGenerateChoice();
            case "0":
                currentState = MenuState.MAIN;
                break;
            default:
                System.out.println(ANSI_RED + "Неверный выбор. Попробуйте снова." + ANSI_RESET);
        }
        return true;
    }

    private boolean handleSearchChoice(String choice) {
        switch (choice.trim()) {
            case "1":
                currentState = MenuState.SEARCH_COLOR;
                return handleSearchByColorChoice();
            case "2":
                currentState = MenuState.SEARCH_COMPLICATION;
                return handleSearchByComplictationChoice();
            case "3":
                currentState = MenuState.SEARCH_POWER;
                return handleSearchByPowerChoice();
            case "4":
                currentState = MenuState.SEARCH_YEAR;
                return handleSearchByYearChoice();
            case "0":
                currentState = MenuState.MAIN;
                break;
            default:
                System.out.println(ANSI_RED + "Неверный выбор. Попробуйте снова." + ANSI_RESET);
        }
        return true;
    }

    private boolean handleSortChoice(String choice) {
        switch (choice.trim()) {
            case "1":
                currentState = MenuState.SORT_COLOR;
                return handleSortByColorChoice();
            case "2":
                currentState = MenuState.SORT_COMPLICATION;
                return handleSortByComplicationChoice();
            case "3":
                currentState = MenuState.SORT_POWER;
                return handleSortByPowerChoice();
            case "4":
                currentState = MenuState.SORT_YEAR;
                return handleSortByYearChoice();
            case "5":
                currentState = MenuState.SORT_ANOTHER;
                return handleSortByAnotherChoice();
            case "0":
                currentState = MenuState.MAIN;
                break;
            default:
                System.out.println(ANSI_RED + "Неверный выбор. Попробуйте снова." + ANSI_RESET);
        }
        return true;
    }

    private boolean handleQuestChoice(Runnable searchAction) {
        searchAction.run();
        System.out.println("Нажмите Enter для возврата в меню...");
        scanner.nextLine();
        currentState = MenuState.MAIN;
        return true;
    }

    private boolean handleGenerateChoice() {
        return handleQuestChoice(() -> printer.printGenerateMenu());
    }

    private boolean handleManualInputChoice() {
        return handleQuestChoice(() -> printer.printManualMenu());
    }

    private boolean handleFileLoadChoice() {
        return handleQuestChoice(() -> printer.printFileLoadMenu());
    }



    private boolean handleSearchByColorChoice() {
        return handleQuestChoice(() -> printer.printSearchByColorMenu());
    }

    private boolean handleSearchByComplictationChoice() {
        return handleQuestChoice(() -> printer.printSearchByComplectationMenu());
    }

    private boolean handleSearchByPowerChoice() {
        return handleQuestChoice(() -> printer.printSearchByPowerMenu());
    }

    private boolean handleSearchByYearChoice() {
        return handleQuestChoice(() -> printer.printSearchByYearMenu());
    }



    private boolean handleSortByColorChoice() {
        return handleQuestChoice(() -> printer.printSortByMenu(new SortByColor(), "цвету"));
    }

    private boolean handleSortByComplicationChoice() {
        return handleQuestChoice(() -> printer.printSortByMenu(new SortByConfiguration(), "конфигурации"));
    }

    private boolean handleSortByPowerChoice() {
        return handleQuestChoice(() -> printer.printSortByMenu(new SortByPower(), "мощности"));
    }

    private boolean handleSortByYearChoice() {
        return handleQuestChoice(() -> printer.printSortByMenu(new SortByYear(), "году"));
    }

    private boolean handleSortByAnotherChoice() {
        return handleQuestChoice(() -> printer.printSortByMenu(new AdditionSortByYear(), "доп задание: сортировка чётные годы сортируются по возрастанию, нечётные остаются на местах"));
    }
}