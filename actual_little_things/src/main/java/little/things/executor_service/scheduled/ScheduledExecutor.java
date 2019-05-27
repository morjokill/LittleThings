package little.things.executor_service.scheduled;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledExecutor {
    public static void scheduleTask(int periodMillis) {
        System.out.println("Scheduling tasks dispatcher with delay: " + periodMillis + " ms");
        ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();

        service.scheduleWithFixedDelay(new Task(), 0, periodMillis, TimeUnit.MILLISECONDS);
    }

    private static class Task implements Runnable {
        @Override
        public void run() {
            System.out.println("Task is running!");
        }
    }

    public static void main(String[] args) {
        scheduleTask(200);
    }
}
