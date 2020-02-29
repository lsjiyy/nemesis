package com.lsjyy.nemesis.common.utils;


import com.lsjyy.nemesis.common.security.Coder;
import com.lsjyy.nemesis.common.security.DESCoder;

/**
 * @Authoer LsjYy
 * @DATE 2019-11-12 9:36
 * @Description: 加密工具类
 */
public class EncryptUtil {


    //加密方法
    public static String encrypt(String word) throws Exception {
        return Coder.encodeBase64URLSafeString(DESCoder.encrypt(word.getBytes(), DESCoder.DES_KEY));
    }

    //解密方法
    public static String decrypt(String word) throws Exception {
        return new String(DESCoder.decrypt(Coder.decryptBASE64(word), DESCoder.DES_KEY));
    }

    public static void main(String[]args){
        try{

            System.out.println(EncryptUtil.decrypt("EJgJQexIhtk"));
        }catch (Exception E){

        }
    }
}
