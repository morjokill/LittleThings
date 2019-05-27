package little.things.executor_service.thread_pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FixedThreadPool {
    public static void dispatchTasks(int poolSize) {
        ExecutorService service = Executors.newFixedThreadPool(poolSize);

        for (int i = 0; i < 10; i++) {
            service.execute(new Task());
        }
    }

    private static class Task implements Runnable {
        @Override
        public void run() {
            System.out.println("Task is running!");
        }
    }

    public static void main(String[] args) {
        dispatchTasks(5);
    }
}
