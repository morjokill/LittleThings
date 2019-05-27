package little.things.bits_training;

public class Main {
    public static void main(String[] args) {
        byte myByte = 8;
        System.out.println(myByte);
        System.out.println(getBit(myByte, 7));
    }

    public static int getBit(byte myByte, int position) {
        return (myByte >> position) & 1;
    }
}
