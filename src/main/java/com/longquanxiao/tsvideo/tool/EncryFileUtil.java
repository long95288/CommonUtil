package com.longquanxiao.tsvideo.tool;


import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.security.SecureRandom;
import java.util.Base64;

/**
 * 文件加密和解密工具
 * @author longquanxiao
 * @date 2019/10/8
 */
public class EncryFileUtil {

    /**
     * 加密文件
     * @param sourcePath 源文件路径
     * @param destinationPath 保存文件的路径
     * @param key 加密的密钥
     */
    public static void encryptFile(String sourcePath, String destinationPath, String key) throws Exception{
        File outFile = new File(destinationPath);
        if (!outFile.exists()){
            outFile.createNewFile();
        }
        FileInputStream fis = new FileInputStream(sourcePath);
        FileOutputStream fos = new FileOutputStream(outFile);

        // 获得加密密钥
        Cipher cipher = Cipher.getInstance("AES");
//        byte[] bytes = Base64.getDecoder().decode(key);
        byte[] bytes = key.getBytes();
        SecretKeySpec secretKey = new SecretKeySpec(bytes,"AES");
        cipher.init(Cipher.ENCRYPT_MODE,secretKey);
        // 加密流
        CipherInputStream cis = new CipherInputStream(fis,cipher);
        byte[] buffer = new byte[1024];
        int n = 0;
        while ((n = cis.read(buffer)) != -1){
            fos.write(buffer,0,n);
        }
        System.out.println("加密成功:");
        cis.close();
        fos.close();
    }

    /**
     * 解密文件
     * @param sourcePath 源文件
     * @param destinationPath 保存的文件
     * @param key 密钥
     */
    public static void decryptFile(String sourcePath,String destinationPath,String key) throws Exception {
        File outFile = new File(destinationPath);
        if (!outFile.exists()){
            outFile.createNewFile();
        }
        // 待解密的文件输入流
        FileInputStream fis = new FileInputStream(sourcePath);
        FileOutputStream fos = new FileOutputStream(outFile);

        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
         byte[] bytes = key.getBytes();
        SecretKeySpec secretKey = new SecretKeySpec(bytes,"AES");
        IvParameterSpec iv = new IvParameterSpec(key.getBytes());
        cipher.init(Cipher.DECRYPT_MODE,secretKey,iv);
        // 解密流
        CipherInputStream cis = new CipherInputStream(fis,cipher);
        byte[] buffer = new byte[1024];
        int n = 0;
        while ((n = cis.read(buffer)) != -1){
            fos.write(buffer,0,n);
        }
        System.out.println("解密成功:");
        cis.close();
        fos.close();
    }
}
