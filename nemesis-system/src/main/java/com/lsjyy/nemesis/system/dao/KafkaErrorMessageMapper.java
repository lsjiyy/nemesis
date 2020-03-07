package com.lsjyy.nemesis.system.dao;

import com.lsjyy.nemesis.system.pojo.KafkaErrorMessage; /**
 * @Authoer LsjYy
 * @DATE 2020-03-07 18:41
 * @Description:
 */
public interface KafkaErrorMessageMapper {
    void insert(KafkaErrorMessage errorMessage);
}
