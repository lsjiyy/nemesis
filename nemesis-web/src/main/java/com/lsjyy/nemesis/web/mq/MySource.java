package com.lsjyy.nemesis.web.mq;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 * @Authoer LsjYy
 * @DATE 2020-02-29 11:23
 * @Description:
 */
public interface MySource {

    String str = "myOutput";   //管道名称为"myOutput"

    @Output(str)
    MessageChannel myOutput();

}
