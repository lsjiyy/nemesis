package com.lsjyy.nemesis.common.utils;

import java.util.UUID;

/**
 * @Authoer LsjYy
 * @DATE 2020-02-11 15:40
 * @Description:
 */
public class PrimaryKeyUtil {
    public static String generateKey(String key) {
        StringBuilder keyBuilder = new StringBuilder("");
        keyBuilder.append(key)
                .append(UUID.randomUUID().toString().replace("-", "").toUpperCase().substring(0, 7))
                .append(String.valueOf(System.currentTimeMillis()).substring(0, 9));
        return keyBuilder.toString();
    }
}
