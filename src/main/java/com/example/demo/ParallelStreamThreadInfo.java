package com.example.demo;

public class ParallelStreamThreadInfo {

    public static void main(String[] args) {
        // 查看默认的 ForkJoinPool 线程数
        int parallelism = java.util.concurrent.ForkJoinPool.commonPool().getParallelism();
        System.out.println("Default parallelism: " + parallelism);

        // 可用处理器数量
        int availableProcessors = Runtime.getRuntime().availableProcessors();
        System.out.println("Available processors: " + availableProcessors);
    }
}
