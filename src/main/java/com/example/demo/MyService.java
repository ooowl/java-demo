package com.example.demo;

import java.util.concurrent.Executor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class MyService {

    @Autowired
    private Executor customTaskExecutor;


    @Async("customTaskExecutor")
    public void executeAsyncTask() {
        System.out.println("任务由线程 " + Thread.currentThread().getName() + " 执行");
        // 模拟耗时任务
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void executeAsyncTask_back() {
        customTaskExecutor.execute(() -> {
            System.out.println("任务由线程 " + Thread.currentThread().getName() + " 执行");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
    }
}
