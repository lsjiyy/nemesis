package com.lsjyy.nemesis.email.service;

import com.alibaba.fastjson.JSONObject;
import com.lsjyy.nemesis.common.domain.mail.MailContentType;
import com.lsjyy.nemesis.common.domain.mail.SendMailVO;
import com.lsjyy.nemesis.common.domain.template.TemplateType;
import com.lsjyy.nemesis.common.utils.PrimaryKeyUtil;
import com.lsjyy.nemesis.email.dao.EmailHistoryMapper;
import com.lsjyy.nemesis.email.pojo.EmailHistory;
import freemarker.template.Template;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import javax.mail.internet.MimeMessage;
import java.util.HashMap;
import java.util.Map;

/**
 * @Authoer LsjYy
 * @DATE 2020-02-14 13:46
 * @Description:
 */
@Service
public class MailServiceImpl implements MailService {
    private static final Logger log = LoggerFactory.getLogger(MailService.class);

    @Value("${spring.mail.username}")
    private String from;
    @Autowired
    private EmailHistoryMapper emailHistoryMapper;

    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private FreeMarkerConfigurer configurer;

    @Override
    public void sendHtmlMail(SendMailVO vo) {
        MimeMessage message = mailSender.createMimeMessage();
        boolean flag;
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(vo.getReceiveEmail());
            helper.setSubject(vo.getSubject());
            helper.setText(vo.getContent(), true);
            //模板填充模型
            Map<String, Object> model = new HashMap<>();
            model.put("content", vo.getContent());
            //读取模板
            Template template = configurer.getConfiguration().getTemplate(TemplateType.EMAIL);
            //模板转字符串
            String text = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
            helper.setText(text, true);
            vo.setContent(text);
            mailSender.send(message);
            flag = true;
        } catch (Exception e) {
            flag = false;
            log.error("发送html邮件时发生异常！", e);
        }
        recordEmail(vo, flag);
    }

    @Override
    public void sendTextMail(SendMailVO vo) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        try {
            mailMessage.setTo(vo.getReceiveEmail()); //发送目标
            mailMessage.setText(vo.getContent()); //发送内容
            mailMessage.setSubject(vo.getSubject()); //标题
            mailMessage.setFrom(from); //发送人
            mailSender.send(mailMessage);
        } catch (Exception e) {
            log.error("普通文本邮件发送失败", e);
        }
    }

    @Override
    public void receiveContent(String content) {
        SendMailVO vo = JSONObject.parseObject(content, SendMailVO.class);
        if (vo.getMailContentType() == MailContentType.TEXT) {
            sendTextMail(vo);
        } else {
            sendHtmlMail(vo);
        }
    }

    public void recordEmail(SendMailVO vo, boolean flag) {
        EmailHistory history = new EmailHistory();
        history.setEmailType(vo.getMailType());
        history.setContent(vo.getContent());
        if (flag) {
            history.setStatus("成功");
        } else {
            history.setStatus("失败");
        }
        history.setReceiveEmail(vo.getReceiveEmail());
        history.setEmailId(PrimaryKeyUtil.generateKey("Email_"));
        emailHistoryMapper.insert(history);
    }
}
