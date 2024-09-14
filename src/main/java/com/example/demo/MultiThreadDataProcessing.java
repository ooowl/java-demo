package com.example.demo;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.ArrayList;
import java.util.List;

public class MultiThreadDataProcessing {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        // 创建一个包含1000万条数据的数组
        long[] data = new long[10000000];
        for (int i = 0; i < data.length; i++) {
            data[i] = i;
        }

        // 定义线程池大小
        int numThreads = 4;
        ExecutorService executor = Executors.newFixedThreadPool(numThreads);

        // 分割任务
        List<Future<Long>> futures = new ArrayList<>();
        int chunkSize = data.length / numThreads;

        // 提交任务给线程池
        for (int i = 0; i < numThreads; i++) {
            int start = i * chunkSize;
            int end = (i == numThreads - 1) ? data.length : (i + 1) * chunkSize;
            Callable<Long> task = new DataProcessingTask(data, start, end);
            futures.add(executor.submit(task));
        }

        // 收集结果
        long totalSum = 0;
        for (Future<Long> future : futures) {
            totalSum += future.get();
        }

        // 关闭线程池
        executor.shutdown();

        System.out.println("Total Sum: " + totalSum);
    }
}

// 每个线程处理数据的任务
class DataProcessingTask implements Callable<Long> {
    private long[] data;
    private int start;
    private int end;

    public DataProcessingTask(long[] data, int start, int end) {
        this.data = data;
        this.start = start;
        this.end = end;
    }

    @Override
    public Long call() {
        long sum = 0;
        for (int i = start; i < end; i++) {
            sum += data[i];  // 模拟数据处理
        }
        return sum;
    }
}
