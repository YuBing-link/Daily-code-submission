package com.hmdp.utils;


import com.hmdp.entity.Mail;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
@Slf4j
public class MailUtils {
    @Value("${spring.mail.username}")
    private String sender; //邮件发送者

    @Resource
    private JavaMailSender javaMailSender;
    /**
     * 发送文本邮件
     *
     * @param mail
     */
    public void sendSimpleMail(Mail mail) {
        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setFrom(sender); //邮件发送者
            mailMessage.setTo(mail.getRecipient()); // 邮件发给的人
            mailMessage.setSubject(mail.getSubject());  // 邮件主题
            mailMessage.setText(mail.getContent());  // 邮件内容
            //mailMessage.copyTo(copyTo);

            javaMailSender.send(mailMessage);
            log.info("邮件发送成功 收件人：{}", mail.getRecipient());
        } catch (Exception e) {
            log.error("邮件发送失败 {}", e.getMessage());
            throw new RuntimeException("邮件找不到"); //这是我自定义的一个异常码
        }
    }

    //复杂邮件
//    MimeMessage mimeMessage = mailSender.createMimeMessage();
//    MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
//        messageHelper.setFrom("jiuyue@163.com");
//        messageHelper.setTo("September@qq.com");
//        messageHelper.setSubject("BugBugBug");
//        messageHelper.setText("一杯茶，一根烟，一个Bug改一天！");
//        messageHelper.addInline("bug.gif", new File("xx/xx/bug.gif"));
//        messageHelper.addAttachment("bug.docx", new File("xx/xx/bug.docx"));
//        mailSender.send(mimeMessage);

}

