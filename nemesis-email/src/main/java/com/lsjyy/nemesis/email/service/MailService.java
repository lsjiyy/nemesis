package com.lsjyy.nemesis.email.service;

import com.lsjyy.nemesis.common.domain.mail.SendMailVO;
import freemarker.template.TemplateException;

import java.io.IOException;

/**
 * @Authoer LsjYy
 * @DATE 2020-02-14 13:46
 * @Description:
 */
public interface MailService {
    void sendHtmlMail(SendMailVO vo);

    void sendTextMail(SendMailVO vo);

    void receiveContent(String content) ;


}
