package com.liang;


import com.alibaba.fastjson2.JSON;
import com.liang.bean.Message;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j
public class ApiTest {
    @Test
    public void test_write_01() throws IOException {
        File file = new File("./src/test/java/files/test.txt");
        File dateFolder = new File("src/test/java/files");
        if (!dateFolder.exists()) {
            dateFolder.mkdirs();
        }
        try (FileWriter writer = new FileWriter(file)){
            writer.write("寒影决  ");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try(OutputStream os = new FileOutputStream(file,true)){
            os.write("攻敌三分，自留七分".getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        log.info("{}写入成功！",file.getAbsoluteFile());
    }

    @Test
    public void test_write_02() throws IOException, InterruptedException {
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
    @Test
    public void test_read_03() throws IOException {
        File file = new File("./src/test/java/files/test_diff.txt");
        StringBuilder response = new StringBuilder("读取数据：");
        try(FileReader fileReader = new FileReader(file)){
            BufferedReader br = new BufferedReader(fileReader);
            String inputLine;
            while ((inputLine = br.readLine()) != null) {
                response.append(inputLine).append("\n");
            }
            br.close();
        }
        log.info(String.valueOf(response));
    }

    @Test
    public void test04() throws IOException {
        // 1.初始化url
        URL url = new URL("http://localhost:8091/test/data");
        // 2.打开连接并设置请求方式
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        // 3.获取conn的输入流
        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();
        while((inputLine=br.readLine())!=null){
            response.append(inputLine).append("\n");
        }
        br.close();
        log.info("测试响应值：{}",response);
    }

    @Test
    public void test05() throws IOException {
        URL url = new URL("http://localhost:8091/test/send");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json; utf-8");
        conn.setRequestProperty("Accept", "application/json");
        conn.setDoOutput(true);
        Message message = new Message();
        message.put("title","测试消息标题");
        message.put("content","测试消息内容");
        try(OutputStream os = conn.getOutputStream()){
            os.write(JSON.toJSONString(message).getBytes(StandardCharsets.UTF_8));
        }
        StringBuilder response = new StringBuilder();
        try(InputStream in = conn.getInputStream()){
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String inputLine;
            while((inputLine = br.readLine())!=null){
                response.append(inputLine);
            }
            br.close();
        }
        log.info("响应信息：{}",response);
    }
    @Test
    public void test06(){
        Set<Character> set = new HashSet<>(List.of('a','e','i','o','u'));
        String s = "A man, a plan, a canal: Panama9281034";
        String str = s.replaceAll("[^A-Za-z0-9]", "").toLowerCase();
        log.info("测试字符串结果：{}",str);
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
