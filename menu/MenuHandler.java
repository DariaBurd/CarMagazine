package com.carmagazine.menu;

import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import static com.carmagazine.menu.MenuManager.*;

public class MenuHandler {
    private MenuState currentState;
    private Scanner scanner;
    private ExecutorService executorService;
    private MenuPrinter printer;

    public MenuHandler(MenuState initialState,
                       Scanner scanner, ExecutorService executorService,
                       MenuPrinter printer) {
        this.currentState = initialState;
        this.scanner = scanner;
        this.executorService = executorService;
        this.printer = printer;
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
            case POWER:
                return handlePowerMenuChoice(choice);
            case YEAR:
                return handleYearMenuChoice(choice);
            case COLOR:
                return handleColorMenuChoice(choice);
            case COMPLECTATION:
                return handleComplectationMenuChoice(choice);
            default:
                return true;
        }
    }

    private boolean handleMainMenuChoice(String choice) {
        switch (choice) {
            case "1":
                currentState = MenuState.SOURCE;
                break;
            case "2":
                currentState = MenuState.MAIN;
                printer.printReroll();
                break;
            case "3":
                currentState = MenuState.MAIN;
                printer.printLog();
                break;
            case "4":
                currentState = MenuState.SEARCH;
                break;
            case "5":
                currentState = MenuState.POWER;
                break;
            case "6":
                currentState = MenuState.YEAR;
                break;
            case "7":
                currentState = MenuState.COLOR;
                break;
            case "8":
                currentState = MenuState.COMPLECTATION;
                break;
            case "0":
                return false; // Выход из программы
            default:
                System.out.println(ANSI_RED + "Неверный выбор. Попробуйте снова." + ANSI_RESET);
        }
        return true;
    }

    private boolean handleSourceMenuChoice(String choice) {
        switch (choice) {
            case "1":
                currentState = MenuState.FILE_LOAD;
                printer.printFileLoadMenu();
                break;
            case "2":
                currentState = MenuState.MANUAL_INPUT;
                printer.printManualMenu();
                break;
            case "3":
                currentState = MenuState.GENERATE;
                printer.printGenerateMenu();
                break;
            case "0":
                currentState = MenuState.MAIN;
                break;
            default:
                System.out.println(ANSI_RED + "Неверный выбор. Попробуйте снова." + ANSI_RESET);
        }
        return true;
    }

    private boolean handlePowerMenuChoice(String choice) {
        return true;
    }

    private boolean handleYearMenuChoice(String choice) {
        return true;
    }

    private boolean handleColorMenuChoice(String choice) {
        return true;
    }

    private boolean handleComplectationMenuChoice(String choice) {
        return true;
    }
}
