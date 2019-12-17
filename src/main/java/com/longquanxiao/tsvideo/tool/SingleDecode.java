package com.longquanxiao.tsvideo.tool;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author longquanxiao
 * @date 2019/12/17
 */
public class SingleDecode {

    // 文件所在的目录
    public static String foldPath = "K:\\I盘\\longsourcemanage\\video\\1547400782827\\";
    // 文件的密钥
    public static String key = "6c0bd9baadeffc9a";
    // 文件的总的数量
    public static int partSize = 363;

    public static void main(String[] args) throws Exception{

        for(int i= 0;i < partSize;i++){
            // 组装文件所在的地址和相应解密存放的地址
            String sourcePath =foldPath + i;
            String destinationPath = foldPath + "e"+ i;
            EncryFileUtil.decryptFile(sourcePath,destinationPath,key);
        }
        // 合并数据
        try {
            // 合并数据-合并后的数据
            String mergeFilePath = foldPath+"merge.ts";
            FileOutputStream mergeFile = new FileOutputStream(mergeFilePath);
            for (int i=0;i < partSize;i++){
                // 获得解密后的文件
                String encodeFile = foldPath +"e"+i;
                FileInputStream inputStream = new FileInputStream(encodeFile);
                byte[] buffer = new byte[1024];
                int n=0;
                while ((n = inputStream.read(buffer))!= -1){
                    mergeFile.write(buffer,0,n);
                }
                inputStream.close();
            }
            System.out.println("合并数据成功");
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
