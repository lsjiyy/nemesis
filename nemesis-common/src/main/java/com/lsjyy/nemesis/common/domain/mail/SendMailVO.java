package com.lsjyy.nemesis.common.domain.mail;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Authoer LsjYy
 * @DATE 2020-02-14 13:50
 * @Description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SendMailVO {
    private String receiveEmail;
    private String content;
    private String subject;
    private Integer mailContentType;
    private String templateType;
    private String mailType;
}
