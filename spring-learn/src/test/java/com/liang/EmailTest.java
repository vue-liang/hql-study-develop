package com.liang;

import com.liang.utils.MarkdownConverter;
import com.liang.utils.MimeTypeDetector;
import org.junit.jupiter.api.Test;

import javax.activation.DataHandler;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class EmailTest {

    @Test
    public void testSendEmail() {
        long start = System.currentTimeMillis();
        //	账号信息
        String username = "2330983050@qq.com";//	邮箱发送账号
        String password = "magsnyuikkgaeafd";//	邮箱授权码

        //	创建一个配置文件，并保存
        Properties properties = new Properties();

        //	SMTP服务器连接信息
        //  126——smtp.126.com
        //  163——smtp.163.com
        //  qq——smtp.qq.com
        properties.put("mail.smtp.host", "smtp.qq.com");//	SMTP主机名

        //  126——25
        //  163——465
        properties.put("mail.smtp.port", "587");//	主机端口号
        properties.put("mail.smtp.auth", "true");//	是否需要用户认证
        properties.put("mail.smtp.starttls.enable", "true");//	启用TlS加密

        // 获取默认session对象
        Session session = Session.getDefaultInstance(properties, new Authenticator() {
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password); //发件人邮件用户名、授权码
            }
        });
//        session.setDebug(true);

        try {
            //	创建邮件对象
            MimeMessage message = new MimeMessage(session);
            message.setSubject("测试发送主题");
//            message.setText("测试发送文本信息");
            message.setFrom(new InternetAddress(username, "龙戬"));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress("2330983050@qq.com", "火麟飞")); //3512972187@qq.com

//            Transport.send(message);
            //	邮件主体
            BodyPart textPart = new MimeBodyPart();

            String htmlContent = MarkdownConverter.convertToHtml("**关于DDD是什么，在维基百科有一个明确的定义。\"Domain-driven design (DDD) is a major software design approach.\" 也就是说DDD是一种主要的软件设计方法。而软件设计涵盖了；范式、模型、框架、方法论。**\n" +
                    "\n" +
                    "- 范式（paradigm）指的是一种编程思想。\n" +
                    "- 模型（model）指的是对现实世界或者问题的抽象描述。\n" +
                    "- 框架（framework）指的是提供了一系列通用功能和结构的软件工具。\n" +
                    "- 方法论（methodology）指的是一种系统的、有组织的解决问题的方法。\n" +
                    "\n" +
                    "所以，DDD不只是只有指导思想，伴随的DDD的还包括框架结构分层。但说到底，这些仍然是理论讨论。在没有一个DDD落地项目物参考下，其实大部分码农是没法完成DDD开发的。");
            textPart.setContent(htmlContent, "text/html;charset=utf-8");

            //	邮件附件
            BodyPart filePart = new MimeBodyPart();
            filePart.setFileName("龙戟.txt");

            //	提交附件文件
            Path path = Paths.get("./src/test/java/files/test.txt");
            filePart.setDataHandler(new DataHandler(new ByteArrayDataSource(Files.readAllBytes(path), Files.probeContentType(path))));

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(textPart);
            multipart.addBodyPart(filePart);

            //	将邮件装入信封
            message.setContent(multipart);

            Transport.send(message);
            long end = System.currentTimeMillis();
            System.out.println("发送邮箱耗时共计"+ TimeUnit.MILLISECONDS.toSeconds(end-start)+"s");
        } catch (MessagingException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static String getMimeType(String fileName) {
        if (fileName.endsWith(".txt")) return "text/plain";
        else if (fileName.endsWith(".pdf")) return "application/pdf";
        else if (fileName.endsWith(".jpg") || fileName.endsWith(".jpeg")) return "image/jpeg";
        else if (fileName.endsWith(".png")) return "image/png";
            // 可以继续添加其他类型...
        else return "application/octet-stream"; // 默认类型
    }


    @Test
    public void test_md2html() {
        System.out.println(MarkdownConverter.convertToHtml("**关于DDD是什么，在维基百科有一个明确的定义。\"Domain-driven design (DDD) is a major software design approach.\" 也就是说DDD是一种主要的软件设计方法。而软件设计涵盖了；范式、模型、框架、方法论。**\n" +
                "\n" +
                "- 范式（paradigm）指的是一种编程思想。\n" +
                "- 模型（model）指的是对现实世界或者问题的抽象描述。\n" +
                "- 框架（framework）指的是提供了一系列通用功能和结构的软件工具。\n" +
                "- 方法论（methodology）指的是一种系统的、有组织的解决问题的方法。\n" +
                "\n" +
                "所以，DDD不只是只有指导思想，伴随的DDD的还包括框架结构分层。但说到底，这些仍然是理论讨论。在没有一个DDD落地项目物参考下，其实大部分码农是没法完成DDD开发的。"));
    }

}
