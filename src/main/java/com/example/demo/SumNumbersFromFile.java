package com.example.demo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Stream;

public class SumNumbersFromFile {

    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {
        long startTime = System.currentTimeMillis();
//        String filePath = "E:\\temp\\random_numbers1.txt"; // 替换为你的文件路径
////        long sum = getSumMultiThread(filePath);
//        long sum = getSumSingleThread(filePath);
//        System.out.println("文件中数字的总和是: " + sum);
        asd();
        long endTime = System.currentTimeMillis();
        System.out.println("程序运行时间: " + (endTime - startTime) / 1000.0 + " 秒");
    }

    private static long getSumMultiThread(String filePath)
            throws IOException, ExecutionException, InterruptedException {
        // 线程池
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        // 每个线程负责的任务
        List<Future<Long>> futures = new ArrayList<>();

        // 将文件按区块分配给不同的线程，每个线程计算部分和
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            List<String> block = new ArrayList<>();
            int blockSize = 1000;  // 每1000行作为一个任务分配给一个线程

            while ((line = reader.readLine()) != null) {
                block.add(line);
                if (block.size() == blockSize) {
                    // 提交给线程池处理
                    futures.add(executorService.submit(new SumTask(new ArrayList<>(block))));
                    block.clear();
                }
            }
            // 处理最后不足 blockSize 的数据
            if (!block.isEmpty()) {
                futures.add(executorService.submit(new SumTask(new ArrayList<>(block))));
            }
        }

        // 等待所有线程计算完成并汇总结果
        long totalSum = 0;
        for (Future<Long> future : futures) {
            totalSum += future.get();  // 获取每个线程的计算结果
        }

        // 关闭线程池
        executorService.shutdown();

        System.out.println("Total sum: " + totalSum);
        return totalSum;
    }

    // 定义每个线程要执行的任务
    static class SumTask implements Callable<Long> {

        // 内部数据，有 1000 个列表
        private final List<String> data;

        // 外部方法，告诉外界，我有个计算任务的方法
        public SumTask(List<String> data) {
            this.data = data;
        }

        // 外部方法，告诉外界，可以调用这个 call 方法，作用就是将自己的数据域加起来
        @Override
        public Long call() {
            long sum = 0;
            for (String line : data) {
                try {
                    // 将每一行的数字进行累加
                    sum += Integer.parseInt(line.trim());
                } catch (NumberFormatException e) {
                    System.out.println("跳过无效的数字: " + line);
                }
            }
            return sum;
        }
    }

    private static long getSumSingleThread(String filePath) {
        long sum = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                try {
                    // 将每一行的数字进行累加
                    sum += Integer.parseInt(line.trim());
                } catch (NumberFormatException e) {
                    System.out.println("跳过无效的数字: " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("读取文件时出错: " + e.getMessage());
        }
        return sum;
    }

    private static void asd() {
        String filePath = "E:\\temp\\random_numbers1.txt"; // 假设文件名为 numbers.txt

        try (Stream<String> lines = Files.lines(Paths.get(filePath))) {
            // 使用 parallel() 方法将 Stream 转换为并行流
            long totalSum = lines.parallel() // 并行处理每行
                    .map(String::trim) // 移除每行的空格
                    .filter(line -> !line.isEmpty()) // 过滤掉空行
                    .mapToLong(line -> {
                        try {
                            return Long.parseLong(line);
                        } catch (NumberFormatException e) {
                            System.out.println("Warning: Invalid number format: " + line);
                            return 0; // 出错时返回 0
                        }
                    })
                    .sum(); // 计算总和

            System.out.println("Total sum of numbers: " + totalSum);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
