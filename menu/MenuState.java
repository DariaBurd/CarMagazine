package com.carmagazine.menu;

public enum MenuState {
    MAIN,                    // Главный экран
    SOURCE,                  // Выбор источника (файл/ручной ввод/генерация)
    GENERATE,                // Генерация случайных авто
    MANUAL_INPUT,            // Ручной ввод
    FILE_LOAD,               // Загрузка из файла

    SEARCH,                  // Меню поиска
    SORT_MENU,               // Меню сортировки
    FILTER_MENU,             // Меню фильтрации

    SEARCH_COMPLICATION,     // Поиск по комплектации
    SEARCH_POWER,            // Поиск по мощности
    SEARCH_YEAR,             // Поиск по году
    SEARCH_COLOR,            // Поиск по цвету
    SEARCH_ANOTHER,            // Поиск по цвету
}