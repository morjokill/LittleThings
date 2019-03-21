package trash;

public class Main {
    public static void main(String[] args) {
        String fileName = "testupload.csv";
        System.out.println(fileName.indexOf('.'));
        System.out.println(fileName.substring(fileName.indexOf('.') + 1));
    }
}
