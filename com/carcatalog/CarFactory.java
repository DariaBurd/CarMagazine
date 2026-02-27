import java.util.*;
import java.io.*;

public class CarFactory {

    private static final Random random = new Random();

    private static final String[] CONFIGURATIONS = {"Base", "Sport", "Luxury", "Premium"};
    private static final String[] COLORS = {"Белый", "Черный", "Серебристый", "Синий", "Красный"};

    public static List<Automobile> generateRandom(int count) {
        List<Automobile> cars = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            Automobile car = new Automobile.Builder()
                    .setPower(50 + random.nextInt(451)) // 50-500 л.с.
                    .setYear(2000 + random.nextInt(27)) // 2000-2026
                    .setConfiguration(CONFIGURATIONS[random.nextInt(CONFIGURATIONS.length)])
                    .setColor(COLORS[random.nextInt(COLORS.length)])
                    .build();
            cars.add(car);
        }

        System.out.println("Сгенерировано автомобилей: " + count);
        return cars;
    }

    public static List<Automobile> inputFromUser(Scanner scanner, int count) {
        List<Automobile> cars = new ArrayList<>();

        System.out.println("\nВвод автомобилей вручную:");

        for (int i = 0; i < count; i++) {
            System.out.println("\nАвтомобиль " + (i + 1) + " из " + count);

            String powerStr;
            do {
                System.out.print("Мощность (л.с.): ");
                powerStr = scanner.nextLine();
                if (!InputValidator.isValidPower(powerStr)) {
                    System.out.println("Ошибка: мощность должна быть числом больше 0");
                }
            } while (!InputValidator.isValidPower(powerStr));


            String yearStr;
            do {
                System.out.print("Год выпуска (1886-2026): ");
                yearStr = scanner.nextLine();
                if (!InputValidator.isValidYear(yearStr)) {
                    System.out.println("Ошибка: год должен быть от 1886 до 2026");
                }
            } while (!InputValidator.isValidYear(yearStr));


            String config;
            do {
                System.out.print("Комплектация: ");
                config = scanner.nextLine();
                if (!InputValidator.isValidConfiguration(config)) {
                    System.out.println("Ошибка: комплектация не может быть пустой");
                }
            } while (!InputValidator.isValidConfiguration(config));


            String color;
            do {
                System.out.print("Цвет: ");
                color = scanner.nextLine();
                if (!InputValidator.isValidColor(color)) {
                    System.out.println("Ошибка: цвет не может быть пустым");
                }
            } while (!InputValidator.isValidColor(color));


            Automobile car = new Automobile.Builder()
                    .setPower(Integer.parseInt(powerStr))
                    .setYear(Integer.parseInt(yearStr))
                    .setConfiguration(config)
                    .setColor(color)
                    .build();

            cars.add(car);
            System.out.println("Автомобиль добавлен");
        }

        System.out.println("\nВсего добавлено: " + cars.size() + " автомобилей");
        return cars;
    }


    public static List<Automobile> loadFromFile(String filename) {
        List<Automobile> cars = new ArrayList<>();
        int lineNumber = 0;
        int loaded = 0;
        int skipped = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;

            while ((line = reader.readLine()) != null) {
                lineNumber++;

                if (line.trim().isEmpty()) {
                    continue;
                }

                String[] parts = line.split(",");

                if (parts.length == 4) {
                    try {
                        String powerStr = parts[0].trim();
                        String yearStr = parts[1].trim();
                        String config = parts[2].trim();
                        String color = parts[3].trim();

                        if (!InputValidator.isValidPower(powerStr)) {
                            throw new IllegalArgumentException("Неверная мощность");
                        }
                        if (!InputValidator.isValidYear(yearStr)) {
                            throw new IllegalArgumentException("Неверный год");
                        }
                        if (!InputValidator.isValidConfiguration(config)) {
                            throw new IllegalArgumentException("Неверная комплектация");
                        }
                        if (!InputValidator.isValidColor(color)) {
                            throw new IllegalArgumentException("Неверный цвет");
                        }

                        Automobile car = new Automobile.Builder()
                                .setPower(Integer.parseInt(powerStr))
                                .setYear(Integer.parseInt(yearStr))
                                .setConfiguration(config)
                                .setColor(color)
                                .build();

                        cars.add(car);
                        loaded++;

                    } catch (Exception e) {
                        System.out.println("Строка " + lineNumber + " пропущена: " + e.getMessage());
                        skipped++;
                    }
                } else {
                    System.out.println("Строка " + lineNumber + " пропущена: неверный формат (нужно 4 поля)");
                    skipped++;
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден: " + filename);
            return cars;
        } catch (IOException e) {
            System.out.println("Ошибка чтения файла: " + e.getMessage());
            return cars;
        }

        System.out.println("\nЗагрузка из файла завершена:");
        System.out.println("Загружено: " + loaded);
        if (skipped > 0) {
            System.out.println("Пропущено: " + skipped);
        }

        return cars;
    }
}
