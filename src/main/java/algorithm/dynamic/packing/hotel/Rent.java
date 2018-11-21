package algorithm.dynamic.packing.hotel;

public class Rent {
    private int day1;
    private int day2;
    private int payment;

    public Rent(int day1, int day2, int payment) {
        this.day1 = day1;
        this.day2 = day2;
        this.payment = payment;
    }

    public int getDay1() {
        return day1;
    }

    public int getDay2() {
        return day2;
    }

    public int getPayment() {
        return payment;
    }

    @Override
    public String toString() {
        return "Rent{" +
                "day1=" + day1 +
                ", day2=" + day2 +
                ", payment=" + payment +
                '}';
    }
}
