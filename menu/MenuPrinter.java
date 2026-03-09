package com.carmagazine.menu;

import static com.carmagazine.menu.MenuManager.*;

public class MenuPrinter {
    public MenuPrinter() {

    }

    public void printMainMenu() {
        printTableHeader();
        printMenuItem(1, "Исходник", "Выбор способа загрузки авто");
        printMenuItem(2, "Перепроверка", "Проверить заново sortedCar.txt");
        printMenuItem(3, "Загрузить лог", "Записать последние события в log.txt");
        printMenuItem(4, "Поиск", "Найти авто из исходника");
        printMenuItem(5, "Мощность авто", "Текущий: 111");
        printMenuItem(6, "Год авто", "Текущий: ");
        printMenuItem(7, "Цвет авто", "Текущий: ");
        printMenuItem(8, "Комплектация авто", "Текущий: ");
        printMenuItem(0, "Выход", "Завершить программу");
        printTableFooter();
        printPrompt("[0/1/2/3/4/5/6/7/8]");
    }

    public void printSourceMenu() {
        printTableHeader();
        printMenuItem(1, "Файл", "Загрузить авто из .txt");
        printMenuItem(2, "Ручной ввод", "Описать авто вручную");
        printMenuItem(3, "Генерация", "Создание рандомных авто");
        printMenuItem(0, "Назад", "Вернуться в главное меню");
        printTableFooter();
        printPrompt("[0/1/2/3]");
    }

    public void printPowerMenu() {

    }

    public void printYearMenu() {

    }

    public void printColorMenu() {

    }

    public void printComplectationMenu() {

    }

    public void printFileLoadMenu() {
        System.out.print("Введите путь к файлу .txt: ");
        //FIXME метод загрузки файла
    }

    public void printManualMenu() {
        //FIXME метод ручного ввода
    }

    public void printGenerateMenu() {
        //FIXME метод генерации
    }

    public void printReroll() {
        //FIXME метод перепроверки
    }

    public void printLog() {
        //FIXME метод записи лога потоков
    }

    public void printHeader() {
        System.out.println(ANSI_BLUE + "                          ╔═══════════════════" + ANSI_RESET + ANSI_PURPLE +
                " CarMagazine v1.0.0 " + ANSI_RESET + ANSI_BLUE +
                "═══════════════════╗" + ANSI_RESET);
        System.out.println(ANSI_BLUE + "                          ║                                                          ║" + ANSI_RESET);
        System.out.println(ANSI_BLUE + "                          ║  ____ ____ ____    _  _ ____ ____ ____ ___  _ _  _ ____  ║" + ANSI_RESET);
        System.out.println(ANSI_BLUE + "                          ║  |    |__| |__/    |\\/| |__| | __ |__|   /  | |\\ | |___  ║" + ANSI_RESET);
        System.out.println(ANSI_BLUE + "                          ║  |___ |  | |  \\    |  | |  | |__] |  |  /__ | | \\| |___  ║" + ANSI_RESET);
        System.out.println(ANSI_BLUE + "                          ║                                                          ║" + ANSI_RESET);
        System.out.println(ANSI_BLUE + "                          ╚══════════════════════════════════════════════════════════╝" + ANSI_RESET);
        System.out.println(ANSI_CYAN + "                               Github: https://github.com/DariaBurd/CarMagazine/" + ANSI_RESET);
    }

    private void printTableHeader() {
        System.out.println("+──────────────┬───────────────────────────────────┬───────────────────────────────────────────────────────────+");
        System.out.println("│      №       │ Действие                          │ Описание                                                  │");
        System.out.println("├──────────────┼───────────────────────────────────┼───────────────────────────────────────────────────────────┤");
    }

    private void printMenuItem(int number, String action, String description) {
        System.out.printf("│      %d       │ %-33s │ %-57s │\n", number, action, description);
    }

    private void printTableFooter() {
        System.out.println("+──────────────┴───────────────────────────────────┴───────────────────────────────────────────────────────────+");
    }

    private void printPrompt(String options) {
        System.out.print(ANSI_PURPLE + ">" + ANSI_RESET + " Выберите действие " + ANSI_GREEN + options + ": " + ANSI_RESET);
    }
}
