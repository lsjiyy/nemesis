package com.lsjyy.nemesis.cargo.pojo.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author LsjYy
 * @DATE 2020-03-15 16:26
 * @Description: 修改分组
 */
@Data
public class RevampGroupVO implements Serializable {
    private String sysUserId;
    private Long groupId;
    private String groupName;
    private Integer status;

}
