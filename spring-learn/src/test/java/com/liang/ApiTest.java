package com.liang;


import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

@Slf4j
public class ApiTest {
    @Test
    public void test01() throws IOException {
        File file = new File("./src/test/java/files/test.txt");
        File dateFolder = new File("src/test/java/files");
        if (!dateFolder.exists()) {
            dateFolder.mkdirs();
        }
        try (FileWriter writer = new FileWriter(file)){
            writer.write("你这么厉害？");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try(OutputStream os = new FileOutputStream(file,true)){
            os.write("你真厉害".getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void test02() throws IOException, InterruptedException {
        String diff = diff();
        File file = new File("./src/test/java/files/test_diff.txt");
        File dateFolder = new File("src/test/java/files");
        if (!dateFolder.exists()) {
            dateFolder.mkdirs();
        }
        try(FileOutputStream os = new FileOutputStream(file)){
            os.write(diff.getBytes(StandardCharsets.UTF_8));
        }
        log.info("{}写入完毕",file.getAbsolutePath());
    }

    public String diff() throws IOException, InterruptedException {
        /**
         * 获取最新提交的哈希值 git log -1 --pretty=format:%H
         *  -1：只取最近一次提交；
         *  --pretty=format:%H：输出格式为提交的哈希值
         */
        ProcessBuilder logProcessBuilder = new ProcessBuilder("git", "log", "-1", "--pretty=format:%H");
        logProcessBuilder.directory(new File("."));
        Process logProcess = logProcessBuilder.start();

        /**
         * git diff <commit_hash>^ <commit_hash>
         * 获取最新提交代码 diff <base> <head>
         *  <base>：指定比较的基准版本；
         *  <head>：指定比较的HEAD版本。
         */
        BufferedReader logReader = new BufferedReader(new InputStreamReader(logProcess.getInputStream()));
        String latestCommitHash = logReader.readLine();
        logReader.close();
        logProcess.waitFor();

        ProcessBuilder diffProcessBuilder = new ProcessBuilder("git", "diff", latestCommitHash + "^", latestCommitHash);
        diffProcessBuilder.directory(new File("."));
        Process diffProcess = diffProcessBuilder.start();

        StringBuilder diffCode = new StringBuilder();
        BufferedReader diffReader = new BufferedReader(new InputStreamReader(diffProcess.getInputStream()));
        String line;
        while ((line = diffReader.readLine()) != null) {
            diffCode.append(line).append("\n");
        }
        diffReader.close();

        int exitCode = diffProcess.waitFor();
        if (exitCode != 0) {
            throw new RuntimeException("Failed to get diff, exit code:" + exitCode);
        }

        return diffCode.toString();
    }
}
