package com.lsjyy.nemesis.common.utils.file;

import lombok.Data;

import java.io.Serializable;

/**
 * @Authoer LsjYy
 * @DATE 2020-02-07 11:51
 * @Description: 文件基础信息
 */
@Data
public class FileInfo implements Serializable{
   private Boolean action =false;  //false检验分片是否上传过(默认); true上传文件
   private String uuid;
   //文件名称
   private String name;
   //文件大小
   private Integer size;
   //总共几片
   private Integer total;
   //当前分片索引
   private Integer index;
   //整个文件的md5
   private String fileMd5;
   //文件第一个分片上传的日期
   private String date;
   //文件分片的md5
   private String md5;



}
