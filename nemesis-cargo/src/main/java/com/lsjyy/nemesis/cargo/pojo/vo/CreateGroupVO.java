package com.lsjyy.nemesis.cargo.pojo.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author LsjYy
 * @DATE 2020-03-14 18:28
 * @Description: 创建分类
 */
@Data
public class CreateGroupVO implements Serializable {
    private String sysUserId;
    private String groupName;
    private Long upGroupId;
}
