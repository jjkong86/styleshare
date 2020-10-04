package styleshare.task.utils;

import org.jetbrains.annotations.NotNull;

import java.util.concurrent.ThreadFactory;

class NamedThreadsFactory implements ThreadFactory {
    private int count = 0;

    @Override
    public Thread newThread(@NotNull Runnable runnable) {
        String name = "Board Game Event Thread-";
        return new Thread(runnable, name + ++count);
    }
}
