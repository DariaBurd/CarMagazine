public class AutomobileTest {

    public static void main(String[] args) {

        testValidCar();
        testInvalidPower();
        testInvalidYear();
        testEmptyConfiguration();
        testMissingField();

        System.out.println("\nВсе тесты завершены.");
    }

    private static void testValidCar() {
        try {
            Automobile car = new Automobile.Builder()
                    .setPower(150)
                    .setYear(2020)
                    .setConfiguration("Luxury")
                    .setColor("Black")
                    .build();

            System.out.println("testValidCar: OK");
            System.out.println(car);

        } catch (Exception e) {
            System.out.println("testValidCar: FAILED");
        }
    }

    private static void testInvalidPower() {
        try {
            new Automobile.Builder()
                    .setPower(-100);

            System.out.println("testInvalidPower: FAILED");

        } catch (IllegalArgumentException e) {
            System.out.println("testInvalidPower: OK (" + e.getMessage() + ")");
        }
    }

    private static void testInvalidYear() {
        try {
            new Automobile.Builder()
                    .setYear(1500);

            System.out.println("testInvalidYear: FAILED");

        } catch (IllegalArgumentException e) {
            System.out.println("testInvalidYear: OK (" + e.getMessage() + ")");
        }
    }

    private static void testEmptyConfiguration() {
        try {
            new Automobile.Builder()
                    .setConfiguration("");

            System.out.println("testEmptyConfiguration: FAILED");

        } catch (IllegalArgumentException e) {
            System.out.println("testEmptyConfiguration: OK (" + e.getMessage() + ")");
        }
    }

    private static void testMissingField() {
        try {
            new Automobile.Builder()
                    .setPower(100)
                    .setYear(2020)
                    .setColor("Red")
                    .build(); // нет комплектации

            System.out.println("testMissingField: FAILED");

        } catch (IllegalStateException e) {
            System.out.println("testMissingField: OK (" + e.getMessage() + ")");
        }
    }
}
