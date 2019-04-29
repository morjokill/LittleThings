package little.things.enums;

public enum Fruits implements Eatable {
    BANANA("yellow") {
        @Override
        public void eat() {
            System.out.println("Eating very " + getColor() + " banana");
        }
    }, ORANGE("orange") {
        @Override
        public void eat() {
            System.out.println("Eating very " + getColor() + " orange");
        }
    }, APPLE("green") {
        @Override
        public void eat() {
            System.out.println("Eating very " + getColor() + " apple");
        }
    };

    private String color;

    Fruits(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public static void main(String[] args) {
        Fruits[] values = Fruits.values();
        for (Fruits value : values) {
            System.out.println(value + " " + value.color);
            value.eat();
        }
    }
}
