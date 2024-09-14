package com.example.demo;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.util.Random;

public class RandomNumberFileGenerator {

    public static void main(String[] args) {
        // 定义生成的文件名
        String fileName = "E:\\temp\\random_numbers2.txt";
        // 定义需要生成的行数
//        int linesToGenerate = 1000000000; // 可以修改这个数字来生成不同数量的行
        int linesToGenerate = 4000; // 可以修改这个数字来生成不同数量的行

        // 调用方法生成文件
        try {
            generateRandomNumberFile(fileName, linesToGenerate);
            System.out.println("文件生成成功: " + fileName);
        } catch (IOException e) {
            System.err.println("文件生成失败: " + e.getMessage());
        }
    }

    /**
     * 生成一个包含随机数字的文件，每行一个数字（0-9）
     * @param fileName 要生成的文件名
     * @param linesToGenerate 要生成的行数
     * @throws IOException 文件写入异常
     */
    public static void generateRandomNumberFile(String fileName, int linesToGenerate) throws IOException {
        Random random = new Random();

        // 使用 OutputStreamWriter 并指定 UTF-8 编码
        try (Writer writer = new OutputStreamWriter(new FileOutputStream(fileName), StandardCharsets.UTF_8)) {
            // 写入 UTF-8 BOM (Byte Order Mark) 确保 Windows 记事本能正确识别为 UTF-8
            writer.write("\uFEFF");

            for (int i = 0; i < linesToGenerate; i++) {
                int randomNumber = random.nextInt(10); // 生成 0-9 之间的随机数
                writer.write(randomNumber + "\n"); // 写入文件并换行
            }
        }
    }
}
