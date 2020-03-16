package com.lsjyy.nemesis.cargo.pojo;

import lombok.Data;

import java.sql.Timestamp;

/**
 * @Author LsjYy
 * @DATE 2020-03-14 18:25
 * @Description: 商品分类
 */
@Data
public class CargoGroup {
    private Long groupId;
    private String groupName;
    private Integer status;
    private Long upGroupId;
    private Timestamp createTime;
}
