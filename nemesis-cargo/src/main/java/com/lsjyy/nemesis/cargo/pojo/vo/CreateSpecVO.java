package com.lsjyy.nemesis.cargo.pojo.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author LsjYy
 * @DATE 2020-03-14 21:08
 * @Description: 规格
 */
@Data
public class CreateSpecVO implements Serializable {
    private String specName;
    private String coverUrl;
    private Integer unitPrice;
    private Integer stock;

}
