/*
 *            DO WHAT THE FUCK YOU WANT TO PUBLIC LICENSE
 *                    Version 2, December 2004
 *
 * Copyright (C) 2004 Sam Hocevar <sam@hocevar.net>
 *
 * Everyone is permitted to copy and distribute verbatim or modified
 * copies of this license document, and changing it is allowed as long
 * as the name is changed.
 *
 *            DO WHAT THE FUCK YOU WANT TO PUBLIC LICENSE
 *   TERMS AND CONDITIONS FOR COPYING, DISTRIBUTION AND MODIFICATION
 *
 *   0. You just DO WHAT THE FUCK YOU WANT TO.
 *
 * ----------------------------------------------------------------------
 * Copyright (C) 2026 fnJey
 * ----------------------------------------------------------------------
 */

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

    SORT_ANOTHER,            // Кастомная сортировка
    SORT_YEAR,               // Сортировка по году
    SORT_COLOR,              // Сортировка по цвету
    SORT_COMPLICATION,       // Сортировка по комплектации
    SORT_POWER,              // Сортировка по мощности
}