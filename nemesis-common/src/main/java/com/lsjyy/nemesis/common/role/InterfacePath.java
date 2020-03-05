package com.lsjyy.nemesis.common.role;

import lombok.Data;

/**
 * @Authoer LsjYy
 * @DATE 2020-02-11 13:49
 * @Description:
 */
@Data
public class InterfacePath {
    private String interfaceId;
    private String serviceName;
    private String path;
    private String method;
}
