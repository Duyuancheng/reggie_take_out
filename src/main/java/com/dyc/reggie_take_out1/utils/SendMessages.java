package com.dyc.reggie_take_out1.utils;


import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class SendMessages {


    /**
     * title：邮箱体
     * code：验证码
     * email：收件人的邮箱
     */
    public static void Send(String tittle, String code, String email) throws MessagingException {
        //创建Properties 类记录邮箱的一些属性
        Properties properties = new Properties();
        //下面内容是SMTP发送邮件，必须进行身份验证
        properties.put("mail.smtp.auth", "true");

        //TODO 填写SMTP服务器 smtp.qq.com QQ邮箱 smtp.163.com 网易邮箱
        properties.put("mail.smtp.host", "smtp.qq.com");// 主机名 QQ邮箱
        //properties.put("mail.smtp.host", "smtp.163.com");

        //TODO QQ邮箱端口号587  网易端口25
//        properties.put("mail.smtp.port", 25 );
        properties.put("mail.smtp.port", 587 );

        //TODO 填写写信人账号
        properties.put("mail.user", "1661875410@qq.com");
        //TODO 填写16位写信人账号授权码
        properties.put("mail.password", "ecxngiauuljzebhg");

        //构建授权信息，用于进行SMTP进行身份验证
        Authenticator authenticator = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                //用户名和密码
                String username = properties.getProperty("mail.user");
                String password = properties.getProperty("mail.password");
                return new PasswordAuthentication(username, password);
            }
        };
        //使用环境授权属性和授权信息，创建邮件会话
        Session mailSession = Session.getInstance(properties, authenticator);
        //创建邮件信息
        MimeMessage message = new MimeMessage(mailSession);
        //设置发件人
        InternetAddress from = new InternetAddress(properties.getProperty("mail.user"));
        message.setFrom(from);

        //TODO 设置收件人 email是收件人的邮箱地址
        InternetAddress to = new InternetAddress(email);

        message.setRecipient(Message.RecipientType.TO, to);

        //TODO 设置邮件主题（标题）
        message.setSubject(tittle);

        //TODO 设置邮箱体(邮件内容）
        //text/html;charset=UTF-8 表示时html文件，编码是utf-8
        message.setContent(" 您正在进行登录验证，验证码为：" + code + "，切勿将验证码泄漏于他人。本条验证码有效期为5分钟。", "text/html;charset=UTF-8");

        //发送邮件
        Transport.send(message);
    }
}
