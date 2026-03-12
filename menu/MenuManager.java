package com.carmagazine.menu;

import com.carmagazine.thread.ThreadManager;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MenuManager {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    private Scanner scanner;
    private ExecutorService executorService;
    private ThreadManager threadManager;
    private MenuPrinter printer;
    private MenuHandler handler;
    private boolean isRunning;

    public MenuManager() {
        this.isRunning = true;
        this.scanner = new Scanner(System.in);
        this.executorService = Executors.newFixedThreadPool(3);
        this.threadManager = new ThreadManager(executorService);

        this.printer = new MenuPrinter(scanner, threadManager);
        this.handler = new MenuHandler(MenuState.MAIN, scanner, executorService, printer, threadManager);
    }

    public void start() {
        printer.printHeader();

        while (isRunning) {
            printCurrentMenu();
            String choice = scanner.nextLine();
            isRunning = handler.handleChoice(choice);
        }

        shutdown();
    }

    private void printCurrentMenu() {
        switch (handler.getCurrentState()) {
            case MAIN:
                printer.printMainMenu();
                break;
            case SOURCE:
                printer.printSourceMenu();
                break;
            case SEARCH:
                printer.printSearchMenu();
                break;
            case SORT_MENU:
                printer.printSortMenu();
                break;
        }
    }

    private void shutdown() {
        System.out.println("Завершение работы приложения...");
        threadManager.shutdown();
        scanner.close();
        System.out.println("Приложение завершено.");
    }
}
