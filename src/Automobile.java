import java.util.Objects;

public class Automobile {

    private final int power;
    private final int year;
    private final String configuration;
    private final String color;

    private Automobile(Builder builder) {
        this.power = builder.power;
        this.year = builder.year;
        this.configuration = builder.configuration;
        this.color = builder.color;
    }

    public int getPower() {
        return power;
    }

    public int getYear() {
        return year;
    }

    public String getConfiguration() {
        return configuration;
    }

    public String getColor() {
        return color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Automobile that = (Automobile) o;

        return power == that.power &&
                year == that.year &&
                Objects.equals(configuration, that.configuration) &&
                Objects.equals(color, that.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(power, year, configuration, color);
    }

    @Override
    public String toString() {
        return "Автомобиль {" +
                "мощность=" + power +
                ", год=" + year +
                ", комплектация='" + configuration + '\'' +
                ", цвет='" + color + '\'' +
                '}';
    }

    public static class Builder {
        private Integer power;
        private Integer year;
        private String configuration;
        private String color;

        public Builder setPower(int power) {
            if (power <= 0) {
                throw new IllegalArgumentException("Мощность должна быть больше 0");
            }
            this.power = power;
            return this;
        }

        public Builder setYear(int year) {
            if (year < 1886 || year > 2026) {
                throw new IllegalArgumentException("Некорректный год выпуска");
            }
            this.year = year;
            return this;
        }

        public Builder setConfiguration(String configuration) {
            if (configuration == null || configuration.trim().isEmpty()) {
                throw new IllegalArgumentException("Комплектация не может быть пустой");
            }
            this.configuration = configuration;
            return this;
        }

        public Builder setColor(String color) {
            if (color == null || color.trim().isEmpty()) {
                throw new IllegalArgumentException("Цвет не может быть пустым");
            }
            this.color = color;
            return this;
        }

        public Automobile build() {

            if (power == null)
                throw new IllegalStateException("Мощность не задана");

            if (year == null)
                throw new IllegalStateException("Год не задан");

            if (configuration == null)
                throw new IllegalStateException("Комплектация не задана");

            if (color == null)
                throw new IllegalStateException("Цвет не задан");

            return new Automobile(this);
        }
    }
}
