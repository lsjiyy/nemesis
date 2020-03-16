package com.lsjyy.nemesis.cargo.pojo.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Author LsjYy
 * @DATE 2020-03-14 21:01
 * @Description: 创建商品
 */
@Data
public class CreateCargoVO implements Serializable {
    private String cargoName;
    private String mainCoverUrl;
    private Long groupId;
    private String intro;
    private String direction;
    private String unit;
    private List<CreateSpecVO> specList;
}
