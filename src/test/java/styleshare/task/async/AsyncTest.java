package styleshare.task.async;

import org.junit.Test;
import styleshare.task.utils.ExecutorWrapper;

import java.util.concurrent.atomic.AtomicBoolean;

public class AsyncTest {

    int THREAD_COUNT = 7;
    ExecutorWrapper executorWrapper = new ExecutorWrapper(THREAD_COUNT);

    @Test
    public void async_test() {
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            executorWrapper.supportExecute(() ->
            {
                System.out.println(Thread.currentThread().getName());
                if (finalI == 5) {
                    throw new RuntimeException("예외 발생 !");
                }

                return null;
            });
        }

        executorWrapper.executorWaitAndShutdown(); // thread await and shutdown
    }
}
