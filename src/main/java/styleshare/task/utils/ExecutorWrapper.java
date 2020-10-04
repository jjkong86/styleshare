package styleshare.task.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class ExecutorWrapper {
    private ExecutorService executorService;
    private CountDownLatch countDownLatch;
    private AtomicBoolean atomicBoolean;
    private List<Future<?>> list = new ArrayList<>();

    public ExecutorWrapper(int threadCount) {
        NamedThreadsFactory factory = new NamedThreadsFactory();
        this.executorService = Executors.newFixedThreadPool(threadCount, factory);
        this.countDownLatch = new CountDownLatch(threadCount);
    }

    public <T> void supportExecute(Callable<T> callable) {
        try {
            this.list.add(executorService.submit(callable));
        } catch (Exception e) {
            e.printStackTrace();
            this.atomicBoolean.set(false);
        }
    }

    public void countDown() {
        if (this.countDownLatch != null) {
            this.countDownLatch.countDown(); // count--
        }
    }

    // maximum await 10 seconds
    public void executorWaitAndShutdown() {
        this.executorService.shutdown();
        list.forEach(ExecutorWrapper::accept);
    }

    private static void accept(Future<?> future) {
        try {
            System.out.println(future.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }
}
