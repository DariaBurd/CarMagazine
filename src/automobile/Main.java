package automobile;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Введите мощность: ");
            int power = Integer.parseInt(scanner.nextLine());

            System.out.print("Введите год: ");
            int year = Integer.parseInt(scanner.nextLine());

            System.out.print("Введите комплектацию: ");
            String configuration = scanner.nextLine();

            System.out.print("Введите цвет: ");
            String color = scanner.nextLine();

            Automobile car = new Automobile.Builder()
                    .setPower(power)
                    .setYear(year)
                    .setConfiguration(configuration)
                    .setColor(color)
                    .build();

            System.out.println("Создан объект:");
            System.out.println(car);

        } catch (Exception e) {
            System.out.println("Ошибка ввода: " + e.getMessage());
        }

        scanner.close();
    }
}
