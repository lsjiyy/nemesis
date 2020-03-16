package com.lsjyy.nemesis.cargo.pojo.dto;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @Author LsjYy
 * @DATE 2020-03-14 22:46
 * @Description:
 */
@Data
public class BackCargoDTO implements Serializable {
    private String cargoId;
    private String cargoName;
    private String groupName;
    private Integer countStock;
    private String intro;
    private Integer status;
    private String mainCoverUrl;
}
