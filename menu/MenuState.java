package com.carmagazine.menu;

public enum MenuState {
    MAIN,
    SOURCE,           // Выбор источника (файл/ручной ввод/генерация)
    GENERATE,         // Генерация случайных авто
    MANUAL_INPUT,     // Ручной ввод
    FILE_LOAD,        // Загрузка из файла
    VIEW_CARS,        // Просмотр всех авто
    SORT_MENU,        // Меню сортировки
    FILTER_MENU,      // Меню фильтрации
    COLLECTION_TYPE,  // Выбор типа коллекции
    POWER,            // Настройка мощности
    YEAR,             // Настройка года
    COLOR,            // Настройка цвета
    SEARCH,
    COMPLECTATION     // Настройка комплектации
}