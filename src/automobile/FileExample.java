package automobile;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileExample {
    public static void main(String[] args) {

        try (BufferedReader reader = new BufferedReader(new FileReader("car.txt"))) {

            int power = Integer.parseInt(reader.readLine());
            int year = Integer.parseInt(reader.readLine());
            String configuration = reader.readLine();
            String color = reader.readLine();

            Automobile car = new Automobile.Builder()
                    .setPower(power)
                    .setYear(year)
                    .setConfiguration(configuration)
                    .setColor(color)
                    .build();

            System.out.println(car);

        } catch (IOException | IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }
}
