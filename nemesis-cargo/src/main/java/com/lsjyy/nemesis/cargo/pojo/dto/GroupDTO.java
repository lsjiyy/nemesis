package com.lsjyy.nemesis.cargo.pojo.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Author LsjYy
 * @DATE 2020-03-14 19:21
 * @Description:
 */
@Data
public class GroupDTO implements Serializable {
    private Long groupId;
    private String groupName;
    private List<GroupDTO> children;
}
