package com.lsjyy.nemesis.system.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Authoer LsjYy
 * @DATE 2020-02-09 19:12
 * @Description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServiceInfo {
    private String serviceId;
    private String serviceName;
    private String explain;
    private int port;
}
