package com.lsjyy.nemesis.email.pojo;

import com.lsjyy.nemesis.common.domain.mail.SendMailVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

/**
 * @Authoer LsjYy
 * @DATE 2020-02-15 16:40
 * @Description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmailHistory {
    private String emailId;
    private String receiveEmail;
    private String content;
    private String status;
    private String emailType;
    private Timestamp createTime;

}
