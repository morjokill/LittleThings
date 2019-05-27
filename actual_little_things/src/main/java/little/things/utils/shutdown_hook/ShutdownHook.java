package little.things.utils.shutdown_hook;

public class ShutdownHook implements Runnable {
    public static void addRuntimeShutdownHook() {
        System.out.println("Registering ShutdownHook");
        Runtime.getRuntime().addShutdownHook(new Thread(new ShutdownHook()));
    }

    @Override
    public void run() {
        System.out.println("Starting shutdown hook thread...");
    }

    public static void main(String[] args) {
        ShutdownHook.addRuntimeShutdownHook();
        System.exit(0);
    }
}
