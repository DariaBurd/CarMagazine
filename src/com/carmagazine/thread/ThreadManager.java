package com.carmagazine.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public class ThreadManager {
    private final ExecutorService executorService;
    private boolean enabled;

    public ThreadManager(ExecutorService executorService) {
        this.executorService = executorService;
        this.enabled = false;
    }

    public void toggle() {
        this.enabled = !this.enabled;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void execute(Runnable task) {
        if (enabled) {
            try {
                Future<?> future = executorService.submit(task);
                future.get(); // Ждем завершения задачи, чтобы ввод не перепутался
            } catch (Exception e) {
                System.out.println("Ошибка выполнения задачи в потоке: " + e.getMessage());
            }
        } else {
            task.run();
        }
    }

    public void shutdown() {
        executorService.shutdown();
    }
}
