package com.lsjyy.nemesis.parasite.vo;


import lombok.Data;

/**
 * @Authoer LsjYy
 * @DATE 2020-02-05 20:40
 * @Description:
 */
@Data
public class CsdnBlog {
    private int key;
    private String title;
    private String content;
    private String dates;
    private String category;
    private int view;
}
