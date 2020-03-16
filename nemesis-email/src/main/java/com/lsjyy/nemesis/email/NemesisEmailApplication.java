package com.lsjyy.nemesis.email;

import com.lsjyy.nemesis.common.domain.AjaxResult;
import com.lsjyy.nemesis.common.domain.mail.SendMailVO;
import com.lsjyy.nemesis.email.service.MailService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@MapperScan("com.lsjyy.nemesis.email.dao")
@ComponentScan("com.lsjyy.nemesis.email")
@EnableAutoConfiguration
@RestController
public class NemesisEmailApplication {

    public static void main(String[] args) {
        SpringApplication.run(NemesisEmailApplication.class, args);
    }

    @Autowired
    private MailService mailService;

    @PostMapping("/email")
    public AjaxResult sendTest(SendMailVO vo) {
        try {
            mailService.sendHtmlMail(vo);
            return AjaxResult.success("");
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.error("");
        }
    }
}
